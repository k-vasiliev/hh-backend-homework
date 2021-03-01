package ru.hh.school.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employer")
public class Employer {

    public Employer() {}

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

    @ManyToOne
    private Area area;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(columnDefinition = "integer default 0")
    private int popularity;

    @Column(name = "views_count", columnDefinition = "integer default 0")
    private int viewsCount;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    @Override
    public String toString() {
        return "Employer[id=" + id +
                    ", name=" + name + '\n' +
                    ", dateCreate=" + dateCreate + '\n' +
                    ", area=" + area + '\n' +
                    ", popularity=" + popularity + '\n' +
                    ", viewsCount=" + viewsCount +
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
    public int hashCode() { return Objects.hash(name); }

}
