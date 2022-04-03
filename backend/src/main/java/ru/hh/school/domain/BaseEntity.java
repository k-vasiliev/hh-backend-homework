package ru.hh.school.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Temporal(TemporalType.TIMESTAMP)
////    @CreatedDate
//    @Column(name = "created")
//    private Date created;
//
//    @Temporal(TemporalType.TIMESTAMP)
////    @LastModifiedDate
//    @Column(name = "updated")
//    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
