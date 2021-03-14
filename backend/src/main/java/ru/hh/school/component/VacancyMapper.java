package ru.hh.school.component;

import org.jvnet.hk2.annotations.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.FavoriteVacancyDto;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.*;

import java.util.List;

@Service
public class VacancyMapper extends HhJsonParser {

    private final FileSettings fileSettings;

    public VacancyMapper(FileSettings fileSettings) {
        this.fileSettings = fileSettings;
    }

    public List<VacancyDto> mapListOfItemsFromApi(String vacancyData) {
        return super.mapListOfItemsFromApi(vacancyData, VacancyDto.class);
    }

    public VacancyDto mapSingleItemFromApiToDto(String vacancyData) {
        return super.mapSingleItemFromApi(vacancyData, VacancyDto.class);
    }

    public Vacancy mapSingleItemFromApiToEntity(String dataFromApi) {
        return mapSingleItemFromApi(dataFromApi, Vacancy.class);
    }

    public FavoriteVacancyDto mapEntityToDto(Vacancy vacancy) {
        FavoriteVacancyDto vacancyDto = objectMapper.convertValue(vacancy, FavoriteVacancyDto.class);
        Integer viewsCount = getAmendedVacancyViewsCount(vacancy);
        Popularity popularity = getVacancyPopularity(viewsCount);

        vacancyDto.setDateCreate(vacancy.getDateCreate());
        vacancyDto.setViewsCount(viewsCount);
        vacancyDto.setPopularity(popularity);
        return vacancyDto;
    }


    private Popularity getVacancyPopularity(Integer viewsCount) {
        return viewsCount >= fileSettings.getInteger("popularity.settings")
                ? Popularity.POPULAR : Popularity.REGULAR;
    }

    private Integer getAmendedVacancyViewsCount(Vacancy vacancy) {
        return vacancy.getViewsCount().getCounter() + 1;
    }

}
