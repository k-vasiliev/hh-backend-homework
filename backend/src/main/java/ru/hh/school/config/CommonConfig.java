package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.resource.*;
import ru.hh.school.service.*;

@Configuration
@Import({
        MainResource.class,
        NabCommonConfig.class,
        MainResource.class,
        MeResource.class,
        HhService.class,
        EmployerResource.class,
        FavoritesEmployerResource.class,
        FavoritesEmployerService.class,
        AreaDao.class,
        EmployerDao.class,
        VacancyResource.class,
        VacancyDao.class,
        FavoritesVacancyResource.class,
        FavoritesVacancyService.class
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
