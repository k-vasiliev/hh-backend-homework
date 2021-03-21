package ru.hh.school.dto;

public class SalaryDTO {
    private Double from;
    private Double to;
    private String currency;
    private Boolean gross;

    public SalaryDTO(Double from, Double to, String currency, Boolean gross) {
        this.from = from;
        this.to = to;
        this.currency = currency;
        this.gross = gross;
    }

    public Double getFrom() {
        return from;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(Double to) {
        this.to = to;
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
}
