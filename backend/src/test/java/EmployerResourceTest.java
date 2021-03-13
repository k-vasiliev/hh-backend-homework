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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;

import feign.FeignException;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.entity.Employer;
import ru.hh.school.exceptionmapper.FeignExceptionMapper;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.service.EmployerService;

@ContextConfiguration(classes = AppTestConfig.class)
public class EmployerResourceTest extends NabTestBase {

  @Mock
  EmployerService service;

  @InjectMocks
  EmployerResource res;

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey()
      .registerResources(FeignExceptionMapper.class)
      .bindToRoot().build();
  }

  @Before
  public void before() throws IOException {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getEmployersShouldReturnOk() throws IOException {
    when(service.getEmployers("test", null, null))
      .thenReturn(List.of(new Employer()));
    Response response = createRequest("/employer?query=test").get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void getEmployerShouldReturnOk() throws JsonProcessingException {
    when(service.getEmployer(0))
      .thenReturn(new Employer());
    Response response = createRequest("/employer/0").get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void getEmployersShouldDelegateBadRequest() throws IOException {
    when(service.getEmployers(null, -1, null))
      .thenThrow(new FeignException(400, "Bad request") {});
    Response response = createRequest("/employer?page=-1").get();
    assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
  }

  @Test
  public void getEmployerShouldDelegateNotFound() throws JsonProcessingException {
    Mockito.when(service.getEmployer(-1))
      .thenThrow(new FeignException(404, "Not found") {});
    Response response = createRequest("/employer/-1").get();
    assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
  }
}
