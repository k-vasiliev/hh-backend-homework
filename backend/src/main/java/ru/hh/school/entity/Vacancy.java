package ru.hh.school.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "vacancy")
public class Vacancy extends CommonDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id",  updatable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "company_id")
    private Company company;

    @Column (name = "title", nullable = false)
    private String title;

    @Column (name = "compensation")
    private BigInteger compensation;

    @Column (name = "description")
    private String description;

    @Column (name = "contacts", nullable = false)
    private String contacts;

    public Vacancy (){
    }

    public Vacancy(Company company, String title, BigInteger compensation, String description, String contacts) {
        this.company = company;
        this.title = title;
        this.compensation = compensation;
        this.description = description;
        this.contacts = contacts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public BigInteger getCompensation() {
        return compensation;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }
}
