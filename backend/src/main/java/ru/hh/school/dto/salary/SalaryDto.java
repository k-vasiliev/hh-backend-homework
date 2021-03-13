package ru.hh.school.dto.salary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalaryDto {
    private Integer to;
    private Integer from;
    private String currency;
    private Boolean gross;

    public SalaryDto(Integer to, Integer from, String currency, Boolean gross) {
        this.to = to;
        this.from = from;
        this.currency = currency;
        this.gross = gross;
    }

    public SalaryDto() {
    }

    public Integer getTo() {
        return to;
    }

    public Integer getFrom() {
        return from;
    }

    public String getCurrency() {
        return currency;
    }

    public Boolean getGross() {
        return gross;
    }

    @Override
    public String toString() {
        return "SalaryDto{" +
                "to=" + to +
                ", from=" + from +
                ", currency='" + currency + '\'' +
                ", gross=" + gross +
                '}';
    }
}
