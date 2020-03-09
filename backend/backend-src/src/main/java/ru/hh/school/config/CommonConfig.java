package ru.hh.school.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.hibernate.NabSessionFactoryBean;
import ru.hh.nab.hibernate.NabSessionFactoryBuilderFactory;
import ru.hh.nab.hibernate.transaction.DataSourceContextTransactionManager;
import ru.hh.school.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.controller.*;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.ResumeDao;
import ru.hh.school.dao.UserDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.service.CompanyService;
import ru.hh.school.service.ResumeService;
import ru.hh.school.service.UserService;
import ru.hh.school.service.VacancyService;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Configuration
@Import({
        // import your beans here
        ApiCompany.class,
        ApiNegotiation.class,
        ApiResume.class,
        ApiUser.class,
        ApiVacancy.class,
        CompanyDao.class,
        ResumeDao.class,
        UserDao.class,
        VacancyDao.class,
        CompanyService.class,
        ResumeService.class,
        UserService.class,
        VacancyService.class,
        NabCommonConfig.class,
        NabHibernateCommonConfig.class
})
public class CommonConfig {


    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru/hh/school/entity");
        return mappingConfig;
    }

    @Bean
    DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings settings) {
        return dataSourceFactory.create(DataSourceType.MASTER, false, settings);
    }


}
