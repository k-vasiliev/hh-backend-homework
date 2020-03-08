package ru.hh.school.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
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
        NabCommonConfig.class
})
public class CommonConfig {

    @Bean
    DataSource dataSource() {
        DataSource hikariDataSource = new HikariDataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return connection;
            }
        };
        return hikariDataSource;
    }

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru/hh/school/entity");
        return mappingConfig;
    }


    @Bean
    NabSessionFactoryBean.ServiceSupplier<?> nabSessionFactoryBuilderServiceSupplier() {
        return new NabSessionFactoryBean.ServiceSupplier<NabSessionFactoryBuilderFactory.BuilderService>() {
            @Override
            public Class<NabSessionFactoryBuilderFactory.BuilderService> getClazz() {
                return NabSessionFactoryBuilderFactory.BuilderService.class;
            }

            @Override
            public NabSessionFactoryBuilderFactory.BuilderService get() {
                return new NabSessionFactoryBuilderFactory.BuilderService();
            }
        };
    }

    @Bean
    DataSourceContextTransactionManager transactionManager(SessionFactory sessionFactory, DataSource routingDataSource) {
        HibernateTransactionManager simpleTransactionManager = new HibernateTransactionManager(sessionFactory);
        simpleTransactionManager.setDataSource(routingDataSource);
        return new DataSourceContextTransactionManager(simpleTransactionManager);
    }

    @Bean
    NabSessionFactoryBean sessionFactoryBean(DataSource dataSource, NabSessionFactoryBean.ServiceSupplier supplier) throws IOException {
        var props = new Properties();
        props.load(CommonConfig.class.getResourceAsStream("src/etc/hibernate.properties"));
        return new NabSessionFactoryBean(dataSource, props,
                new BootstrapServiceRegistryBuilder(), List.of(supplier), List.of());
    }

}
