package ru.hh.school.dto;

public class EmployerDetailed extends Employer {

    private String description;

    private Area area;

    public EmployerDetailed() {
    }

    public EmployerDetailed(Integer id, String name, String description, Area area) {
        super(id, name);
        this.description = description;
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

}
