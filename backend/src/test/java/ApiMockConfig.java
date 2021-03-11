import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hh.school.http.HhClient;
import ru.hh.school.service.ApiService;
import ru.hh.school.util.IdParameterValidator;
import ru.hh.school.util.PaginationValidator;

import javax.inject.Inject;

@Configuration
public class ApiMockConfig {

    @Inject
    private PaginationValidator paginationValidator;
    @Inject
    private HhClient hhClient;
    @Inject
    private IdParameterValidator idParameterValidator;

    @Bean
    public ApiService apiService() {
        return Mockito.spy(new ApiService(hhClient, paginationValidator, idParameterValidator));
    }
}
