package entity;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "vacancy_response")
public class VacancyResponseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name ="vacancy_response_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "vacancy_id", referencedColumnName = "vacancy_id")
    private VacancyEntity vacancy;

    @OneToOne
    @JoinColumn(name = "resume_id", referencedColumnName = "resume_id")
    private ResumeEntity resume;

    public VacancyEntity getVacancy() {
        return vacancy;
    }

    public Integer getId() {
        return id;
    }

    public ResumeEntity getResume() {
        return resume;
    }

    public  VacancyResponseEntity(Integer resumeId, Integer vacancyId) {
        vacancy = new VacancyEntity(vacancyId);
        resume  = new ResumeEntity(resumeId);
    }

    public VacancyResponseEntity() {}
}
