import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ru.hh.school.config.CommonConfig;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.feignclient.HhApi;
import ru.hh.school.service.VacancyService;

public class VacancyServiceTest {

  private static final String EMPTY_ITEMS_JSON = "{\"items\":[]}";

  private static final String ONE_ITEM_JSON = "{\"items\":[{\"name\":\"test\"}]}";

  private static final String VACANCY_JSON = "{\"name\":\"test\"}";

  @Mock
  HhApi api;

  VacancyService service;

  @Before
  public void before() throws IOException {
    MockitoAnnotations.initMocks(this);
    service = new VacancyService(api, new CommonConfig().getObjectMapper());
  }

  @Test
  public void getVacanciesWithZeroPerPageShouldReturnEmptyList() throws IOException {
    when(api.getVacancies(null, null, 0)).thenReturn(EMPTY_ITEMS_JSON);
    assertTrue(service.getVacancies(null, null, 0).isEmpty());
  }

  @Test
  public void getVacanciesShouldReturnVacancyListOnValidJson() throws IOException {
    when(api.getVacancies("test", null, null)).thenReturn(ONE_ITEM_JSON);
    List<VacancyDto> vacancies = service.getVacancies("test", null, null);
    assertTrue(vacancies.size() == 1);
    assertEquals("test", vacancies.get(0).getName());
  }

  @Test
  public void getVacancyShouldReturnVacancyOnValidJson() throws JsonProcessingException {
    when(api.getVacancy(0)).thenReturn(VACANCY_JSON);
    assertEquals("test", service.getVacancy(0).getName());
  }
}
