import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import ru.hh.nab.testbase.hibernate.HibernateTestBase;
import ru.hh.school.dao.FavoriteVacancyDao;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.FavoriteEmployer;
import ru.hh.school.entity.FavoriteVacancy;

@ContextConfiguration(classes = AppTestConfig.class)
public class FavoriteVacancyDaoTest extends HibernateTestBase {

  @Inject
  FavoriteVacancyDao dao;

  @Before
  public void init() {
    startTransaction();
  }

  @After
  public void rollback() {
    rollBackTransaction();
  }

  @Test
  public void saveShouldSaveVacancyToDb() {
    dao.save(createFavoriteVacancy(1, "test"));
    assertEquals("test", getCurrentSession().get(FavoriteVacancy.class, 1).getName());
  }

  @Test
  public void getShouldReturnValidVacancy() {
    FavoriteVacancy vacancy = createFavoriteVacancy(1, "test");
    getCurrentSession().save(vacancy);
    assertEquals(vacancy, dao.get(1));
  }

  @Test
  public void updateShouldUpdateVacancyInDb() {
    FavoriteVacancy vacancy = createFavoriteVacancy(1, "test");
    getCurrentSession().save(vacancy);
    String comment = "comment";
    vacancy.setComment(comment);
    dao.update(vacancy);
    assertEquals(comment, getCurrentSession().get(FavoriteVacancy.class, 1).getComment());
  }

  @Test
  public void deleteShouldRemoveVacancyFromDb() {
    FavoriteVacancy vacancy = createFavoriteVacancy(1, "test");
    getCurrentSession().save(vacancy);
    dao.delete(vacancy);
    assertNull(getCurrentSession().get(FavoriteVacancy.class, 1));
  }

  @Test
  public void getListOfVacanciesShouldReturnListOfGivenLength() {
    Area area = new Area();
    area.setId(2);
    FavoriteEmployer employer = new FavoriteEmployer();
    employer.setId(0);
    getCurrentSession().save(createFavoriteVacancy(1, "test1", area, employer));
    getCurrentSession().save(createFavoriteVacancy(2, "test2", area, employer));
    getCurrentSession().save(createFavoriteVacancy(3, "test3", area, employer));
    assertEquals(2, dao.get(0, 2).size());
  }

  @Test
  public void getListOfVacanciesShouldReturnEmptyListOnZeroCountArgument() {
    Area area = new Area();
    area.setId(2);
    FavoriteEmployer employer = new FavoriteEmployer();
    employer.setId(0);
    getCurrentSession().save(createFavoriteVacancy(1, "test1", area, employer));
    getCurrentSession().save(createFavoriteVacancy(2, "test2", area, employer));
    getCurrentSession().save(createFavoriteVacancy(3, "test3", area, employer));
    assertTrue(dao.get(0, 0).isEmpty());
  }

  @Test
  public void incrementViewsCountShouldBeOkOnEmptyList() {
    dao.incrementViewsCount(List.of());
  }

  // there was dreadful concurrent test
  @Test
  public void incrementViewsCountShouldIncrementVacanciesViewsWithGivenIds() {
    getCurrentSession().save(createFavoriteVacancy(1, "test1", 0));
    getCurrentSession().save(createFavoriteVacancy(2, "test2", 0));
    getCurrentSession().save(createFavoriteVacancy(3, "test3", 0));
    int iterations = 1000;
    for (int i = 0; i < iterations; ++i)
      dao.incrementViewsCount(List.of(1, 2));
    getCurrentSession().clear();
    assertEquals(Integer.valueOf(iterations), getCurrentSession().get(FavoriteVacancy.class, 1).getViewsCount());
    assertEquals(Integer.valueOf(iterations), getCurrentSession().get(FavoriteVacancy.class, 2).getViewsCount());
    assertEquals(Integer.valueOf(0), getCurrentSession().get(FavoriteVacancy.class, 3).getViewsCount());
  }

  private FavoriteVacancy createFavoriteVacancy(Integer id, String name) {
    FavoriteVacancy vacancy = new FavoriteVacancy();
    vacancy.setId(id);
    vacancy.setName(name);
    return vacancy;
  }

  private FavoriteVacancy createFavoriteVacancy(Integer id, String name, Area area, FavoriteEmployer employer) {
    FavoriteVacancy vacancy = createFavoriteVacancy(id, name);
    vacancy.setArea(area);
    vacancy.setEmployer(employer);
    return vacancy;
  }

  private FavoriteVacancy createFavoriteVacancy(Integer id, String name, Integer viewsCount) {
    FavoriteVacancy vacancy = createFavoriteVacancy(id, name);
    vacancy.setViewsCount(viewsCount);
    return vacancy;
  }
}
