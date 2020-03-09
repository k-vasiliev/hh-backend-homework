package ru.hh.school.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends CommonDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "users_id", updatable = false)
    private Integer id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User (){
    }

    public User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }
}
