import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;

import feign.FeignException;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.starter.jersey.ObjectMapperContextResolver;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.exceptionmapper.FeignExceptionMapper;
import ru.hh.school.resource.VacancyResource;
import ru.hh.school.service.VacancyService;

@ContextConfiguration(classes = AppTestConfig.class)
public class VacancyResourceTest extends NabTestBase {

  @Mock
  VacancyService service;

  @InjectMocks
  VacancyResource res;

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey()
      .registerResources(FeignExceptionMapper.class, ObjectMapperContextResolver.class)
      .bindToRoot().build();
  }

  @Before
  public void before() throws IOException {
    MockitoAnnotations.initMocks(this);
  }

  private static Vacancy getEmptyVacancy() {
    Vacancy vacancy = new Vacancy();
    vacancy.setEmployer(new Employer());
    return vacancy;
  }

  @Test
  public void getVacanciesShouldReturnOk() throws IOException {
    when(service.getVacancies("empty", null, null))
      .thenReturn(List.of(getEmptyVacancy()));
    Response response = createRequest("/vacancy?query=empty").get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void getVacancyShouldReturnOk() throws JsonProcessingException {
    when(service.getVacancy(0))
      .thenReturn(getEmptyVacancy());
    Response response = createRequest("/vacancy/0").get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void getVacanciesShouldDelegateBadRequest() throws IOException {
    when(service.getVacancies(null, -1, null))
      .thenThrow(new FeignException(400, "Bad request") {});
    Response response = createRequest("/vacancy?page=-1").get();
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void getVacancyShouldDelegateNotFound() throws JsonProcessingException {
    when(service.getVacancy(-1))
      .thenThrow(new FeignException(404, "Not found") {});
    Response response = createRequest("/vacancy/-1").get();
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }
}
