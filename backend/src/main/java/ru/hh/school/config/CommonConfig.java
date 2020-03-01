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
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.entity.Company;
import ru.hh.school.entity.Resume;
import ru.hh.school.entity.User;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.resource.CompanyResource;
import ru.hh.school.resource.ResumeResource;
import ru.hh.school.resource.UserResource;
import ru.hh.school.resource.VacancyResource;
import ru.hh.school.service.CompanyService;
import ru.hh.school.service.ResumeService;
import ru.hh.school.service.UserService;
import ru.hh.school.service.VacancyService;

import javax.sql.DataSource;

@Configuration
@Import({NabProdConfig.class,
        NabHibernateProdConfig.class,
        CompanyDao.class,
        ResumeDao.class,
        UserDao.class,
        VacancyDao.class,
        CompanyResource.class,
        ResumeResource.class,
        UserResource.class,
        VacancyResource.class,
        CompanyService.class,
        ResumeService.class,
        UserService.class,
        VacancyService.class
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        return new MappingConfig(User.class, Resume.class, Company.class, Vacancy.class);
    }

    @Bean
    public DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings settings) {
        return dataSourceFactory.create(DataSourceType.MASTER, false, settings);
    }
}
