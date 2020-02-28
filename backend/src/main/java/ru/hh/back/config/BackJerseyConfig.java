package ru.hh.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.back.dao.NegotiationDao;
import ru.hh.back.resource.CompanyResource;
import ru.hh.back.resource.ResumeResource;
import ru.hh.back.resource.UserResource;
import ru.hh.back.resource.VacancyResource;

@Configuration
@Import({CompanyResource.class, ResumeResource.class, UserResource.class, VacancyResource.class, NegotiationDao.class})
public class BackJerseyConfig {

}
