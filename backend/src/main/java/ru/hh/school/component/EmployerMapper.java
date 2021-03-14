package ru.hh.school.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvnet.hk2.annotations.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.*;

import javax.ws.rs.ServerErrorException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployerMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AreaMapper areaMapper;
    private final AreaDao areaDao;
    private final FileSettings fileSettings;

    public EmployerMapper(AreaMapper areaMapper, AreaDao areaDao, FileSettings fileSettings) {
        this.areaMapper = areaMapper;
        this.areaDao = areaDao;
        this.fileSettings = fileSettings;
    }

    public List<EmployerDto> mapDataFromApi(String employersData) {
        try {
            JsonNode rootNode = objectMapper.readTree(employersData);
            JsonNode items = rootNode.path("items");
            Iterator<JsonNode> elements = items.elements();
            Iterable<JsonNode> iterable = () -> elements;
            List<EmployerDto> employers = StreamSupport.stream(iterable.spliterator(), false)
                    .map(employer -> convertJsonNodeToValue(employer, EmployerDto.class))
                    .collect(Collectors.toList());
            return employers;
        } catch (JsonProcessingException e) {
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

    public EmployerDtoById mapDataFromApiById(String employerData) {
        try {
            EmployerDtoById employer = objectMapper.readValue(employerData, EmployerDtoById.class);
            return employer;
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(500);
        }
    }

    public FavoriteEmployerDto mapDataFromDatabase(Employer employer) {
        AreaDto areaDto = areaMapper.mapToDto(employer.getArea());
        String comment = employer.getComment().getComment();
        Integer viewsCount = employer.getViewsCount().getCounter() + 1;
        Popularity popularity = viewsCount >= fileSettings.getInteger("popularity.settings")
                ? Popularity.POPULAR : Popularity.REGULAR;
        return new FavoriteEmployerDto(
                employer.getId(),
                employer.getName(),
                employer.getDescription(),
                comment,
                employer.getDateCreate(),
                viewsCount,
                popularity,
                areaDto
        );
    }

    private Employer copyDtoFieldsToEntity(Employer employer, EmployerDtoById employerDto) {
        employer.setId(employerDto.getId());
        employer.setName(employerDto.getName());
        employer.setDescription(employerDto.getDescription());
        return employer;
    }

    public Employer mapEmployerDtoToEntity(EmployerDtoById employerDto, String comment) {
        Employer employer = new Employer();
        Area area = areaMapper.mapToEntity(employerDto.getArea());
        EmployerComment newComment = new EmployerComment(comment);
        EmployerCounter counter = new EmployerCounter();
        newComment.setEmployer(employer);
        counter.setEmployer(employer);
        employer.setArea(area);
        employer.setComment(newComment);
        employer.setViewsCount(counter);
        return copyDtoFieldsToEntity(employer, employerDto);
    }

    public Employer refreshEmployer(Employer employer, EmployerDtoById employerDto) {
        AreaDto areaDto = employerDto.getArea();
        Area area = areaDao.get(Area.class, areaDto.getId()).orElse(areaMapper.mapToEntity(areaDto));
        employer.setArea(area);
        return copyDtoFieldsToEntity(employer, employerDto);
    }

}
