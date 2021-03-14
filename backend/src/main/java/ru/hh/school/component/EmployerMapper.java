package ru.hh.school.component;

import org.jvnet.hk2.annotations.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.EmployerDtoById;
import ru.hh.school.dto.FavoriteEmployerDto;
import ru.hh.school.entity.*;

import java.util.List;

@Service
public class EmployerMapper extends HhJsonParser {

    private final FileSettings fileSettings;

    public EmployerMapper(FileSettings fileSettings) {
        this.fileSettings = fileSettings;
    }

    public List<EmployerDto> mapListOfItemsFromApi(String employersData) {
        return super.mapListOfItemsFromApi(employersData, EmployerDto.class);
    }

    public EmployerDtoById mapSingleItemFromApiToDto(String employerData) {
        return super.mapSingleItemFromApi(employerData, EmployerDtoById.class);
    }

    public Employer mapSingleItemFromApiToEntity(String employerData) {
        return super.mapSingleItemFromApi(employerData, Employer.class);
    }

    public FavoriteEmployerDto mapEntityToDto(Employer employer) {
        FavoriteEmployerDto employerDto = objectMapper.convertValue(employer, FavoriteEmployerDto.class);
        Integer viewsCount = getAmendedEmployerViewsCount(employer);
        Popularity popularity = getEmployerPopularity(viewsCount);

        employerDto.setPopularity(popularity);
        employerDto.setDateCreate(employer.getDateCreate());
        employerDto.setViewsCount(viewsCount);
        return employerDto;
    }

    private Popularity getEmployerPopularity(Integer viewsCount) {
        return viewsCount >= fileSettings.getInteger("popularity.settings")
                ? Popularity.POPULAR : Popularity.REGULAR;
    }

    private Integer getAmendedEmployerViewsCount(Employer employer) {
        return employer.getViewsCount().getCounter() + 1;
    }

}
