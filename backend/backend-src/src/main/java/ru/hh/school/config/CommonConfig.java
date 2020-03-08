package ru.hh.school.config;

import ru.hh.school.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.controller.*;

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
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru/hh/school/entity");
        return mappingConfig;
    }
}
