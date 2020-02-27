package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.models.Company;
import ru.hh.school.models.Resume;
import ru.hh.school.models.User;
import ru.hh.school.models.Vacancy;

import javax.sql.DataSource;

@Configuration
@Import({NabCommonConfig.class, NabHibernateCommonConfig.class})
public class BaseConfig {
    @Bean
    public DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings fileSettings) {
        return dataSourceFactory.create(DataSourceType.MASTER, false, fileSettings);
    }

    @Bean
    public MappingConfig mappingConfig() {
        return new MappingConfig(User.class, Resume.class, Company.class, Vacancy.class);
    }
}
