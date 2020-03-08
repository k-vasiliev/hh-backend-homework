package ru.hh.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.resource.*;

@Configuration
@Import({ExampleResource.class, CompanyResource.class, ResumeResource.class, UserResource.class, VacancyResource.class, NegotiationResource.class})
public class JerseyConfig {

}
