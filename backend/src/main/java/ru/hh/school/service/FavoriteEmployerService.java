package ru.hh.school.service;

import static java.util.stream.Collectors.toList;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.transaction.annotation.Transactional;

import ru.hh.school.dao.FavoriteEmployerDao;
import ru.hh.school.dto.EmployerData;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.FavoriteEmployer;
import ru.hh.school.exception.FavoriteEmployerException;

@Transactional
public class FavoriteEmployerService {

  private final EmployerService employerService;

  private final FavoriteEmployerDao favoriteEmployers;

  private final Clock clock;

  @Inject
  public FavoriteEmployerService(EmployerService employerService, FavoriteEmployerDao favoriteEmployers, Clock clock) {
    this.employerService = employerService;
    this.favoriteEmployers = favoriteEmployers;
    this.clock = clock;
  }

  public void addToFavorites(Integer employerId, String comment) throws JsonProcessingException {
    FavoriteEmployer employer = new FavoriteEmployer(employerService.getEmployer(employerId));
    employer.setComment(comment);
    employer.setCreationTime(OffsetDateTime.now(clock));
    employer.setViewsCount(1);
    favoriteEmployers.save(employer);
  }

  public List<FavoriteEmployer> getFavoriteEmployers(Integer page, Integer perPage) {
    List<FavoriteEmployer> result = favoriteEmployers.get(page * perPage, perPage);
    favoriteEmployers.incrementViewsCount(result.stream().map(FavoriteEmployer::getId).collect(toList()));
    return result;
  }

  public FavoriteEmployer getFavoriteEmployer(Integer employerId) throws FavoriteEmployerException {
    return Optional.ofNullable(favoriteEmployers.get(employerId))
      .orElseThrow(() -> new FavoriteEmployerException("No such favorite employer"));
  }

  public void updateEmployer(Integer employerId, String comment) {
    FavoriteEmployer employer = getFavoriteEmployer(employerId);
    employer.setComment(comment);
    favoriteEmployers.update(employer);
  }

  public void removeFromFavorites(Integer employerId) {
    favoriteEmployers.delete(getFavoriteEmployer(employerId));
  }

  public void refreshEmployerData(Integer employerId) throws JsonProcessingException {
    FavoriteEmployer employer = getFavoriteEmployer(employerId);
    EmployerData newData = employerService.getEmployer(employerId);
    employer.setName(newData.getName());
    employer.setDescription(newData.getDescription());
    employer.setArea(new Area(newData.getArea()));
    favoriteEmployers.update(employer);
  }
}
