package ru.hh.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.backend.dao.UserDao;
import ru.hh.backend.mapper.UserMapper;
import ru.hh.backend.service.UserService;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;

@Configuration
@Import({
        CommonConfig.class,
        DaoConfig.class,
        MapperConfig.class,
        ResourceConfig.class,
        ServiceConfig.class
})
public class ApplicationConfig {
}
