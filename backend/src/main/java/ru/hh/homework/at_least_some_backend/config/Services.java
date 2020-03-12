package ru.hh.homework.at_least_some_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.homework.at_least_some_backend.dao.*;
import ru.hh.homework.at_least_some_backend.resource.*;
import ru.hh.homework.at_least_some_backend.service.*;

@Configuration
@Import({
        HHUserDao.class,
        HHUserService.class,
        HHUserResource.class,

        HHCompanyDao.class,
        HHCompanyService.class,
        HHCompanyResource.class,

        HHResumeDao.class,
        HHResumeService.class,
        HHResumeResource.class,

        HHVacancyDao.class,
        HHVacancyService.class,
        HHVacancyResource.class,

        HHNegotiationDao.class,
        HHNegotiationService.class,
        HHNegotiationResource.class
})
public class Services { }
