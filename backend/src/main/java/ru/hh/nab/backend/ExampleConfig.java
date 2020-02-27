package ru.hh.nab.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.dao.UserDAO;
import ru.hh.nab.entity.Company;
import ru.hh.nab.entity.Resume;
import ru.hh.nab.entity.User;
import ru.hh.nab.entity.Vacancy;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.starter.NabProdConfig;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;

import javax.sql.DataSource;

@Configuration
@Import({NabProdConfig.class, NabHibernateCommonConfig.class})
public class ExampleConfig {

    @Bean
    public MappingConfig mappingConfig() {
        return new MappingConfig(Company.class, Resume.class, User.class, Vacancy.class, UserDAO.class);
    }

}
