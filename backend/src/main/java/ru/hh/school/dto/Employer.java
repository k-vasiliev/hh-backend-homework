package ru.hh.school.dto;

public class Employer {
    public Integer id;

    public String name;

    public static Employer fromHHEmployer(ru.hh.school.hhapiclient.Employer hhEmployer) {
        Employer res = new Employer();
        res.name = hhEmployer.name;
        res.id = Integer.valueOf(hhEmployer.id);
        return res;
    }
}
