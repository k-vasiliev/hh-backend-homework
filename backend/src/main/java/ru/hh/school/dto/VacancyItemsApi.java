package ru.hh.school.dto;

import java.util.List;

public class VacancyItemsApi {
    @Override
    public String toString() {
        return "VacancyItems{" +
                "items=" + items +
                '}';
    }

    List<VacancyDto> items;

    public List<VacancyDto> getItems() {
        return items;
    }
}
