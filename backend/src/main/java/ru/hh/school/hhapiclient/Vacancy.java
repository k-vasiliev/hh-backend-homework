package ru.hh.school.hhapiclient;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Vacancy {
    public String id;
    public String description;
    public String name;
    public Salary salary;
    public Area area;
    public Employer employer;
    public String created_at;
}
