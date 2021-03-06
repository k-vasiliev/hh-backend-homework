package ru.hh.school.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Salary {

    @Column(name = "salary_to")
    private Integer to = null;
    @Column(name = "salary_from")
    private Integer from = null;
    @Column(name = "salary_curr")
    private String currency = "";
    @Column(name = "salary_gross")
    private Boolean gross = null;

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

    public Boolean isGross() {
        return gross;
    }

    public void setGross(Boolean gross) {
        this.gross = gross;
    }

    @Override
    public String toString() {
        return "Salary[to=" + to + ", form=" + from + ", currency=" + currency + ", gross=" + gross + ']';
    }
}
