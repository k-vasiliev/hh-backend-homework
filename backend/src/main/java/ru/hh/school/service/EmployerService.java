package ru.hh.school.service;

import com.google.gson.Gson;
import org.apache.http.HttpHeaders;
import org.apache.http.client.utils.URIBuilder;
import org.eclipse.jetty.server.Response;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.EmployerByIdDto;
import ru.hh.school.dto.EmployerItemsApi;
import ru.hh.school.entity.Employer;
import ru.hh.school.exception.HhRequestException;
import ru.hh.school.mapper.EmployerMapper;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Singleton
public class EmployerService {

    private final EmployerDao employerDao;

    private final EmployerMapper employerMapper;

    public EmployerService(EmployerDao employerDao, EmployerMapper employerMapper) {
        this.employerDao = employerDao;
        this.employerMapper = employerMapper;
    }

    public EmployerItemsApi getEmployers(String page, String per_page, String query) throws HhRequestException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URIBuilder()
                            .setScheme("https")
                            .setHost("api.hh.ru")
                            .setPath("/employers")
                            .addParameter("text", query)
                            .addParameter("page", String.valueOf(page))
                            .addParameter("per_page", String.valueOf(per_page))
                            .build())
                    .timeout(Duration.ofSeconds(2))
                    .header(HttpHeaders.AUTHORIZATION,
                            "Bearer JOIN9M0LTBRLMF0S1JBLA2VUSFHAPSJF63PGT89P96D6HGNNHALD7QL2PSTKUD8P")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new HhRequestException(e.getMessage());
        }

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new HhRequestException(e.getMessage());
        }

        return new Gson().fromJson(response.body(), EmployerItemsApi.class);
    }

    public EmployerByIdDto get(String id) throws HhRequestException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URIBuilder()
                            .setScheme("https")
                            .setHost("api.hh.ru")
                            .setPath("/employers/" + id)
                            .build())
                    .timeout(Duration.ofSeconds(2))
                    .header(HttpHeaders.AUTHORIZATION,
                            "Bearer JOIN9M0LTBRLMF0S1JBLA2VUSFHAPSJF63PGT89P96D6HGNNHALD7QL2PSTKUD8P")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new HhRequestException(e.getMessage());
        }

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new HhRequestException(e.getMessage());
        }

        if (response.statusCode() != Response.SC_OK) {
            throw new HhRequestException("status code is "+ response.statusCode());
        }

        return new Gson().fromJson(response.body(), EmployerByIdDto.class);
    }

    @Transactional
    public Employer get(Integer employerId) {
        return employerDao.get(employerId).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Employer create(Employer employer) {
        return employerDao.create(employer);
    }

    @Transactional
    public Employer saveOrUpdate(Employer employer) {
        return employerDao.saveOrUpdate(employer);
    }

    @Transactional
    public void delete(Employer employer) {
        employerDao.delete(employer);
    }

    @Transactional
    public void update(Integer id) throws HhRequestException {
        if (id  == null) {
            throw new HhRequestException("employer id is empty");
        }

        EmployerByIdDto employer = this.get(String.valueOf(id));
        this.saveOrUpdate(employerMapper.map(employer));
    }
}
