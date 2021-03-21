package ru.hh.school.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

public class SalaryDto {
    private Integer from;
    private Integer to;
    private String currency;
    private Boolean gross;

    public Integer getFrom() {
        return from;
    }

    @JsonSetter("from")
    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    @JsonSetter("to")
    public void setTo(Integer to) {
        this.to = to;
    }

    public String getCurrency() {
        return currency;
    }

    @JsonSetter("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getGross() {
        return gross;
    }

    @JsonSetter("gross")
    public void setGross(Boolean gross) {
        this.gross = gross;
    }
}
