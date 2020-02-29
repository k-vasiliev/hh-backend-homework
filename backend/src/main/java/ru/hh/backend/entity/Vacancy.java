package ru.hh.backend.entity;


import javax.persistence.*;

@Entity
@Table(name = "vacancy")
public class Vacancy extends BaseEntity {

    @Id
    @Column(name = "vacancy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vacancyId;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column
    private String salary;

    @Column
    private String description;

    @Column
    private String contacts;

    public Vacancy() {
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
