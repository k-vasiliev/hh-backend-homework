package entity;

import dao.CompanyDao;
import jdk.jshell.spi.ExecutionControl;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resume")
public class ResumeEntity {
    @Id
    @GeneratedValue
    @Column(name = "resume_id")
    private Integer id;


    @Column(name = "experience")
    private String experience;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "title")
    private String title;


    @Column(name = "creation_time")
    private LocalDate created;

    @OneToOne(targetEntity = UsersEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity user;

    public String toString() {
        return "RESUME : " + id + " " + contacts + " " + experience + " " + user.getName() + " "+created.toString();
    }

    public String getTitle() {
        return title;
    }

    public UsersEntity getUser() {
        return user;
    }

    public LocalDate getCreated() {
        return created;
    }

}
