import com.opentable.db.postgres.embedded.EmbeddedPostgres;
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
import ru.hh.nab.testbase.postgres.embedded.EmbeddedPostgresDataSourceFactory;
import ru.hh.school.config.CommonConfig;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
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

  @PostConstruct
  public static void before() throws IOException {
    EmbeddedPostgres em = EmbeddedPostgresDataSourceFactory.getEmbeddedPostgres();
    Arrays.stream(Files.readString(Path.of("init.sql")).split(";"))
            .forEach(query -> executeQuery(em, query));
  }

  private static void executeQuery(EmbeddedPostgres em, String query) {
    try (Connection connection = em.getPostgresDatabase().getConnection();
         Statement statement = connection.createStatement()) {
      statement.executeUpdate(query);
      System.out.println("SUCCECC\n" + query);
    } catch (SQLException e) {
      System.out.println("SQLEXCEPTION" + e.getMessage());
    }
  }

}
