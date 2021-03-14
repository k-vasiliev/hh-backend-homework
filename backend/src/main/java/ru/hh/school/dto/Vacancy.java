package ru.hh.school.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vacancy {
    public Integer id;

    public String name;

    public Area area;

    public Salary salary;

    public String created_at;

    public Employer employer;

    public static Vacancy fromHHVacancy(ru.hh.school.hhapiclient.Vacancy hhVacancy) {
        Vacancy res = new Vacancy();
        res.name = hhVacancy.name;
        res.id = Integer.valueOf(hhVacancy.id);

        // check datetime validity
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        LocalDateTime dateTime = LocalDateTime.from(f.parse(hhVacancy.created_at));
        res.created_at = hhVacancy.created_at;
        Area resArea = new Area();
        resArea.id = Integer.valueOf(hhVacancy.area.id);
        resArea.name = hhVacancy.area.name;
        res.area = resArea;
        Employer resEmployer = new Employer();
        resEmployer.name = hhVacancy.employer.name;
        resEmployer.id = Integer.valueOf(hhVacancy.employer.id);
        res.employer = resEmployer;
        if (hhVacancy.salary == null) {
            res.salary = null;
        } else {
            Salary resSalary = new Salary();
            resSalary.from = hhVacancy.salary.from;
            resSalary.to = hhVacancy.salary.to;
            resSalary.gross = hhVacancy.salary.gross;
            res.salary = resSalary;
        }
        return res;
    }
}
