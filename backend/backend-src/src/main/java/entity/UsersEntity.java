package entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue
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

    public String toString() {
        return id.toString()+" "+isCompany.toString() +" "+ name;
    }
}
