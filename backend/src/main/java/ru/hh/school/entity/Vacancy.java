package ru.hh.school.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "vacancy")
public class Vacancy {

    public Vacancy() {}

    @Id
    private int id;

    @NotNull
    private String name;

    @CreationTimestamp
    @Column(name = "date_create")
    private LocalDate dateCreate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Area area;

    @Embedded
    private Salary salary;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "employer")
    private Employer employer;

    @OneToOne(mappedBy = "vacancy", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private VacancyCounter vacancyCounter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public VacancyCounter getViewsCount() {
        return vacancyCounter;
    }

    public void setViewsCount(VacancyCounter vacancyCounter) {
        this.vacancyCounter = vacancyCounter;
    }

    @Override
    public String toString() {
        return "Vacancy=" + id +
                ", name=" + name + '\n' +
                ", dateCreate=" + dateCreate + '\n' +
                ", salary=" + salary + '\n' +
                ", createdAt=" + createdAt + '\n' +
                ", employer=[id=" + employer.getId() + ", name=" + employer.getName() + "]\n" +
                ", area=" + area + '\n' +
                ", vacancyCounter=" + vacancyCounter + '\n' +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(id, vacancy.id);
    }

    @Override
    public int hashCode() { return 27; }

}
