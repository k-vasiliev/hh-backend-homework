package ru.hh.school.service;

import static java.util.stream.Collectors.toList;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.transaction.annotation.Transactional;

import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.dao.FavoriteVacancyDao;
import ru.hh.school.dto.VacancyData;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.FavoriteEmployer;
import ru.hh.school.entity.FavoriteVacancy;
import ru.hh.school.exception.FavoriteVacancyException;

@Transactional
public class FavoriteVacancyService {

  private final VacancyService vacancyService;

  private final FavoriteVacancyDao favoriteVacancies;

  private final FavoriteEmployerService favoriteEmployers;

  private final FavoriteEmployerDao favoriteEmployerDao;

  private final AreaDao areas;

  private final Clock clock;

  @Inject
  public FavoriteVacancyService(VacancyService vacancyService, FavoriteVacancyDao favoriteVacancies,
      FavoriteEmployerService favoriteEmployers, FavoriteEmployerDao favoriteEmployerDao, AreaDao areas, Clock clock) {
    this.vacancyService = vacancyService;
    this.favoriteVacancies = favoriteVacancies;
    this.favoriteEmployers = favoriteEmployers;
    this.favoriteEmployerDao = favoriteEmployerDao;
    this.areas = areas;
    this.clock = clock;
  }

  public void addToFavorites(Integer vacancyId, String comment) throws JsonProcessingException {
    VacancyData vacancyData = vacancyService.getVacancy(vacancyId);
    FavoriteVacancy vacancy = new FavoriteVacancy(vacancyData);
    FavoriteEmployer employer = favoriteEmployerDao.get(vacancyData.getEmployer().getId());
    if (employer == null) {
      favoriteEmployers.addToFavorites(vacancyData.getEmployer().getId(), null);
      employer = favoriteEmployerDao.get(vacancyData.getEmployer().getId());
    }
    vacancy.setEmployer(employer);
    vacancy.setArea(Optional.ofNullable(areas.get(vacancyData.getArea().getId())).orElse(new Area(vacancyData.getArea())));
    vacancy.setComment(comment);
    vacancy.setArchivingTime(OffsetDateTime.now(clock));
    vacancy.setViewsCount(1);
    favoriteVacancies.save(vacancy);
  }

  public List<FavoriteVacancy> getFavoriteVacancies(Integer page, Integer perPage) {
    List<FavoriteVacancy> result = favoriteVacancies.get(page * perPage, perPage);
    favoriteVacancies.incrementViewsCount(result.stream().map(FavoriteVacancy::getId).collect(toList()));
    favoriteEmployerDao.incrementViewsCount(result.stream().map(vacancy -> vacancy.getEmployer().getId())
      .distinct().collect(toList()));
    return result;
  }

  public FavoriteVacancy getFavoriteVacancy(Integer vacancyId) throws FavoriteVacancyException {
    return Optional.ofNullable(favoriteVacancies.get(vacancyId))
      .orElseThrow(() -> new FavoriteVacancyException("No such favorite vacancy"));
  }

  public void removeFromFavorites(Integer vacancyId) {
    favoriteVacancies.delete(getFavoriteVacancy(vacancyId));
  }

  public void refreshVacancyData(Integer vacancyId) throws JsonProcessingException {
    FavoriteVacancy vacancy = getFavoriteVacancy(vacancyId);
    VacancyData newData = vacancyService.getVacancy(vacancyId);
    vacancy.setName(newData.getName());
    vacancy.setArea(new Area(newData.getArea()));
    vacancy.setCompensationFrom(newData.getCompensationFrom());
    vacancy.setCompensationTo(newData.getCompensationTo());
    vacancy.setCompensationGross(newData.getCompensationGross());
    vacancy.setCompensationCurrency(newData.getCompensationCurrency());
    favoriteVacancies.update(vacancy);
  }
}

