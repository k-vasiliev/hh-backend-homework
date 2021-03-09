package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.*;
import ru.hh.school.exception.CustomExceptionMapper;
import ru.hh.school.http.HhClient;
import ru.hh.school.resource.*;
import ru.hh.school.service.*;
import ru.hh.school.util.*;

@Configuration
@Import({
        // import your beans here
        ExampleResource.class,
        NabCommonConfig.class,
        EmployerDao.class,
        AreaDao.class,
        VacancyDao.class,
        HhClient.class,
        ApiService.class,
        PaginationValidator.class,
        StringParameterFilter.class,
        EmployerResource.class,
        EmployerMapper.class,
        IdParameterValidator.class,
        FavoritesEmployerResource.class,
        EmployerService.class,
        AreaMapper.class,
        CustomExceptionMapper.class,
        CommentDao.class,
        ViewsCounterDao.class,
        CounterService.class,
        CommentService.class,
        VacancyResource.class,
        VacancyMapper.class,
        VacancyService.class,
        FavoritesVacancyResource.class,
        SalaryMapper.class
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }
}
