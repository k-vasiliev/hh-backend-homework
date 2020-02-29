package ru.hh.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.NegotiationDao;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.resource.CompanyResource;
import ru.hh.school.resource.NegotiationResource;
import ru.hh.school.resource.ResumeResource;
import ru.hh.school.resource.UserResource;
import ru.hh.school.resource.VacancyResource;
import ru.hh.school.service.CompanyService;
import ru.hh.school.service.NegotiationService;
import ru.hh.school.service.ResumeService;
import ru.hh.school.service.UserService;
import ru.hh.school.service.VacancyService;

import javax.sql.DataSource;

@Configuration
@Import({
    NabProdConfig.class,
    NabHibernateProdConfig.class,
    UserDao.class,
    UserResource.class,
    UserService.class,
    ResumeDao.class,
    ResumeService.class,
    ResumeResource.class,
    CompanyDao.class,
    CompanyResource.class,
    CompanyService.class,
    VacancyDao.class,
    VacancyResource.class,
    VacancyService.class,
    NegotiationDao.class,
    NegotiationResource.class,
    NegotiationService.class
})
public class Config {

    @Bean
    public DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings fileSettings) {
        return dataSourceFactory.create("postgres", false, fileSettings);
    }

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }
}
