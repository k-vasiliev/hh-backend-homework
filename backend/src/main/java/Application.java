import ru.hh.nab.starter.NabApplication;
import ru.hh.school.config.Config;

public class Application {

    public static void main(String[] args) {
        NabApplication.builder()
                .configureJersey().bindToRoot()
                .build().run(Config.class);
    }
}
