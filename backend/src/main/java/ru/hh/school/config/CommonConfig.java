package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.AreaDAO;
import ru.hh.school.dao.EmployerDAO;
import ru.hh.school.dao.VacancyDAO;
import ru.hh.school.resource.*;
import ru.hh.school.service.FavoriteEmployersService;
import ru.hh.school.service.FavoriteVacanciesService;
import ru.hh.school.service.MainService;

@Configuration
@Import({
    VacancyDAO.class,
    EmployerDAO.class,
    AreaDAO.class,
    VacanciesResource.class,
    FavoriteVacanciesResource.class,
    EmployersResource.class,
    FavoriteEmployersResource.class,
    MeResource.class,
    MainResource.class,
    FavoriteEmployersService.class,
    FavoriteVacanciesService.class,
    MainService.class,
    NabCommonConfig.class
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }
}
