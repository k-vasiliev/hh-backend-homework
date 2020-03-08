package ru.hh.homework.at_least_some_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import javax.sql.DataSource;

import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;

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
        return new MappingConfig();
    }
}
