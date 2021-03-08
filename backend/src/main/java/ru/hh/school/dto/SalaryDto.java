package ru.hh.school.dto;

public class SalaryDto {

    private Integer to = null;

    private Integer from = null;

    private String currency = "";

    private Boolean gross = null;

    public SalaryDto() {}

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getGross() {
        return gross;
    }

    public void setGross(Boolean gross) {
        this.gross = gross;
    }

    @Override
    public String toString() {
        return "SalaryDto[" +
                "to=" + to +
                ", from=" + from +
                ", currency='" + currency + '\'' +
                ", gross=" + gross +
                ']';
    }
}
