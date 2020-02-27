package ru.hh.back.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
// import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.hh.back.dao.UserDao;
import ru.hh.back.entity.CompanyEntity;
// import ru.hh.back.entity.NegotiationEntity;
import ru.hh.back.entity.ResumeEntity;
import ru.hh.back.entity.UserEntity;
import ru.hh.back.entity.VacancyEntity;
import ru.hh.back.resource.CompanyResource;
import ru.hh.back.resource.ResumeResource;
import ru.hh.back.resource.UserResource;
import ru.hh.back.resource.VacancyResource;
// import ru.hh.back.service.UserMapper;
import ru.hh.back.service.UserMapper;
import ru.hh.nab.common.properties.FileSettings;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@Import({CompanyResource.class, ResumeResource.class, UserResource.class, VacancyResource.class, UserMapper.class})
public class BackJerseyConfig {
    @Bean
    public DataSource dataSource(FileSettings settings) {
        var subsettings = settings.getSubSettings("postgres");
        var url = subsettings.getString("url");
        var user = subsettings.getString("user");
        var password = subsettings.getString("password");
         // return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/hh", "hh", "hh");
         return new DriverManagerDataSource(url, user, password);
    }

    @Bean
    LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, PostgreSQL10Dialect.class.getName());
        //properties.put(Environment.HBM2DDL_AUTO, "update");

        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan("ru.hh.back.dao");
        localSessionFactoryBean.setAnnotatedClasses(ResumeEntity.class, UserEntity.class, CompanyEntity.class
                , VacancyEntity.class);

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

    @Bean
    public UserDao userDao(SessionFactory sessionFactory){
        return new UserDao(sessionFactory);
    }

}
