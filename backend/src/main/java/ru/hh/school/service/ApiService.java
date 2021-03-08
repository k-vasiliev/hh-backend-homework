package ru.hh.school.service;

import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.http.HhClient;
import ru.hh.school.util.EmployerMapper;
import ru.hh.school.util.IdParameterValidator;
import ru.hh.school.util.PaginationValidator;
import ru.hh.school.util.StringParameterFilter;

import java.util.List;

@Service
public class ApiService {

    private final HhClient hhClient;
    private final PaginationValidator paginationValidator;
    private final StringParameterFilter queryFilter;
    private final EmployerMapper employerMapper;
    private final IdParameterValidator idParameterValidator;

    public ApiService(HhClient hhClient, PaginationValidator paginationValidator, StringParameterFilter queryFilter, EmployerMapper employerMapper, IdParameterValidator idParameterValidator) {
        this.hhClient = hhClient;
        this.paginationValidator = paginationValidator;
        this.queryFilter = queryFilter;
        this.employerMapper = employerMapper;
        this.idParameterValidator = idParameterValidator;
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
        System.out.println("Employerid" + employerId);
        String dataFromApi = hhClient.makeGetRequest("employers/" + idParam, "").body();
        System.out.println();
        return employerMapper.mapDataFromApiById(dataFromApi);
    }

}
