package ru.hh.school.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.exception.ApiDataParsingException;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployerMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<EmployerDto> mapDataFromApi(String employersData) throws ApiDataParsingException {
        try {
            JsonNode rootNode = objectMapper.readTree(employersData);
            JsonNode items = rootNode.path("items");
            Iterator<JsonNode> elements = items.elements();
            Iterable<JsonNode> iterable = () -> elements;
            List<EmployerDto> employers = StreamSupport.stream(iterable.spliterator(), false)
                    .map(employer -> new EmployerDto(employer.get("id").asInt(), employer.get("name").asText()))
                    .collect(Collectors.toList());
            return employers;
        } catch (JsonProcessingException e) {
            throw new ApiDataParsingException("Error while parsing json for employers. Message: " + e.getMessage());
        }
    }

}
