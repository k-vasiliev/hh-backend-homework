package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "favourite_vacancies")
public class FavouriteVacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id")
    private Integer id;

    /*
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
     */

    private Integer employer_id;

    private String employer_name;

    @OneToOne
    @JoinColumn(name = "area_id")
    private Area area;

    private String name;

    @Column(name = "compensation_from")
    private Integer compensationFrom;

    @Column(name = "compensation_to")
    private Integer  compensationTo;

    @Column(name = "compensation_gross")
    private Boolean compensationGross;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "views_count")
    private Integer viewsCount;

    private String comment;

    private Integer hh_id;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    public FavouriteVacancy() {
    }

    /*
    public Vacancy(Employer employer) {
        this.employer = employer;
    }
     */

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployerId() {
        return employer_id;
    }

    public void setEmployerId(Integer employer_id) {
        this.employer_id = employer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompensationFrom(Integer compensationFrom) {
        this.compensationFrom = compensationFrom;
    }

    public void setCompensationTo(Integer compensationTo) {
        this.compensationTo = compensationTo;
    }

    public void setCompensationGross(Boolean newCompensationGross) {
        this.compensationGross = newCompensationGross;
    }

    public Integer getCompensationTo() {
        return compensationTo;
    }

    public Integer getCompensationFrom() {
        return compensationFrom;
    }

    public Boolean getCompensationGross() {
        return compensationGross;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime dt) {
        this.creationTime = dt;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime dt) {
        this.created_at = dt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String newComment) {
        this.comment = newComment;
    }

    public String getEmployerName() {
        return employer_name;
    }

    public void setEmployerName(String name) {
        this.employer_name = name;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer newViewsCount) {
        this.viewsCount = newViewsCount;
    }

    public Integer getHHId() {
        return hh_id;
    }

    public void setHHId(Integer newHHId) {
        this.hh_id = newHHId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouriteVacancy vacancy = (FavouriteVacancy) o;
        return Objects.equals(id, vacancy.id);
    }

    @Override
    public int hashCode() {
        return 17;
    }

}
