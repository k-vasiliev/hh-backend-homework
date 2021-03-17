import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.entity.FavoriteEmployer;
import ru.hh.school.exception.FavoriteEmployerException;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.FavoriteEmployerService;

public class FavoriteEmployerServiceTest {

  @Mock
  EmployerService employerService;

  @Mock
  FavoriteEmployerDao favoriteEmployers;

  // I won't test time much, but it can be done in the future
  Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

  FavoriteEmployerService service;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    service = new FavoriteEmployerService(employerService, favoriteEmployers, clock);
  }

  @Test
  public void addToFavoritesShouldSaveEntityWithCurrentTimeOneViewAndGivenComment() throws JsonProcessingException {
    EmployerDto employerData = new EmployerDto();
    employerData.setName("test");
    employerData.setArea(new AreaDto());
    String comment = "test";
    when(employerService.getEmployer(0)).thenReturn(employerData);
    doAnswer(new Answer<FavoriteEmployer>(){
      @Override
      public FavoriteEmployer answer(InvocationOnMock invocation) throws Throwable {
        FavoriteEmployer employer = ((FavoriteEmployer) invocation.getArgument(0));
        assertEquals("test", employer.getName());
        assertEquals(comment, employer.getComment());
        assertEquals(OffsetDateTime.now(clock), employer.getCreationTime());
        assertEquals(Integer.valueOf(1), employer.getViewsCount());
        return employer;
      }
    }).when(favoriteEmployers).save(any(FavoriteEmployer.class));
    service.addToFavorites(0, comment);
  }

  @Test
  public void getFavoriteEmployersShouldIncrementTheirViews() {
    Integer page = 5;
    Integer perPage = 10;
    List<FavoriteEmployer> result = List.of();
    when(favoriteEmployers.get(page * perPage, perPage)).thenReturn(result);
    assertEquals(result, service.getFavoriteEmployers(page, perPage));
    verify(favoriteEmployers, times(1)).incrementViewsCount(anyList());
  }

  @Test(expected = FavoriteEmployerException.class)
  public void getFavoriteEmployerShouldThrowOnNoFavoriteEmployer() {
    when(favoriteEmployers.get(0)).thenReturn(null);
    service.getFavoriteEmployer(0);
  }

  @Test
  public void updateEmployerShouldSetComment() {
    FavoriteEmployer employer = new FavoriteEmployer();
    String comment = "test";
    when(favoriteEmployers.get(0)).thenReturn(employer);
    doAnswer(new Answer<FavoriteEmployer>(){
      @Override
      public FavoriteEmployer answer(InvocationOnMock invocation) throws Throwable {
        FavoriteEmployer employer = ((FavoriteEmployer) invocation.getArgument(0));
        assertEquals(comment, employer.getComment());
        return employer;
      }
    }).when(favoriteEmployers).update(any(FavoriteEmployer.class));
    service.updateEmployer(0, comment);
  }

  @Test
  public void removeFromFavoritesShouldCallFavoriteEmployersDelete() {
    FavoriteEmployer employer = new FavoriteEmployer();
    when(favoriteEmployers.get(0)).thenReturn(employer);
    service.removeFromFavorites(0);
    verify(favoriteEmployers, times(1)).delete(any());
  }

  @Test
  public void refreshEmployerDataShouldSetNewDataPriorToUpdate() throws JsonProcessingException {
    FavoriteEmployer employer = new FavoriteEmployer();
    when(favoriteEmployers.get(0)).thenReturn(employer);
    EmployerDto employerData = new EmployerDto();
    employerData.setName("name");
    employerData.setDescription("description");
    AreaDto areaData = new AreaDto();
    areaData.setName("area");
    employerData.setArea(areaData);
    when(employerService.getEmployer(0)).thenReturn(employerData);
    doAnswer(new Answer<FavoriteEmployer>(){
      @Override
      public FavoriteEmployer answer(InvocationOnMock invocation) throws Throwable {
        FavoriteEmployer employer = ((FavoriteEmployer) invocation.getArgument(0));
        assertEquals("name", employer.getName());
        assertEquals("description", employer.getDescription());
        assertEquals("area", employer.getArea().getName());
        return employer;
      }
    }).when(favoriteEmployers).update(any(FavoriteEmployer.class));
    service.refreshEmployerData(0);
  }
}
