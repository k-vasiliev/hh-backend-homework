package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hh.school.resource.CompanyResource;
import ru.hh.school.resource.ResumeResource;
import ru.hh.school.resource.UserResource;
import ru.hh.school.resource.VacancyResource;
import ru.hh.school.service.CompanyService;
import ru.hh.school.service.ResumeService;
import ru.hh.school.service.UserService;
import ru.hh.school.service.VacancyService;

@Configuration
public class JerseyConfig {

    @Bean
    public UserResource userResource(UserService userService) {
        return new UserResource(userService);
    }

    @Bean
    public ResumeResource resumeResource(ResumeService resumeService) {
        return new ResumeResource(resumeService);
    }

    @Bean
    public CompanyResource companyResource(CompanyService companyService) {
        return new CompanyResource(companyService);
    }

    @Bean
    public VacancyResource vacancyResource(VacancyService vacancyService) {
        return new VacancyResource(vacancyService);
    }
}
