package ru.hh.school.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvnet.hk2.annotations.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.*;
import ru.hh.school.dto.AreaDto;
import ru.hh.school.dto.FavoriteVacancyDto;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.*;
import ru.hh.school.entity.comment.VacancyComment;
import ru.hh.school.entity.counter.VacancyCounter;
import ru.hh.school.service.EmployerService;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import java.util.List;

@Service
public class VacancyMapper extends HhJsonParser {

    private final EmployerService employerService;
    private final VacancyDao vacancyDao;
    private final AreaDao areaDao;
    private final AreaMapper areaMapper;
    private final FileSettings fileSettings;

    public VacancyMapper(EmployerService employerService, VacancyDao vacancyDao, AreaDao areaDao, AreaMapper areaMapper, FileSettings fileSettings) {
        this.employerService = employerService;
        this.vacancyDao = vacancyDao;
        this.areaDao = areaDao;
        this.areaMapper = areaMapper;
        this.fileSettings = fileSettings;
    }

    public List<VacancyDto> mapDataFromApi(String vacancyData) {
        return super.mapDataFromApi(vacancyData, VacancyDto.class);
    }

    public VacancyDto mapDataFromApiById(String vacancyData) {
        try {
            return objectMapper.readValue(vacancyData, VacancyDto.class);
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
