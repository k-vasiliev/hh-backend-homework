package ru.hh.nab.backend;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.dao.*;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.entity.*;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.service.CompanyService;
import ru.hh.nab.service.ResumeService;
import ru.hh.nab.service.UserService;
import ru.hh.nab.service.VacancyService;
import ru.hh.nab.starter.NabCommonConfig;

@Configuration
@Import({
        NabCommonConfig.class,
        NabHibernateCommonConfig.class,
        UserDAO.class,
        ResumeDAO.class,
        CompanyDAO.class,
        VacancyDAO.class,
        NegotiationDAO.class,
        ResumeService.class,
        UserService.class,
        CompanyService.class,
        VacancyService.class
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.nab.entity");
        mappingConfig.addPackagesToScan("ru.hh.nab.dto");
        return mappingConfig;
    }

    @Bean
    DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings settings) {
        return dataSourceFactory.create(DataSourceType.MASTER, false, settings);
    }
}
