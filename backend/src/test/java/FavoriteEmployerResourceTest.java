import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;

import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.exceptionmapper.FavoriteCollectionExceptionMapper;
import ru.hh.school.resource.FavoriteEmployerResource;
import ru.hh.school.service.FavoriteEmployerService;

@ContextConfiguration(classes = FavoriteEmployerResourceTest.Config.class)
public class FavoriteEmployerResourceTest extends NabTestBase {

  private static final String RESOURCE_PATH = "/favorites/employer";

  private static final int BAD_REQUEST_SC = Response.Status.BAD_REQUEST.getStatusCode();
  private static final int OK_SC = Response.Status.OK.getStatusCode();

  @Inject
  FileSettings settings;

  @Inject
  FavoriteEmployerService service;

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder().configureJersey()
      .registerResources(FavoriteCollectionExceptionMapper.class)
      .bindToRoot().build();
  }

  @Test
  public void addToFavoritesShouldReturnBadRequestOnLongComment() {
    String comment = "a".repeat(FavoriteEmployerResource.COMMENT_MAX_LENGTH + 1);
    Response response = createRequest(RESOURCE_PATH).post(Entity.form(new Form("comment", comment)));
    assertEquals(BAD_REQUEST_SC, response.getStatus());
  }

  @Test
  public void addToFavoritesShouldReturnOk() {
    Response response = createRequest(RESOURCE_PATH).post(Entity.form(new Form("employer_id", "0")));
    assertEquals(OK_SC, response.getStatus());
  }

  @Test
  public void addToFavoritesShouldReturnBadRequestOnDataIntegrityViolationException() throws JsonProcessingException {
    doThrow(new DataIntegrityViolationException("test")).when(service).addToFavorites(0, null);
    Response response = createRequest(RESOURCE_PATH).post(Entity.form(new Form("employer_id", "0")));
    assertEquals(BAD_REQUEST_SC, response.getStatus());
  }

  // parameterized test only since junit5 :(
  @Test
  public void getFavoriteEmployersShouldReturnBadRequestOnNegativePage() {
    Response response = createRequest(RESOURCE_PATH + "?page=-1").get();
    assertEquals(BAD_REQUEST_SC, response.getStatus());
  }

  @Test
  public void getFavoriteEmployersShouldReturnBadRequestOnNegativePerPage() {
    Response response = createRequest(RESOURCE_PATH + "?per_page=-1").get();
    assertEquals(BAD_REQUEST_SC, response.getStatus());
  }

  @Test
  public void getFavoriteEmployersShouldReturnBadRequestOnNegativePaginationParams() {
    Response response = createRequest(RESOURCE_PATH + "?page=-1&per_page=-1").get();
    assertEquals(BAD_REQUEST_SC, response.getStatus());
  }

  @Test
  public void getFavoriteEmployersShouldReturnOk() {
    Integer page = settings.getInteger("pagination.default_page");
    Integer perPage = settings.getInteger("pagination.default_per_page");
    when(service.getFavoriteEmployers(page, perPage)).thenReturn(List.of());
    Response response = createRequest(RESOURCE_PATH).get();
    assertEquals(OK_SC, response.getStatus());
  }

  @Test
  public void updateEmployerShouldReturnBadRequestOnLongComment() {
    String comment = "a".repeat(FavoriteEmployerResource.COMMENT_MAX_LENGTH + 1);
    Response response = createRequest(RESOURCE_PATH + "/0").put(Entity.form(new Form("comment", comment)));
    assertEquals(BAD_REQUEST_SC, response.getStatus());
  }

  @Test
  public void updateEmployerShouldReturnOkOnUpdateEmployer() {
    Response response = createRequest(RESOURCE_PATH + "/0").put(Entity.form(new Form()));
    assertEquals(OK_SC, response.getStatus());
  }

  @Test
  public void deleteShouldReturnOk() {
    Response response = createRequest(RESOURCE_PATH + "/0").delete();
    assertEquals(OK_SC, response.getStatus());
  }

  @Test
  public void refreshEmployerDataShouldReturnOk() throws JsonProcessingException {
    Response response = createRequest(RESOURCE_PATH + "/0/refresh").post(Entity.form(new Form()));
    assertEquals(OK_SC, response.getStatus());
  }

  @Configuration
  @Import(AppTestConfig.class)
  public static class Config {

    @Bean
    @Primary
    public FavoriteEmployerService getEmployerService() {
      return Mockito.mock(FavoriteEmployerService.class);
    }
  }
}
