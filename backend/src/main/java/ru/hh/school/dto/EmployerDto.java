package ru.hh.school.dto;

public class EmployerDto {

    private int id;
    private String name;

    public EmployerDto() {}

    public EmployerDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmployerDto=[id=" + id + ", name=" + name + ']';
    }

}
