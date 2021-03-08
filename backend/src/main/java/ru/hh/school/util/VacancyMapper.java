package ru.hh.school.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvnet.hk2.annotations.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.CommentDao;
import ru.hh.school.dao.ViewsCounterDao;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.Vacancy;

import javax.ws.rs.ServerErrorException;
import java.time.OffsetDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VacancyMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AreaMapper areaMapper;
    private final AreaDao areaDao;
    private final CommentDao commentDao;
    private final ViewsCounterDao viewsCounterDao;
    private final FileSettings fileSettings;

    public VacancyMapper(AreaMapper areaMapper, AreaDao areaDao, CommentDao commentDao, ViewsCounterDao viewsCounterDao, FileSettings fileSettings) {
        this.areaMapper = areaMapper;
        this.areaDao = areaDao;
        this.commentDao = commentDao;
        this.viewsCounterDao = viewsCounterDao;
        this.fileSettings = fileSettings;
    }

    public List<VacancyDto> mapDataFromApi(String vacancyData) {
        try {
            JsonNode rootNode = objectMapper.readTree(vacancyData);
            JsonNode items = rootNode.path("items");
            Iterator<JsonNode> elements = items.elements();
            Iterable<JsonNode> iterable = () -> elements;
            List<VacancyDto> vacancies = StreamSupport.stream(iterable.spliterator(), false)
                    .map(vacancy -> convertJsonNodeToValue(vacancy, VacancyDto.class))
                    .collect(Collectors.toList());
            return vacancies;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new ServerErrorException(500);
        }
    }

    private <T> T convertJsonNodeToValue(JsonNode node, Class<T> clz) {
        try {
            return objectMapper.treeToValue(node, clz);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            throw new ServerErrorException(500);
        }
    }

    public VacancyDto mapDataFromApiById(String dataFromApi) {
        try {
            return objectMapper.readValue(dataFromApi, VacancyDto.class);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(500);
        }
    }

    /*public Vacancy mapVacancyDtoToEntity(VacancyDto vacancyDto) {
        Vacancy vacancy = new Vacancy();
    }*/

}
