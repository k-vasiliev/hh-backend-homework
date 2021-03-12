package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.resource.FavoritesEmployerResource;
import ru.hh.school.resource.MainResource;
import ru.hh.school.resource.MeResource;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.FavoritesEmployerService;
import ru.hh.school.service.MeService;

@Configuration
@Import({
        MainResource.class,
        NabCommonConfig.class,
        MainResource.class,
        MeResource.class,
        MeService.class,
        EmployerResource.class,
        EmployerService.class,
        FavoritesEmployerResource.class,
        FavoritesEmployerService.class,
        AreaDao.class,
        EmployerDao.class
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
