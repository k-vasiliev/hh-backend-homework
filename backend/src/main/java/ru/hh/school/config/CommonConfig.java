package ru.hh.school.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.*;
import ru.hh.school.entity.CompanyEntity;
import ru.hh.school.entity.ResumeEntity;
import ru.hh.school.entity.UserEntity;
import ru.hh.school.entity.VacancyEntity;
import ru.hh.school.mapper.*;
import ru.hh.school.resource.*;
import ru.hh.school.service.*;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({
        NabHibernateCommonConfig.class,
        UserDao.class,
        UserService.class,
        ResumeDao.class,
        ResumeService.class,
        CompanyDao.class,
        CompanyService.class,
        VacancyDao.class,
        VacancyService.class,
        NegotiationDao.class,
        NegotiationService.class,
        JerseyConfig.class,
        NabCommonConfig.class,
        CompanyMapper.class,
        NegotiationMapper.class,
        ResumeMapper.class,
        UserMapper.class,
        VacancyMapper.class
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }

    @Bean
    public DataSource dataSource() {
        System.out.println("Creating data source");
        return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/hh", "hh", "hh");
        // return new DriverManagerDataSource("jdbc:postgresql://postgres/hh","hh","hh");
    }


}
