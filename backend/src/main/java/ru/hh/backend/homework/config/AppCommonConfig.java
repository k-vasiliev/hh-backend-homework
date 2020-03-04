package ru.hh.backend.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.hh.backend.homework.dao.*;
import ru.hh.backend.homework.entity.*;
import ru.hh.backend.homework.mapper.*;
import ru.hh.backend.homework.service.*;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.starter.NabCommonConfig;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@Import({
        NabCommonConfig.class,
        NabHibernateCommonConfig.class,
        CompanyDao.class,
        CompanyService.class,
        CompanyMapper.class,
        NegotiationDao.class,
        NegotiationService.class,
        NegotiationMapper.class,
        ResumeDao.class,
        ResumeService.class,
        ResumeMapper.class,
        UserDao.class,
        UserService.class,
        UserMapper.class,
        VacancyDao.class,
        VacancyService.class,
        VacancyMapper.class
})
public class AppCommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.backend.homework.entity");
        return mappingConfig;
    }

    @Bean
    DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings settings) {
        return dataSourceFactory.create(DataSourceType.MASTER, false, settings);
    }
}
