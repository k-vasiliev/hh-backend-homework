package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.resource.mapper.EmployerMapperImpl;
import ru.hh.school.resource.EmployerController;
import ru.hh.school.resource.FavouritesController;
import ru.hh.school.resource.VacancyController;
import ru.hh.school.resource.mapper.VacancyMapperImpl;
import ru.hh.school.service.VacancyService;
import ru.hh.school.service.impl.EmployerServiceImpl;
import ru.hh.school.service.impl.VacancyServiceImpl;

@Configuration
@Import({
        FavouritesController.class,
        EmployerController.class,
        EmployerServiceImpl.class,
        EmployerMapperImpl.class,
        VacancyController.class,
        VacancyServiceImpl.class,
        VacancyMapperImpl.class,
        NabCommonConfig.class,
        RestTemplateConfig.class
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }
}
