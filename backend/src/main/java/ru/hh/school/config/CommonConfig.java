package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.controller.employer.EmployerController;
import ru.hh.school.controller.employer.FavEmployerController;
import ru.hh.school.controller.vacancy.FavVacancyController;
import ru.hh.school.controller.vacancy.VacancyController;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.CompanyDao;
import ru.hh.school.dao.SalaryDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.mapper.AreaMapper;
import ru.hh.school.mapper.CompanyMapper;
import ru.hh.school.mapper.SalaryMapper;
import ru.hh.school.mapper.VacancyMapper;
import ru.hh.school.resource.ExampleResource;
import ru.hh.school.service.AreaService;
import ru.hh.school.service.CompanyService;
import ru.hh.school.service.SalaryService;
import ru.hh.school.service.VacancyService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


@Configuration
@Import({
        // import your beans here
        EmployerController.class,
        VacancyController.class,

        FavEmployerController.class,
        FavVacancyController.class,

        ExampleResource.class,
        NabCommonConfig.class,

        CompanyDao.class,
        AreaDao.class,
        VacancyDao.class,
        SalaryDao.class,

        CompanyMapper.class,
        AreaMapper.class,
        VacancyMapper.class,
        SalaryMapper.class,

        CompanyService.class,
        AreaService.class,
        VacancyService.class,
        SalaryService.class
})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }

    @Bean
    public Client client() {
        return ClientBuilder.newClient();
    }
}
