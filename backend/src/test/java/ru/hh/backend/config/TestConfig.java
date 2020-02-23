package ru.hh.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.testbase.NabTestConfig;
import ru.hh.nab.testbase.hibernate.NabHibernateTestBaseConfig;

@Configuration
@Import({
    NabTestConfig.class,
    NabHibernateTestBaseConfig.class,
    CommonConfig.class
})
@ComponentScan(basePackages = "ru.hh.backend")
public class TestConfig {
}

