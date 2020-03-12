package ru.hh.homework.at_least_some_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;


import ru.hh.homework.at_least_some_backend.entity.HHCompany;
import ru.hh.homework.at_least_some_backend.entity.HHResume;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;

import ru.hh.homework.at_least_some_backend.entity.HHUser;

@Configuration
public class Hibernate
{
    @Bean
    DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings fileSettings)
    {
        return dataSourceFactory.create(DataSourceType.MASTER, false, fileSettings);
    }

    @Bean
    MappingConfig mappingConfig()
    {
        return new MappingConfig(
                HHUser.class,
                HHCompany.class,
                HHResume.class
        );
    }
}
