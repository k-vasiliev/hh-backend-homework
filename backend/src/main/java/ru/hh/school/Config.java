package ru.hh.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;

import javax.sql.DataSource;

@Configuration
@Import({
    NabProdConfig.class,
    NabHibernateProdConfig.class
})
public class Config {

    @Bean
    public DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings fileSettings) {
        return dataSourceFactory.create("postgres", false, fileSettings);
    }

    @Bean
    public MappingConfig mappingConfig() {
        return new MappingConfig();
    }
}
