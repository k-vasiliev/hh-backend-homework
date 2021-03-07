package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.exception.ApiRequestException;
import ru.hh.school.http.HhClient;
import ru.hh.school.util.EmployerMapper;

import java.util.List;

@Service
public class EmployerService {

    private final HhClient hhClient;
    private final PaginationFilter paginationFilter;
    private final QueryFilter queryFilter;
    private final EmployerMapper employerMapper;

    public EmployerService(HhClient hhClient, PaginationFilter paginationFilter, QueryFilter queryFilter, EmployerMapper employerMapper) {
        this.hhClient = hhClient;
        this.paginationFilter = paginationFilter;
        this.queryFilter = queryFilter;
        this.employerMapper = employerMapper;
    }

    public List<EmployerDto> fetchEmployersFromApi(String query, String page, String perPage) throws ApiRequestException {
        String textParam = queryFilter.filter(query);
        String paginationParam = paginationFilter.filter(page, perPage);
        String dataFromApi = hhClient.makeGetRequest("employers", textParam + paginationParam).body();
        return employerMapper.mapDataFromApi(dataFromApi);
    }
}
