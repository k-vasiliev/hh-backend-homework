package ru.hh.school.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import ru.hh.school.entity.FavoriteEmployer;

public class FavoriteEmployerService {

  public void addToFavorites(Integer employerId, String comment) throws JsonProcessingException {
  }

  public List<FavoriteEmployer> getFavoriteEmployers(Integer page, Integer perPage) {
    return null;
  }

  public void updateEmployer(Integer employerId, String comment) {
  }

  public void removeFromFavorites(Integer employerId) {
  }

  public void refreshEmployerData(Integer employerId) throws JsonProcessingException {
  }
}
