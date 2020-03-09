package tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class TestHelper {
    @Autowired
    SessionFactory  factory;

    private static final Path SCRIPTS_DIR = Path.of("src","main", "resources");

    public static void executeScript(SessionFactory streamFactory, String scriptFileName) {
        Session session = streamFactory.openSession();
        Transaction tx = session.beginTransaction();

        splitToQueries(SCRIPTS_DIR.resolve(scriptFileName))
        .forEach((query) -> {
            session.createSQLQuery(query);
        });

        tx.commit();
        session.close();
    }

    private static Stream<String> splitToQueries(Path path) {
        try {
            return Arrays.stream(Files.readString(path).split(";"));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + path, e);
        }
    }


    public static void initDb(SessionFactory factory) {
        executeScript(factory, "db_init.sql");
    }
}
