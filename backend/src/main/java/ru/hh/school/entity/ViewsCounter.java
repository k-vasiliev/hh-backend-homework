package ru.hh.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ViewsCounter {

    public ViewsCounter() {}

    private int id;

    @Column(name = "views_counter")
    private Integer counter;

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
}
