package config;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class TestApplicationConfig {


    @Bean
    public DataSource dataSource() {
        System.out.println("Creating data source");
        return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/hh","hh","hh");
    }


    @Bean
    LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
        System.out.println("Creating localSessionFactory");
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, PostgreSQL10Dialect.class.getName());
        properties.put(Environment.HBM2DDL_AUTO, "update");
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan("dao");
        localSessionFactoryBean.setAnnotatedClasses(
                UsersEntity.class, ResumeEntity.class, VacancyEntity.class, CompanyEntity.class,
                VacancyEntity.class, VacancyResponseEntity.class
        );

        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
        System.out.println("Creating transactionManager");
        return new HibernateTransactionManager(sessionFactory);
    }


    @Bean
    public SessionFactory sessionFactory(LocalSessionFactoryBean localSessionFactoryBean) {
        System.out.println("Creating sessionFactory");
        return localSessionFactoryBean.getObject();
    }

}
