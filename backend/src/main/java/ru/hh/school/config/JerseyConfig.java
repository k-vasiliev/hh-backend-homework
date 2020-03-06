package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hh.school.resource.*;
import ru.hh.school.service.*;

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

    @Bean
    public NegotiationResource negotiationResource(NegotiationService negotiationService) {
        return new NegotiationResource(negotiationService);
    }
}
