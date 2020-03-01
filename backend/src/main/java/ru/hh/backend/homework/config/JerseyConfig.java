package ru.hh.backend.homework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.backend.homework.resource.*;

@Configuration
@Import({CompanyResource.class,
        NegotiationResource.class,
        ResumeResource.class,
        UserResource.class,
        VacancyResource.class})
public class JerseyConfig {
}
