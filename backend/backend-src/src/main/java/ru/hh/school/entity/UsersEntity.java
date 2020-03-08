package ru.hh.school.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name")
    private  String name;

    @Column(name = "is_company")
    private  Boolean isCompany;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCompany() {
        return isCompany;
    }

    public void setCompany(Boolean company) {
        isCompany = company;
    }

    public UsersEntity(Integer id) {
        this.id = id;
    }

    public UsersEntity(String name, boolean isCompany) {
        this.name = name;
        this.isCompany = isCompany;
    }

    public UsersEntity() {}
}
