package ru.hh.school.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
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
    @JoinColumn(name = "area")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Area area;

    @OneToOne
    @JoinColumn(name = "comment")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Comment comment;

    @OneToOne
    @JoinColumn(name = "views_count")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private EmployerCounter viewsCount;

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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public EmployerCounter getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(EmployerCounter viewsCount) {
        this.viewsCount = viewsCount;
    }

    @Override
    public String toString() {
        return "Employer[id=" + id +
                    ", name=" + name + '\n' +
                    ", dateCreate=" + dateCreate + '\n' +
                    ", area=" + area + '\n' +
                    ", viewsCount=" + viewsCount +
                    ", comment=" + comment +
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
