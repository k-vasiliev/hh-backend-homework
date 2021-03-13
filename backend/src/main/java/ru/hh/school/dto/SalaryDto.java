package ru.hh.school.dto;

import java.util.Objects;

public class SalaryDto {

    private Integer to = null;

    private Integer from = null;

    private String currency = "";

    private Boolean gross = null;

    public SalaryDto() {}

    public SalaryDto(Integer to, Integer from, String currency, Boolean gross) {
        this.to = to;
        this.from = from;
        this.currency = currency;
        this.gross = gross;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalaryDto) || o == null) return false;
        SalaryDto salaryDto = (SalaryDto) o;
        return Objects.equals(to, salaryDto.to) &&
                Objects.equals(from, salaryDto.from) &&
                Objects.equals(currency, salaryDto.currency) &&
                Objects.equals(gross, salaryDto.gross);
    }

}
