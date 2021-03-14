package ru.hh.school.dto;

public class EmployerDetail {
    public Integer id;

    public String name;

    public String description;

    public Area area;

    public static EmployerDetail fromHHEmployer(ru.hh.school.hhapiclient.Employer hhEmployer) {
        EmployerDetail res = new EmployerDetail();
        res.name = hhEmployer.name;
        res.id = Integer.valueOf(hhEmployer.id);
        res.description = hhEmployer.description;
        Area resArea = new Area();
        resArea.id = Integer.valueOf(hhEmployer.area.id);
        resArea.name = hhEmployer.area.name;
        res.area = resArea;
        return res;
    }
}
