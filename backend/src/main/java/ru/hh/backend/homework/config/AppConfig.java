package ru.hh.backend.homework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;

@Configuration
@Import({NabProdConfig.class,
        NabHibernateProdConfig.class,
        AppCommonConfig.class
})
public class AppConfig {
}
