package ru.hh.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.backend.resource.UserResource;

@Configuration
@Import(UserResource.class)
public class JerseyConfig {
}
