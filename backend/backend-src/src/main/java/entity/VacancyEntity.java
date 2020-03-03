package entity;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import dto.NewVacancyDto;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vacancy")
public class VacancyEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
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

    @Column(name = "creation_time", insertable = false)
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

    public VacancyEntity(Integer vacancyId) {
        id = vacancyId;
    }

    public VacancyEntity(NewVacancyDto vacancyDto) {
        this.title = vacancyDto.vacancyTtile;
        this.salary = vacancyDto.salary;
        this.description = vacancyDto.description;
        this.contacts = vacancyDto.contacts;
        this.company = new CompanyEntity(vacancyDto.companyId);
    }

    public VacancyEntity() {}
}
