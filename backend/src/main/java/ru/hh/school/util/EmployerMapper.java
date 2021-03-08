package ru.hh.school.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvnet.hk2.annotations.Service;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;

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

    public EmployerMapper(AreaMapper areaMapper, AreaDao areaDao) {
        this.areaMapper = areaMapper;
        this.areaDao = areaDao;
    }

    public List<EmployerDto> mapDataFromApi(String employersData) {
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
            throw new ServerErrorException(500);
        }
    }

    public EmployerDtoById mapDataFromApiById(String employerData) {
        try {
            EmployerDtoById employer = objectMapper.readValue(employerData, EmployerDtoById.class);
            System.out.println(employer);
            return employer;
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(500);
        }
    }

    public FavoriteEmployerDto mapDataFromDatabase(Employer employer) {
        AreaDto areaDto = areaMapper.mapToDto(employer.getArea());
        return new FavoriteEmployerDto(
                employer.getId(),
                employer.getName(),
                employer.getDescription(),
                employer.getComment(),
                employer.getDateCreate(),
                employer.getPopularity(),
                employer.getViewsCount(),
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
        employer.setArea(area);
        employer.setComment(comment);
        return copyDtoFieldsToEntity(employer, employerDto);
    }

    public Employer refreshEmployer(Employer employer, EmployerDtoById employerDto) {
        AreaDto areaDto = employerDto.getArea();
        Area area = areaDao.get(Area.class, areaDto.getId()).orElse(areaMapper.mapToEntity(areaDto));
        area.setName(areaDto.getName());
        employer.setArea(area);
        employer.setComment(employer.getComment());
        return copyDtoFieldsToEntity(employer, employerDto);
    }

}
