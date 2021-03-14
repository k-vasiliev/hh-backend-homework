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
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.feignclient.HhApi;
import ru.hh.school.service.EmployerService;

public class EmployerServiceTest {

  private static final String EMPTY_ITEMS_JSON = "{\"items\":[]}";

  private static final String ONE_ITEM_JSON = "{\"items\":[{\"name\":\"test\"}]}";

  private static final String EMPLOYER_JSON = "{\"id\":\"1455\",\"trusted\":true,\"name\":\"test\"}";

  @Mock
  HhApi api;

  EmployerService service;

  @Before
  public void before() throws IOException {
    MockitoAnnotations.initMocks(this);
    service = new EmployerService(api, new CommonConfig().getObjectMapper());
  }

  @Test
  public void getEmployersWithPerPageZeroShouldReturnEmptyList() throws IOException {
    when(api.getEmployers(null, null, 0)).thenReturn(EMPTY_ITEMS_JSON);
    assertTrue(service.getEmployers(null, null, 0).isEmpty());
  }

  @Test
  public void getEmployersShouldReturnEmployerListOnValidJson() throws IOException {
    when(api.getEmployers("test", null, null)).thenReturn(ONE_ITEM_JSON);
    List<EmployerDto> employers = service.getEmployers("test", null, null);
    assertTrue(employers.size() == 1);
    assertEquals("test", employers.get(0).getName());
  }

  @Test
  public void getEmployerShouldReturnEmployerOnValidJson() throws JsonProcessingException {
    when(api.getEmployer(0)).thenReturn(EMPLOYER_JSON);
    assertEquals("test", service.getEmployer(0).getName());
  }
}
