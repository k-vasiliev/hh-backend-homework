package ru.hh.nab.backend;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.dao.UserDAO;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.entity.Company;
import ru.hh.nab.entity.Resume;
import ru.hh.nab.entity.Users;
import ru.hh.nab.entity.Vacancy;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.starter.NabCommonConfig;

@Configuration
@Import({
        NabCommonConfig.class,
        NabHibernateCommonConfig.class,
        UserDAO.class
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        return new MappingConfig(Company.class, Resume.class, Users.class, Vacancy.class, UserDAO.class);
    }

    @Bean
    DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings settings) {
        return dataSourceFactory.create(DataSourceType.MASTER, false, settings);
    }
}
