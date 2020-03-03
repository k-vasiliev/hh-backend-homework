package hh;

import config.ApplicationConfig;
import config.TestApplicationConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Main server
 *
 */
public class Main
{

    static Server createServer(Class<?> appConfig, int port) {
        Server srv = new Server(port);
        ServletContextHandler contextHandler = new ServletContextHandler();

        System.out.println("Initalizing Spring");

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(appConfig);
        applicationContext.scan("dao", "routes", "service");

        contextHandler.addEventListener(new ContextLoaderListener(applicationContext));

        System.out.println( "Starting server!" );

        srv.setHandler(contextHandler);
        ServletHolder servletHolder = contextHandler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "routes");

        return srv;
    }


    public static void main( String[] args ) throws Exception {
        Class <?> appConfig = ApplicationConfig.class;

        if (args.length > 0 && args[0].equals("debug"))
            appConfig = TestApplicationConfig.class;

        Server srv = createServer(appConfig, 8080);

        srv.start();
        srv.join();
    }
}
