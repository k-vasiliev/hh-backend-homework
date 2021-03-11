package ru.hh.school.dto;

import java.util.List;

public class EmployerItemsApiHh {
    @Override
    public String toString() {
        return "{" +
                "items=" + items +
                '}';
    }

    List<EmployerApiHh> items;

}
