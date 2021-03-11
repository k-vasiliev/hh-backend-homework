package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.FavoritesEmployerDao;
import ru.hh.school.dao.FavoritesVacancyDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.EmployerApiHh;
import ru.hh.school.dto.request.FavoritesEmployerRequestDto;
import ru.hh.school.dto.request.FavoritesVacancyRequestDto;
import ru.hh.school.mapper.*;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.resource.FavoritesEmployerResource;
import ru.hh.school.resource.FavoritesVacancyResource;
import ru.hh.school.resource.VacancyResource;
import ru.hh.school.service.*;

@Configuration
@Import({
        // import your beans here
        NabCommonConfig.class,
        NabHibernateProdConfig.class,

        EmployerResource.class,
        FavoritesEmployerResource.class,
        VacancyResource.class,
        FavoritesVacancyResource.class,

        EmployerService.class,
        FavoritesEmployerService.class,
        VacancyService.class,
        FavoritesVacancyService.class,
        ApiHhService.class,

        FavoritesEmployerMapper.class,
        AreaMapper.class,
        EmployerMapper.class,
        VacancyMapper.class,
        FavoritesVacancyMapper.class,

        EmployerDao.class,
        FavoritesEmployerDao.class,
        VacancyDao.class,
        FavoritesVacancyDao.class,
        FavoritesEmployerRequestDto.class,
        FavoritesVacancyRequestDto.class,
        EmployerApiHh.class,
})
public class CommonConfig {
    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }
}
