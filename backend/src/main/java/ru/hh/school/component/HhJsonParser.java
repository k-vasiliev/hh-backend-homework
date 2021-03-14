package ru.hh.school.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ServerErrorException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class HhJsonParser {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected  <T> List<T> mapListOfItemsFromApi(String apiData, Class<T> clz) {
        try {
            JsonNode rootNode = objectMapper.readTree(apiData);
            JsonNode items = rootNode.path("items");
            Iterator<JsonNode> elements = items.elements();
            Iterable<JsonNode> iterable = () -> elements;
            List<T> employers = StreamSupport.stream(iterable.spliterator(), false)
                    .map(employer -> convertJsonNodeToValue(employer, clz))
                    .collect(Collectors.toList());
            return employers;
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(500);
        }
    }

    protected <T> T mapSingleItemFromApi(String apiData, Class<T> clz) {
        try {
            return objectMapper.readValue(apiData, clz);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(500);
        }
    }

    private <T> T convertJsonNodeToValue(JsonNode node, Class<T> clz) {
        try {
            return objectMapper.treeToValue(node, clz);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(500);
        }
    }

}
