package ru.hh.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.service.*;

@Configuration
@Import({
        ApiService.class,
        CommentService.class,
        CounterService.class,
        EmployerService.class,
        VacancyService.class
})
public class ServiceConfig {
}
