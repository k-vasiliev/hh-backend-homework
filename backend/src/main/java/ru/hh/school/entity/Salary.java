package ru.hh.school.entity;

import javax.persistence.*;

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "to_sal")
    private Integer to;

    @Column(name = "from_sal")
    private Integer from;

    @Column(name = "currency")
    private String currency;

    @Column(name = "gross")
    private Boolean gross;

    public Salary() {
    }

    public Salary(Integer to, Integer from, String currency, Boolean gross) {
        this.to = to;
        this.from = from;
        this.currency = currency;
        this.gross = gross;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
