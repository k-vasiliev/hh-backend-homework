package ru.hh.school.exception;

import javax.ws.rs.core.Response.Status;

public abstract class FavoriteCollectionException extends RuntimeException {

  private static final long serialVersionUID = 3806739289771842053L;

  private int SUGGESTED_RESPONSE_CODE = Status.BAD_REQUEST.getStatusCode();

  public FavoriteCollectionException() {
    super();
  }

  public FavoriteCollectionException(String message) {
    super(message);
  }

  public FavoriteCollectionException(String message, Throwable cause) {
    super(message, cause);
  }

  public FavoriteCollectionException setSuggestedResponseCode(int code) {
    SUGGESTED_RESPONSE_CODE = code;
    return this;
  }

  public int getSeggestedResponseCode() {
    return SUGGESTED_RESPONSE_CODE;
  }
}
