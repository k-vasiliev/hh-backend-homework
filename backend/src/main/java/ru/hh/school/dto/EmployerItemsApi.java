package ru.hh.school.dto;

import ru.hh.school.entity.EmployerApi;

import java.util.List;

public class EmployerItemsApi {
    @Override
    public String toString() {
        return "EmployerItems{" +
                "items=" + items +
                '}';
    }

    List<EmployerApi> items;

    public List<EmployerApi> getItems() {
        return items;
    }
}
