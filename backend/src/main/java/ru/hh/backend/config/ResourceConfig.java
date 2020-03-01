package ru.hh.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.backend.resource.*;

@Configuration
@Import({
        CompanyResource.class,
        NegotiationResource.class,
        ResumeResource.class,
        UserResource.class,
        VacancyResource.class
})
public class ResourceConfig {
}
