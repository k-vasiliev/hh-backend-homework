import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.dao.FavoriteVacancyDao;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.FavoriteVacancy;
import ru.hh.school.exception.FavoriteVacancyException;
import ru.hh.school.service.FavoriteEmployerService;
import ru.hh.school.service.FavoriteVacancyService;
import ru.hh.school.service.VacancyService;

public class FavoriteVacancyServiceTest {

  @Mock
  VacancyService vacancyService;

  @Mock
  FavoriteVacancyDao favoriteVacancies;

  @Mock
  FavoriteEmployerService favoriteEmployers;

  @Mock
  FavoriteEmployerDao FavoriteEmployerDao;

  @Mock
  AreaDao areas;

  // I won't test time much, but it can be done in the future
  Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

  FavoriteVacancyService service;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    service = new FavoriteVacancyService(vacancyService, favoriteVacancies, favoriteEmployers,
      FavoriteEmployerDao, areas, clock);
  }

  @Test
  public void getFavoriteVacanciesShouldIncrementTheirViews() {
    Integer page = 5;
    Integer perPage = 10;
    List<FavoriteVacancy> result = List.of();
    when(favoriteVacancies.get(page * perPage, perPage)).thenReturn(result);
    assertEquals(result, service.getFavoriteVacancies(page, perPage));
    verify(favoriteVacancies, times(1)).incrementViewsCount(anyList());
  }

  @Test(expected = FavoriteVacancyException.class)
  public void getFavoriteVacancyShouldThrowOnNoFavoriteVacancy() {
    when(favoriteVacancies.get(0)).thenReturn(null);
    service.getFavoriteVacancy(0);
  }

  @Test
  public void removeFromFavoritesShouldCallFavoriteVacanciesDelete() {
    FavoriteVacancy vacancy = new FavoriteVacancy();
    when(favoriteVacancies.get(0)).thenReturn(vacancy);
    service.removeFromFavorites(0);
    verify(favoriteVacancies, times(1)).delete(any());
  }

  @Test
  public void refreshVacancyDataShouldSetNewDataPriorToUpdate() throws JsonProcessingException {
    FavoriteVacancy vacancy = new FavoriteVacancy();
    when(favoriteVacancies.get(0)).thenReturn(vacancy);
    VacancyDto vacancyData = new VacancyDto();
    vacancyData.setName("name");
    AreaDto areaData = new AreaDto();
    areaData.setName("area");
    vacancyData.setArea(areaData);
    when(vacancyService.getVacancy(0)).thenReturn(vacancyData);
    doAnswer(new Answer<FavoriteVacancy>(){
      @Override
      public FavoriteVacancy answer(InvocationOnMock invocation) throws Throwable {
        FavoriteVacancy vacancy = ((FavoriteVacancy) invocation.getArgument(0));
        assertEquals("name", vacancy.getName());
        assertEquals("area", vacancy.getArea().getName());
        return vacancy;
      }
    }).when(favoriteVacancies).update(any(FavoriteVacancy.class));
    service.refreshVacancyData(0);
  }
}

