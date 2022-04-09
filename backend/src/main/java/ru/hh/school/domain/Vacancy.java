package ru.hh.school.domain;

import ru.hh.school.resource.dto.SalaryData;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vacancy")
public class Vacancy extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String name;

    @Embedded
    private SalaryData salary; // зарплата в том же формате, что в api hh.ru

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

//    @OneToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Long areaId;

    public Vacancy() {
    }

    public Vacancy(String name, SalaryData salary, LocalDateTime createdAt, Employer employer, Long areaId) {
        this.name = name;
        this.salary = salary;
        this.createdAt = createdAt;
        this.employer = employer;
        this.areaId = areaId;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SalaryData getSalary() {
        return salary;
    }

    public void setSalary(SalaryData salary) {
        this.salary = salary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", createdAt=" + createdAt +
                ", employer=" + employer +
                ", areaId=" + areaId +
                '}';
    }
}
