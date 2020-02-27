package ru.hh.backend.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hh.backend.homework.mapper.*;
import ru.hh.backend.homework.resource.*;
import ru.hh.backend.homework.service.*;

@Configuration
public class JerseyConfig {
    @Bean
    public CompanyResource companyResource(CompanyService companyService, CompanyMapper companyMapper) {
        return new CompanyResource(companyService, companyMapper);
    }

    @Bean
    public NegotiationResource negotiationResource(NegotiationService negotiationService,
                                                   NegotiationMapper negotiationMapper) {
        return new NegotiationResource(negotiationService, negotiationMapper);
    }

    @Bean
    public ResumeResource resumeResource(ResumeService resumeService, ResumeMapper resumeMapper) {
        return new ResumeResource(resumeService, resumeMapper);
    }

    @Bean
    public UserResource userResource(UserService userService, UserMapper userMapper) {
        return new UserResource(userService, userMapper);
    }

    @Bean
    public VacancyResource vacancyResource(VacancyService vacancyService,
                                           NegotiationService negotiationService,
                                           VacancyMapper vacancyMapper,
                                           NegotiationMapper negotiationMapper) {
        return new VacancyResource(vacancyService, negotiationService,
                vacancyMapper, negotiationMapper);
    }
}
