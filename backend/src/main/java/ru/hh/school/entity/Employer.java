package ru.hh.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import ru.hh.school.entity.comment.EmployerComment;
import ru.hh.school.entity.counter.EmployerCounter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employer {

    public Employer() {
    }

    @Id
    private int id;

    @NotNull
    @Column(unique = true)
    private String name;

    @CreationTimestamp
    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Area area;

    @OneToOne(mappedBy = "employer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonUnwrapped
    private EmployerComment comment;

    @OneToOne(mappedBy = "employer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonUnwrapped
    private EmployerCounter employerCounter;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Vacancy> vacancies;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public EmployerComment getComment() {
        return comment;
    }

    public void setComment(EmployerComment comment) {
        this.comment = comment;
    }

    public EmployerCounter getViewsCount() {
        return employerCounter;
    }

    public void setViewsCount(EmployerCounter employerCounter) {
        this.employerCounter = employerCounter;
    }

    public Set<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }


    @Override
    public String toString() {
        return "Employer[id=" + id +
                ", name=" + name + '\n' +
                ", dateCreate=" + dateCreate + '\n' +
                ", area=" + area + '\n' +
                ", employerCounter=" + employerCounter.getCounter() +
                ", comment=" + comment.getComment() +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return Objects.equals(id, employer.id) && Objects.equals(name, employer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
