package ru.hh.school.dto;

import java.util.List;

public class VacancyItemsApiHh {

    @Override
    public String toString() {
        return "{" +
                "items=" + items +
                '}';
    }

    List<VacancyDto> items;

}
