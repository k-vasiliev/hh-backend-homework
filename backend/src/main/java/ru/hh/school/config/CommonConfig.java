package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.FavoritesEmployerDao;
import ru.hh.school.dto.request.FavoritesEmployerRequestDto;
import ru.hh.school.entity.FavoritesEmployer;
import ru.hh.school.mapper.AreaMapper;
import ru.hh.school.mapper.EmployerMapper;
import ru.hh.school.mapper.FavoritesEmployerMapper;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.resource.FavoritesEmployerResource;
import ru.hh.school.resource.VacancyResource;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.FavoritesEmployerService;
import ru.hh.school.service.VacancyService;

@Configuration
@Import({
        // import your beans here
        NabCommonConfig.class,
        NabHibernateProdConfig.class,

        EmployerResource.class,
        FavoritesEmployerResource.class,
        VacancyResource.class,

        EmployerService.class,
        FavoritesEmployerService.class,
        VacancyService.class,

        FavoritesEmployerMapper.class,
        AreaMapper.class,
        EmployerMapper.class,

        EmployerDao.class,
        FavoritesEmployerDao.class,
        FavoritesEmployerRequestDto.class,
        FavoritesEmployer.class,
})
public class CommonConfig {
    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }
}
