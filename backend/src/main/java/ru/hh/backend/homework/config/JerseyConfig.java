package ru.hh.backend.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.backend.homework.mapper.*;
import ru.hh.backend.homework.resource.*;
import ru.hh.backend.homework.service.*;

@Configuration
@Import({
        CompanyResource.class,
        NegotiationResource.class,
        ResumeResource.class,
        UserResource.class,
        VacancyResource.class
})
public class JerseyConfig {
}
