package ru.hh.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.dao.*;

@Configuration
@Import({
        EmployerDao.class,
        AreaDao.class,
        VacancyDao.class,
        CommentDao.class,
        CounterDao.class
})
public class DaoConfig {
}
