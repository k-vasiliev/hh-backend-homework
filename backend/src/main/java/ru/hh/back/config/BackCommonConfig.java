package ru.hh.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.back.dao.CompanyDao;
import ru.hh.back.dao.NegotiationDao;
import ru.hh.back.dao.ResumeDao;
import ru.hh.back.dao.UserDao;
import ru.hh.back.dao.VacancyDao;
import ru.hh.back.entity.CompanyEntity;
import ru.hh.back.entity.NegotiationEntity;
import ru.hh.back.entity.ResumeEntity;
import ru.hh.back.entity.UserEntity;
import ru.hh.back.entity.VacancyEntity;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;

import javax.sql.DataSource;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@Import({
        NabCommonConfig.class,
        NabHibernateCommonConfig.class,
        CompanyDao.class,
        NegotiationDao.class,
        ResumeDao.class,
        UserDao.class,
        VacancyDao.class,
})
public class BackCommonConfig {
    @Bean
    public MappingConfig mappingConfig() {
        return new MappingConfig(CompanyEntity.class, NegotiationEntity.class, ResumeEntity.class, UserEntity.class, VacancyEntity.class);
    }
    @Bean
    DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings settings) {
        return dataSourceFactory.create("postgres", false, settings);
    }


}
