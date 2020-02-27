package ru.hh.backend.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.hh.backend.entity.*;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class ApplicationConfig {

  @Bean
  public DataSource dataSource() {
    return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/hh", "hh", "hh");
  }

  @Bean
  LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
    LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
    localSessionFactoryBean.setDataSource(dataSource);

    Properties properties = new Properties();
    properties.put(Environment.DIALECT, PostgreSQL10Dialect.class.getName());
    //properties.put(Environment.HBM2DDL_AUTO, "update");
    localSessionFactoryBean.setHibernateProperties(properties);
    localSessionFactoryBean.setPackagesToScan("ru.hh.backend.dao");
    localSessionFactoryBean.setAnnotatedClasses(ru.hh.backend.entity.Company.class, Negotiation.class, Resume.class, UserEntity.class, Vacancy.class);
    return localSessionFactoryBean;
  }

  @Bean
  public HibernateTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
    return new HibernateTransactionManager(sessionFactory);
  }

  @Bean
  public SessionFactory sessionFactory(LocalSessionFactoryBean localSessionFactoryBean) {
    return localSessionFactoryBean.getObject();
  }
}
