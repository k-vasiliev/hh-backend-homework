package ru.hh.nab.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.resource.CompanyResource;
import ru.hh.nab.resource.ResumeResource;
import ru.hh.nab.resource.UserResource;
import ru.hh.nab.resource.VacancyResource;

@Configuration
@Import({ResumeResource.class, UserResource.class, CompanyResource.class, VacancyResource.class})
public class JerseyConfig {
}
