package ru.hh.school.entity;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company extends CommonDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "company_id",  updatable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "users_id")
    private User user;

    @Column (name = "title", nullable = false)
    private String title;

    public Company (){
    }

    public Company(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }
}
