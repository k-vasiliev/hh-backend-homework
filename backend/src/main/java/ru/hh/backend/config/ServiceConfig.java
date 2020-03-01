package ru.hh.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.backend.service.*;

@Configuration
@Import({
        CompanyService.class,
        NegotiationService.class,
        ResumeService.class,
        UserService.class,
        VacancyService.class
})
public class ServiceConfig {
}
