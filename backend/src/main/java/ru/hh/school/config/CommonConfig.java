package ru.hh.school.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.EmployerRepository;
import ru.hh.school.dao.FavouriteRepository;
import ru.hh.school.dao.VacancyRepository;
import ru.hh.school.resource.EmployerController;
import ru.hh.school.resource.FavouritesController;
import ru.hh.school.resource.VacancyController;
import ru.hh.school.resource.mapper.AreaMapperImpl;
import ru.hh.school.resource.mapper.EmployerMapperImpl;
import ru.hh.school.resource.mapper.FavouriteMapperImpl;
import ru.hh.school.resource.mapper.VacancyMapperImpl;
import ru.hh.school.service.impl.AreaServiceImpl;
import ru.hh.school.service.impl.EmployerServiceImpl;
import ru.hh.school.service.impl.FavouriteServiceImpl;
import ru.hh.school.service.impl.VacancyServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Configuration
@Import({
        FavouritesController.class,
        EmployerController.class,
        EmployerServiceImpl.class,
        EmployerMapperImpl.class,
        EmployerRepository.class,
        VacancyController.class,
        VacancyServiceImpl.class,
        VacancyMapperImpl.class,
        VacancyRepository.class,
        FavouritesController.class,
        FavouriteServiceImpl.class,
        FavouriteMapperImpl.class,
        FavouriteRepository.class,
        NabCommonConfig.class,
        AreaServiceImpl.class,
        AreaMapperImpl.class,
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.domain");
        return mappingConfig;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper()    {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(sdf);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}
