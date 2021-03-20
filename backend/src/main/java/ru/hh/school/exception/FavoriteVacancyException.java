package ru.hh.school.exception;

public class FavoriteVacancyException extends FavoriteCollectionException {

  private static final long serialVersionUID = -3207783150524847627L;

  public FavoriteVacancyException() {
    super();
  }

  public FavoriteVacancyException(String message) {
    super(message);
  }

  public FavoriteVacancyException(String message, Throwable cause) {
    super(message, cause);
  }
}
