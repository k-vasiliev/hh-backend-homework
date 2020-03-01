package entity;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "vacancy_response")
public class VacancyResponseEntity {
    @Id
    @GeneratedValue
    @Column(name ="vacancy_response_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "vacancy_id", referencedColumnName = "vacancy_id")
    private VacancyEntity vacancy;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UsersEntity user;
}
