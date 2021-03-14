package ru.hh.school.entity;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favourite_employers")
public class FavouriteEmployer {
    public FavouriteEmployer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String comment;

    private String description;

    @Column(name = "views_count")
    private Integer viewsCount;

    private LocalDateTime date_create;

    private Integer hh_id;

    @OneToOne
    @JoinColumn(name = "area_id")
    private Area area;

    public Integer getId() {return id;}

    public void setId(Integer newId) {this.id = newId;}

    public String getName() {return name;}

    public void setName(String newName) {this.name = newName;}

    public String getComment() {return comment;}

    public void setComment(String newComment) {this.comment = newComment;}

    public String getDescription() {return description;}

    public void setDescription(String newDescription) {this.description = newDescription;}

    public Integer getViewsCount() {return viewsCount;}

    public void setViewsCount(Integer newViewsCount) {this.viewsCount = newViewsCount;}

    public LocalDateTime getDateCreate() {return date_create;}

    public void setDateCreate(LocalDateTime dt) {this.date_create = dt;}

    public Area getArea() {return area;}

    public void setArea(Area newArea) {this.area = newArea;}

    public Integer getHHId() {
        return hh_id;
    }

    public void setHHId(Integer newHHId) {
        this.hh_id = newHHId;
    }
}
