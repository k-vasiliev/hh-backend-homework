package ru.hh.school.http;

import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HhClient {

    private final String HH_BASE_URL = "https://api.hh.ru/";

    private HttpRequest createGetRequest(String url) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI(HH_BASE_URL + url))
                    .GET()
                    .build();
        } catch (URISyntaxException e) { return null; }
    }

    private HttpResponse<String> executeRequest(HttpRequest request) {
        try {
            return HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public HttpResponse<String> makeGetRequest(String url, String query) {
        HttpResponse<String> response = executeRequest(createGetRequest(url + query));
        if (response.statusCode() == 200) {
            return response;
        }
        throw new WebApplicationException("Something went wrong", response.statusCode());
    }

}
