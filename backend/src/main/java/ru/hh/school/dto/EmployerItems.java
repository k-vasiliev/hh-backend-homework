package ru.hh.school.dto;

import java.util.List;

public class EmployerItems {
    @Override
    public String toString() {
        return "EmployerItems{" +
                "items=" + items +
                '}';
    }

    List<EmployerDto> items;

    public List<EmployerDto> getItems() {
        return items;
    }
}
