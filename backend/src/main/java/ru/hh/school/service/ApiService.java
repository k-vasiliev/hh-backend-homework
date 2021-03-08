package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.http.HhClient;
import ru.hh.school.util.*;

import java.util.List;

@Service
public class ApiService {

    private final HhClient hhClient;
    private final PaginationValidator paginationValidator;
    private final StringParameterFilter queryFilter;
    private final EmployerMapper employerMapper;
    private final IdParameterValidator idParameterValidator;
    private final VacancyMapper vacancyMapper;

    public ApiService(HhClient hhClient, PaginationValidator paginationValidator, StringParameterFilter queryFilter, EmployerMapper employerMapper, IdParameterValidator idParameterValidator, VacancyMapper vacancyMapper) {
        this.hhClient = hhClient;
        this.paginationValidator = paginationValidator;
        this.queryFilter = queryFilter;
        this.employerMapper = employerMapper;
        this.idParameterValidator = idParameterValidator;
        this.vacancyMapper = vacancyMapper;
    }

    public List<EmployerDto> fetchEmployersFromApi(String query, Integer page, Integer perPage) {
        String textParam = "?text=" + queryFilter.filter(query);
        paginationValidator.validate(page, perPage);
        String paginationParam = "&page=" + page + "&per_page=" + perPage;
        String dataFromApi = hhClient.makeGetRequest("employers", textParam + paginationParam).body();
        return employerMapper.mapDataFromApi(dataFromApi);
    }

    public EmployerDtoById fetchEmployersFromApiById(Integer employerId) {
        Integer idParam = idParameterValidator.validate(employerId);
        String dataFromApi = hhClient.makeGetRequest("employers/" + idParam, "").body();
        return employerMapper.mapDataFromApiById(dataFromApi);
    }

    public List<VacancyDto> fetchVacanciesFromApi(String query, Integer page, Integer perPage) {
        String textParam = "?text=" + queryFilter.filter(query);
        paginationValidator.validate(page, perPage);
        String paginationParam = "&page=" + page + "&per_page=" + perPage;
        String dataFromApi = hhClient.makeGetRequest("vacancies", textParam + paginationParam).body();
        return vacancyMapper.mapDataFromApi(dataFromApi);
    }

    public VacancyDto fetchVacanciesFromApiById(Integer vacancyId) {
        Integer idParam = idParameterValidator.validate(vacancyId);
        String dataFromApi = hhClient.makeGetRequest("vacancies/" + idParam, "").body();
        return vacancyMapper.mapDataFromApiById(dataFromApi);
    }
}
