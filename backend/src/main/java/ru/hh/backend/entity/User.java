package ru.hh.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends Model {

    public User(String name, String userType) {
        this.name = name;
        this.userType = userType;
    }

    @Column(name = "user_name")

    private String name;

    @Column(name = "user_type")
    private String userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
