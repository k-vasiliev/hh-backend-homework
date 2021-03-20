package ru.hh.school.view;

public class Salary {

  private Integer from;

  private Integer to;

  private Boolean gross;

  private String currency;

  public Salary(Integer from, Integer to, Boolean gross, String currency) {
    this.from = from;
    this.to = to;
    this.gross = gross;
    this.currency = currency;
  }

  public static Salary getSalaryOrNull(Integer from, Integer to, Boolean gross, String currency) {
    return (currency != null || from != null || to != null || gross != null) ?
            new Salary(from, to, gross, currency) : null;
  }

  public Integer getFrom() {
    return from;
  }

  public Integer getTo() {
    return to;
  }

  public Boolean getGross() {
    return gross;
  }

  public String getCurrency() {
    return currency;
  }
}
