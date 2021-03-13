package ru.hh.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.component.*;

@Configuration
@Import({
        AreaMapper.class,
        EmployerMapper.class,
        VacancyMapper.class,
        IdParameterValidator.class,
        PaginationValidator.class
})
public class ComponentConfig {
}
