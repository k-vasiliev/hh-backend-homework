package ru.hh.school.resource.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class SalaryData {
    @Column(name = "start_salary")
    private Double from;
    @Column(name = "end_salary")
    private Double to;
    @Column(columnDefinition = "TEXT")
    private String currency;
    private Boolean gross;

    public SalaryData() {
    }

    public SalaryData(Double from, Double to, String currency, Boolean gross) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryData that = (SalaryData) o;
        return Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(currency, that.currency) && Objects.equals(gross, that.gross);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, currency, gross);
    }
}
