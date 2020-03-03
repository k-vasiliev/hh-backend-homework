package entity;

import jdk.jshell.spi.ExecutionControl;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "title")
    private String companyName;

    @OneToOne(targetEntity = UsersEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity user;

    public CompanyEntity() {};
    public CompanyEntity(Integer id) { this.companyId = id; }
    public CompanyEntity(String title, Integer userId) {
        user = new UsersEntity(userId);
        companyName = title;
    };

    public String getCompanyName() {
        return companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }



}
