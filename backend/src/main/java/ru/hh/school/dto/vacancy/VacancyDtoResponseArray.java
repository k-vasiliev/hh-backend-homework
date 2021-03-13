package ru.hh.school.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyDtoResponseArray {
    private VacancyDtoResponse[] items;

    public VacancyDtoResponseArray(VacancyDtoResponse[] items) {
        this.items = items;
    }

    public VacancyDtoResponse[] getItems() {
        return items;
    }

    public VacancyDtoResponseArray() {
    }

    @Override
    public String toString() {
        return "AbcDto{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
