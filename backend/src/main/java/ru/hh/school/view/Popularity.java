package ru.hh.school.view;

public enum Popularity {

  POPULAR,
  REGULAR;

  public static Popularity calculate(Integer viewsCount, Integer popularityLimit) {
    return viewsCount > popularityLimit ? POPULAR : REGULAR;
  }
}
