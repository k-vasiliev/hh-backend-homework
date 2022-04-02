package ru.hh.school.resource.dto;

public class EmployersResponseData {
    private String id; // id компании
    private String name; // название компании

    public EmployersResponseData() {
    }

    public EmployersResponseData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
