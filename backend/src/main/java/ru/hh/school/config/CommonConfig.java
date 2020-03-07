package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;
import ru.hh.school.dao.*;
import ru.hh.school.entity.*;
import ru.hh.school.resource.*;
import ru.hh.school.service.*;

import javax.sql.DataSource;

@Configuration
@Import({NabProdConfig.class,
        NabHibernateProdConfig.class,
        CompanyDao.class,
        ResumeDao.class,
        NegotiationDao.class,
        UserDao.class,
        VacancyDao.class,
        CompanyResource.class,
        ResumeResource.class,
        NegotiationResource.class,
        UserResource.class,
        VacancyResource.class,
        CompanyService.class,
        ResumeService.class,
        NegotiationService.class,
        UserService.class,
        VacancyService.class
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        return new MappingConfig(Company.class, Resume.class, Negotiation.class, User.class, Vacancy.class);
    }

    @Bean
    public DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings settings) {
        return dataSourceFactory.create(DataSourceType.MASTER, false, settings);
    }
}
