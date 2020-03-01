package entity;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacancy")
public class VacancyEntity {

    @Id
    @GeneratedValue
    @Column(name = "vacancy_id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "description")
    private String description;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "creation_time")
    private LocalDate created;

    @OneToOne(targetEntity = CompanyEntity.class)
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    private CompanyEntity   company;

    public String getTitle() {
        return title;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }

    public LocalDate getCreated() {
        return created;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public Integer getId() {
        return id;
    }
}
