import org.mockito.Mockito;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.testbase.NabTestConfig;
import ru.hh.nab.testbase.hibernate.NabHibernateTestBaseConfig;
import ru.hh.school.config.CommonConfig;
import ru.hh.school.http.HhClient;
import ru.hh.school.service.ApiService;
import ru.hh.school.util.IdParameterValidator;
import ru.hh.school.util.PaginationValidator;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({
  NabTestConfig.class,
  NabHibernateCommonConfig.class,
  NabHibernateTestBaseConfig.class,
  CommonConfig.class
})
public class AppTestConfig {

  private static final String DB_SETTINGS_FILE_NAME = "db-settings-test.properties";

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

  @Bean
  public DataSource dataSource(DataSourceFactory dataSourceFactory, Properties dbProperties) {
    return dataSourceFactory.create("master", false, new FileSettings(dbProperties));
  }

  @Bean
  public PropertiesFactoryBean dbProperties() {
    PropertiesFactoryBean properties = new PropertiesFactoryBean();
    properties.setSingleton(false);
    properties.setIgnoreResourceNotFound(true);
    properties.setLocations(
        new ClassPathResource(DB_SETTINGS_FILE_NAME),
        new ClassPathResource(DB_SETTINGS_FILE_NAME + ".dev"));
    return properties;
  }



}
