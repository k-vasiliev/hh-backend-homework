package ru.hh.school.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    @Column
    private LocalDate creation_time;

    @UpdateTimestamp
    @Column
    private LocalDate last_modify;

    @Column
    private String name;

    @Column
    private String type;

    public UserEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDate creation_time) {
        this.creation_time = creation_time;
    }

    public LocalDate getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(LocalDate last_modify) {
        this.last_modify = last_modify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
