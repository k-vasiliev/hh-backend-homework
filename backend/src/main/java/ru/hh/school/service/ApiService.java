package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.http.HhClient;
import ru.hh.school.util.*;


@Service
public class ApiService {

    private final HhClient hhClient;
    private final PaginationValidator paginationValidator;
    private final IdParameterValidator idParameterValidator;

    public ApiService(HhClient hhClient, PaginationValidator paginationValidator, IdParameterValidator idParameterValidator) {
        this.hhClient = hhClient;
        this.paginationValidator = paginationValidator;
        this.idParameterValidator = idParameterValidator;
    }

    public String fetchEmployersFromApi(String query, Integer page, Integer perPage) {
        paginationValidator.validate(page, perPage);
        String requestQuery = "?text=" + query + "&page=" + page + "&per_page=" + perPage;
        return hhClient.makeGetRequest("employers", requestQuery).body();
    }

    public String fetchEmployersFromApiById(Integer employerId) {
        Integer idParam = idParameterValidator.validate(employerId);
        return hhClient.makeGetRequest("employers/" + idParam, "").body();
    }

    public String fetchVacanciesFromApi(String query, Integer page, Integer perPage) {
        paginationValidator.validate(page, perPage);
        String requestQuery = "?text=" + query + "&page=" + page + "&per_page=" + perPage;
        return hhClient.makeGetRequest("vacancies", requestQuery).body();
    }

    public String fetchVacanciesFromApiById(Integer vacancyId) {
        Integer idParam = idParameterValidator.validate(vacancyId);
        return hhClient.makeGetRequest("vacancies/" + idParam, "").body();
    }
}
