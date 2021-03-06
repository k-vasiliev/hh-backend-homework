package ru.hh.school.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvnet.hk2.annotations.Service;
import ru.hh.school.exception.InvalidPaginationException;
import ru.hh.school.http.HhClient;

import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployerService {

    private final HhClient hhClient;
    private final PaginationFilter paginationFilter;
    private final QueryFilter queryFilter;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EmployerService(HhClient hhClient, PaginationFilter paginationFilter, QueryFilter queryFilter) {
        this.hhClient = hhClient;
        this.paginationFilter = paginationFilter;
        this.queryFilter = queryFilter;
    }

    public Response fetchListOfEmployersFromApi(String query, String page, String perPage) throws InvalidPaginationException {
        String textParam = queryFilter.filter(query);
        String pagination = paginationFilter.filter(page, perPage);
        String result = hhClient.makePaginatedRequest("employers", textParam + pagination).body();
        try {
            JsonNode rootNode = objectMapper.readTree(result);
            JsonNode items = rootNode.path("items");
            Iterator<JsonNode> elements = items.elements();
            Iterable<JsonNode> iterable = () -> elements;
            List<EmployerProxyDto> list = StreamSupport.stream(iterable.spliterator(), false)
                    .map(employer -> new EmployerProxyDto(employer.get("id").asInt(), employer.get("name").asText()))
                    .collect(Collectors.toList());
            list.forEach(System.out::println);
            while(elements.hasNext()){
                JsonNode phone = elements.next();
                System.out.println("-----");
                System.out.println("id = "+phone.get("id").asInt());
                System.out.println("name = "+phone.get("name").asText());
            }
            return Response.ok().entity(list).build();
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
            throw new InvalidPaginationException("For time");
        }
    }

}

class EmployerProxyDto {
    private int id;
    private String name;

    public EmployerProxyDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
