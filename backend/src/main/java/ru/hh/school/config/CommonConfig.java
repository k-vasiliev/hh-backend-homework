package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.http.HhClient;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.resource.ExampleResource;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.PaginationFilter;
import ru.hh.school.service.QueryFilter;
import ru.hh.school.util.EmployerMapper;

@Configuration
@Import({
  // import your beans here
  ExampleResource.class,
  NabCommonConfig.class,
        EmployerDao.class,
        AreaDao.class,
        VacancyDao.class,
        HhClient.class,
        EmployerService.class,
        PaginationFilter.class,
        QueryFilter.class,
        EmployerResource.class,
        EmployerMapper.class
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }
}
