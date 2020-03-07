package ru.hh.nab.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.resource.*;

@Configuration
@Import({ResumeResource.class, UserResource.class, CompanyResource.class,
        VacancyResource.class, NegotiationResource.class})
public class JerseyConfig {
}
