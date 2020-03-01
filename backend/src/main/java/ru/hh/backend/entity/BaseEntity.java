package ru.hh.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "creation_date")
    protected LocalDate creationDate;

    @Column(name = "last_update_date")
    protected LocalDate lastUpdateDate;

    public BaseEntity() {
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
