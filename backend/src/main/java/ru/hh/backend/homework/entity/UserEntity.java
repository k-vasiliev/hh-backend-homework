package ru.hh.backend.homework.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.hh.backend.homework.enums.UserType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @UpdateTimestamp
    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @Column(name = "name")
    private String name;

    @Column(name = "user_type")
    private UserType userType;

    public UserEntity() {
    }

    public UserEntity(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    public UserEntity(Integer userId, LocalDate creationDate, LocalDate modificationDate, String name, UserType userType) {
        this.userId = userId;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.name = name;
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return userId.equals(userEntity.userId) &&
                creationDate.equals(userEntity.creationDate) &&
                modificationDate.equals(userEntity.modificationDate) &&
                name.equals(userEntity.name) &&
                userType.equals(userEntity.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, creationDate, modificationDate, name, userType);
    }
}
