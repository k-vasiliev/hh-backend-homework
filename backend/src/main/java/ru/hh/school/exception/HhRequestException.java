package ru.hh.school.exception;

public class HhRequestException extends Exception {
  public HhRequestException() {
  }

  public HhRequestException(String message) {
    super(message);
  }

  public HhRequestException(Throwable cause) {
    super(cause);
  }
}
