package ru.hh.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.backend.dao.*;

@Configuration
@Import({
        CompanyDao.class,
        NegotiationDao.class,
        ResumeDao.class,
        UserDao.class,
        VacancyDao.class
})
public class DaoConfig {
}
