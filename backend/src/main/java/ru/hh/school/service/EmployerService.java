package ru.hh.school.service;

import ru.hh.school.resource.dto.HHEmployerResponseDto;
import ru.hh.school.resource.dto.HHEmployersResponseDto;

public interface EmployerService {
    HHEmployersResponseDto getHHEmployers(String query, Integer page, Integer perPage);

    HHEmployerResponseDto getHHEmployerById(Long id);
}