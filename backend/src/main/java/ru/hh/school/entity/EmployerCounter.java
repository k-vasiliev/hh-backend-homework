package ru.hh.school.entity;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Table(name = "employer_counter")
public class EmployerCounter {

    public EmployerCounter() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "integer default 0")
    private Integer counter;

    @OneToOne(mappedBy = "employer_counter")

    private Employer employer;

    @Version
    private Integer version;

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ViewsCounter[" +
                "id=" + id +
                ", counter=" + counter +
                ", version=" + version +
                ']';
    }
}
