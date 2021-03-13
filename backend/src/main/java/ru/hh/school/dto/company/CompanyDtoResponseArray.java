package ru.hh.school.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDtoResponseArray {
    private CompanyDtoResponse[] items;

    public CompanyDtoResponseArray(CompanyDtoResponse[] items) {
        this.items = items;
    }

    public CompanyDtoResponse[] getItems() {
        return items;
    }

    public CompanyDtoResponseArray() {
    }

    @Override
    public String toString() {
        return "AbcDto{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
