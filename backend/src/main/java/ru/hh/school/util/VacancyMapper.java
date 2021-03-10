package ru.hh.school.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvnet.hk2.annotations.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.*;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.FavoriteVacancyDto;
import ru.hh.school.dto.SalaryDto;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.*;
import ru.hh.school.service.EmployerService;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VacancyMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final EmployerService employerService;
    private final VacancyDao vacancyDao;
    private final AreaDao areaDao;
    private final AreaMapper areaMapper;
    private final CommentDao commentDao;
    private final ViewsCounterDao viewsCounterDao;
    private final FileSettings fileSettings;
    private final GenericDao genericDao;

    public VacancyMapper(EmployerService employerService, VacancyDao vacancyDao, AreaDao areaDao, AreaMapper areaMapper, CommentDao commentDao, ViewsCounterDao viewsCounterDao, FileSettings fileSettings, GenericDao genericDao) {
        this.employerService = employerService;
        this.vacancyDao = vacancyDao;
        this.areaDao = areaDao;
        this.areaMapper = areaMapper;
        this.commentDao = commentDao;
        this.viewsCounterDao = viewsCounterDao;
        this.fileSettings = fileSettings;
        this.genericDao = genericDao;
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
            throw new ServerErrorException(500);
        }
    }

    public VacancyDto mapDataFromApiById(String vacancyData) {
        try {
            return objectMapper.readValue(vacancyData, VacancyDto.class);
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

    private <T> T mapStringData(String data, Class<T> clz) {
        try {
            return objectMapper.readValue(data, clz);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException(500);
        }
    }

    public Vacancy mapApiDataToEntityAndSave(String dataFromApi, String comment) {
        Vacancy vacancy = mapStringData(dataFromApi, Vacancy.class);

        Employer vacancyEmployer = vacancy.getEmployer();
        vacancyEmployer = getOrCreateVacancyEmployer(vacancyEmployer.getId());

        Area vacancyArea = vacancy.getArea();
        vacancyArea = areaDao.get(Area.class, vacancyArea.getId()).orElse(vacancyArea);

        VacancyCounter counter = new VacancyCounter();
        counter.setVacancy(vacancy);

        VacancyComment vacancyComment = new VacancyComment(comment);
        vacancyComment.setVacancy(vacancy);

        vacancy.setEmployer(vacancyEmployer);
        vacancy.setArea(vacancyArea);
        vacancy.setViewsCount(counter);
        vacancy.setComment(vacancyComment);
        vacancyDao.save(vacancy);
        return vacancy;
    }

    private Employer getOrCreateVacancyEmployer(Integer employerId) {
        try {
            return employerService.getEmployer(employerId);
        } catch(NotFoundException e) {
            return employerService.addNewEmployerToFavorites(employerId, "");
        }
    }

    public FavoriteVacancyDto mapEntityToDto(Vacancy vacancy) {
        Integer viewsCount = vacancy.getViewsCount().getCounter() + 1;
        Popularity popularity = viewsCount >= fileSettings.getInteger("popularity.settings")
                ? Popularity.POPULAR : Popularity.REGULAR;
        System.out.println(vacancy);
        FavoriteVacancyDto vacancyDto = objectMapper.convertValue(vacancy, FavoriteVacancyDto.class);
        vacancyDto.setDateCreate(vacancy.getDateCreate());
        vacancyDto.setViewsCount(viewsCount);
        vacancyDto.setPopularity(popularity);
        return vacancyDto;
    }

    public Vacancy refreshVacancy(Vacancy vacancy, VacancyDto vacancyDto) {
        Integer employerId = vacancyDto.getEmployer().getId();
        Employer employer = refreshOrCreateEmployer(employerId);

        AreaDto areaDto = vacancyDto.getArea();
        Area area = areaDao.get(Area.class, areaDto.getId()).orElse(areaMapper.mapToEntity(areaDto));

        Salary salary = objectMapper.convertValue(vacancyDto.getSalary(), Salary.class);

        vacancy.setArea(area);
        vacancy.setEmployer(employer);
        vacancy.setSalary(salary);
        vacancy.setCreatedAt(vacancyDto.getCreatedAt());
        vacancy.setName(vacancyDto.getName());
        return vacancy;
    }

    private Employer refreshOrCreateEmployer(Integer employerId) {
        try {
            return employerService.refresh(employerId);
        } catch (NotFoundException e) {
            return employerService.addNewEmployerToFavorites(employerId, "");
        }
    }

}
