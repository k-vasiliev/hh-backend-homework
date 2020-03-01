package entity;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "title")
    private String companyName;

    @OneToOne(targetEntity = UsersEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity user;

    public String getCompanyName() {
        return companyName;
    }

}
