package ru.hh.school.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import ru.hh.school.entity.Employer;

import javax.persistence.*;

@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Table(name = "employer_comment")
public class EmployerComment extends Comment {

    public EmployerComment() {}

    public EmployerComment(String comment) {
        super(comment);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @JsonIgnore
    private Employer employer;

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
