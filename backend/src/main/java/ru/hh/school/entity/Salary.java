package ru.hh.school.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Salary {

    private int to;
    private int from;
    private String currency;
    private boolean gross;

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isGross() {
        return gross;
    }

    public void setGross(boolean gross) {
        this.gross = gross;
    }

    @Override
    public String toString() {
        return "Salary[to=" + to + ", form=" + from + ", currency=" + currency + ", gross=" + gross + ']';
    }
}
