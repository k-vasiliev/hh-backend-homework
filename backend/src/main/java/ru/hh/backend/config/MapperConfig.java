package ru.hh.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.backend.mapper.*;

@Configuration
@Import({
        CompanyMapper.class,
        NegotiationMapper.class,
        ResumeMapper.class,
        UserMapper.class,
        VacancyMapper.class
})
public class MapperConfig {
}
