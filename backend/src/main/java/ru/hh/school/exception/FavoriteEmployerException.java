package ru.hh.school.exception;

public class FavoriteEmployerException extends FavoriteCollectionException {

  private static final long serialVersionUID = -6023011597284589481L;

  public FavoriteEmployerException() {
    super();
  }

  public FavoriteEmployerException(String message) {
    super(message);
  }

  public FavoriteEmployerException(String message, Throwable cause) {
    super(message, cause);
  }
}
