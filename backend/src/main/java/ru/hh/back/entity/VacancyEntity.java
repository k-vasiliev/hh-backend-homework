package ru.hh.back.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vacancy")
public class VacancyEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "title", nullable = false)
    private String title;
    @OneToOne
    @JoinColumn(name = "companyId")
    private CompanyEntity company;
    @Column(name = "salary", nullable = false)
    private Integer salary;
    @Column(name = "description")
    private String description;
    @Column(name = "contacts")
    private String contacts;

    public VacancyEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
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
