import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;

import feign.FeignException;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.exceptionmapper.FeignExceptionMapper;
import ru.hh.school.service.EmployerService;

@ContextConfiguration(classes = EmployerResourceTest.Config.class)
public class EmployerResourceTest extends NabTestBase {

  @Inject
  EmployerService service;

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey()
      .registerResources(FeignExceptionMapper.class)
      .bindToRoot().build();
  }

  @Test
  public void getEmployersShouldReturnOk() throws IOException {
    when(service.getEmployers("test", null, null))
      .thenReturn(List.of(new EmployerDto()));
    Response response = createRequest("/employer?query=test").get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void getEmployerShouldReturnOk() throws JsonProcessingException {
    when(service.getEmployer(0))
      .thenReturn(new EmployerDto());
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

  @Configuration
  @Import(AppTestConfig.class)
  public static class Config {

    @Bean
    @Primary
    public EmployerService getEmployerService() {
      return Mockito.mock(EmployerService.class);
    }
  }
}
