package ru.hh.backend.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.backend.homework.entity.*;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.starter.NabCommonConfig;

import javax.sql.DataSource;

@Configuration
@Import({
        NabCommonConfig.class,
        NabHibernateCommonConfig.class
})
public class AppCommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        return new MappingConfig(CompanyEntity.class, NegotiationEntity.class, ResumeEntity.class, UserEntity.class, VacancyEntity.class);
    }

    @Bean
    DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings settings) {
        return dataSourceFactory.create(DataSourceType.MASTER, false, settings);
    }
}
