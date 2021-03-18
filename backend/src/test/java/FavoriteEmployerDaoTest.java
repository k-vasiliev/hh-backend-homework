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
import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.FavoriteEmployer;

@ContextConfiguration(classes = AppTestConfig.class)
public class FavoriteEmployerDaoTest extends HibernateTestBase {

  @Inject
  FavoriteEmployerDao dao;

  @Before
  public void init() {
    startTransaction();
  }

  @After
  public void rollback() {
    rollBackTransaction();
  }

  @Test
  public void saveShouldSaveEmployerToDb() {
    dao.save(createFavoriteEmployer(1, "test"));
    assertEquals("test", getCurrentSession().get(FavoriteEmployer.class, 1).getName());
  }

  @Test
  public void getShouldReturnValidEmployer() {
    FavoriteEmployer employer = createFavoriteEmployer(1, "test");
    getCurrentSession().save(employer);
    assertEquals(employer, dao.get(1));
  }

  @Test
  public void updateShouldUpdateEmployerInDb() {
    FavoriteEmployer employer = createFavoriteEmployer(1, "test");
    getCurrentSession().save(employer);
    String comment = "comment";
    employer.setComment(comment);
    dao.update(employer);
    assertEquals(comment, getCurrentSession().get(FavoriteEmployer.class, 1).getComment());
  }

  @Test
  public void deleteShouldRemoveEmployerFromDb() {
    FavoriteEmployer employer = createFavoriteEmployer(1, "test");
    getCurrentSession().save(employer);
    dao.delete(employer);
    assertNull(getCurrentSession().get(FavoriteEmployer.class, 1));
  }

  @Test
  public void getListOfEmployersShouldReturnListOfGivenLength() {
    Area area = new Area();
    area.setId(2);
    getCurrentSession().save(createFavoriteEmployer(1, "test1", area));
    getCurrentSession().save(createFavoriteEmployer(2, "test2", area));
    getCurrentSession().save(createFavoriteEmployer(3, "test3", area));
    assertEquals(2, dao.get(0, 2).size());
  }

  @Test
  public void getListOfEmployersShouldReturnEmptyListOnZeroCountArgument() {
    Area area = new Area();
    area.setId(2);
    getCurrentSession().save(createFavoriteEmployer(1, "test1", area));
    getCurrentSession().save(createFavoriteEmployer(2, "test2", area));
    getCurrentSession().save(createFavoriteEmployer(3, "test3", area));
    assertTrue(dao.get(0, 0).isEmpty());
  }

  @Test
  public void incrementViewsCountShouldBeOkOnEmptyList() {
    dao.incrementViewsCount(List.of());
  }

  // there was dreadful concurrent test
  @Test
  public void incrementViewsCountShouldIncrementEmployersViewsWithGivenIds() {
    getCurrentSession().save(createFavoriteEmployer(1, "test1", 0));
    getCurrentSession().save(createFavoriteEmployer(2, "test2", 0));
    getCurrentSession().save(createFavoriteEmployer(3, "test3", 0));
    int iterations = 1000;
    for (int i = 0; i < iterations; ++i)
      dao.incrementViewsCount(List.of(1, 2));
    getCurrentSession().clear();
    assertEquals(Integer.valueOf(iterations), getCurrentSession().get(FavoriteEmployer.class, 1).getViewsCount());
    assertEquals(Integer.valueOf(iterations), getCurrentSession().get(FavoriteEmployer.class, 2).getViewsCount());
    assertEquals(Integer.valueOf(0), getCurrentSession().get(FavoriteEmployer.class, 3).getViewsCount());
  }

  private FavoriteEmployer createFavoriteEmployer(Integer id, String name) {
    FavoriteEmployer employer = new FavoriteEmployer();
    employer.setId(id);
    employer.setName(name);
    return employer;
  }

  private FavoriteEmployer createFavoriteEmployer(Integer id, String name, Area area) {
    FavoriteEmployer employer = createFavoriteEmployer(id, name);
    employer.setArea(area);
    return employer;
  }

  private FavoriteEmployer createFavoriteEmployer(Integer id, String name, Integer viewsCount) {
    FavoriteEmployer employer = createFavoriteEmployer(id, name);
    employer.setViewsCount(viewsCount);
    return employer;
  }
}
