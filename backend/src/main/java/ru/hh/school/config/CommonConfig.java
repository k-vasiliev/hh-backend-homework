package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.exception.CustomExceptionMapper;
import ru.hh.school.http.HhClient;

@Configuration
@Import({
        // import your beans here
        NabCommonConfig.class,
        DaoConfig.class,
        ServiceConfig.class,
        ResourceConfig.class,
        ComponentConfig.class,
        HhClient.class,
        CustomExceptionMapper.class,
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }
}
