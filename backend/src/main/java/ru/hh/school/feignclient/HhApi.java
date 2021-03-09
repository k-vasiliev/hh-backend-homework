package ru.hh.school.feignclient;

import feign.Param;
import feign.RequestLine;

public interface HhApi {

    public static final String API_URL = "https://api.hh.ru/";

    public static final String PAGINATION_PARAMS = "page={page}&per_page={per_page}&";

    @RequestLine("GET /employers?text={text}&" + PAGINATION_PARAMS)
    String getEmployers(
        @Param("text") String query,
        @Param("page") Integer page,
        @Param("per_page") Integer perPage);

    @RequestLine("GET /employers/{employer_id}")
    String getEmployer(@Param("employer_id") Integer employerId);

    @RequestLine("GET /vacancies?text={text}&" + PAGINATION_PARAMS)
    String getVacancies(
        @Param("text") String query,
        @Param("page") Integer page,
        @Param("per_page") Integer perPage);

    @RequestLine("GET /vacancies/{vacancy_id}")
    String getVacancy(@Param("vacancy_id") Integer employerId);
}
