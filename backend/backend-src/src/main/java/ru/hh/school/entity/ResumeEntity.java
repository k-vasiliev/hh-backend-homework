package ru.hh.school.entity;

import ru.hh.school.dto.NewResumeDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resume")
public class ResumeEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Integer id;


    @Column(name = "experience")
    private String experience;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "title")
    private String title;


    @Column(name = "creation_time", insertable =  false)
    private LocalDate created;

    @OneToOne(targetEntity = UsersEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity user;

    public String getTitle() {
        return title;
    }

    public UsersEntity getUser() {
        return user;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Integer getId() {
        return id;
    }

    public ResumeEntity(Integer resumeId) {
        this.id = resumeId;
    }

    public ResumeEntity(NewResumeDto resumeDto) {
        contacts = resumeDto.getContacts();
        title = resumeDto.getTitle();
        experience = resumeDto.getExperience();
        user = new UsersEntity(resumeDto.getUserId());
    }

    public ResumeEntity() {}

}
