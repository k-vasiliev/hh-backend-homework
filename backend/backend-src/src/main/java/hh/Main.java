package hh;

import config.ApplicationConfig;
import config.LocalApplicationConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Main server
 *
 */
public class Main
{

    static Server createHHServer(WebApplicationContext applicationContext, int port) {
        Server srv = new Server(port);
        ServletContextHandler contextHandler = new ServletContextHandler();

        System.out.println("Initalizing Spring");


        contextHandler.addEventListener(new ContextLoaderListener(applicationContext));

        System.out.println( "Starting server!" );

        srv.setHandler(contextHandler);
        ServletHolder servletHolder = contextHandler.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "controller");

        return srv;
    }

    public static void main( String[] args ) throws Exception {
        Class <?> appConfig = ApplicationConfig.class;

        if (args.length > 0 && args[0].equals("debug"))
            appConfig = LocalApplicationConfig.class;

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(appConfig);
        applicationContext.scan("dao", "controller", "service");

        Server srv = createHHServer(applicationContext, 8080);

        srv.start();
        srv.join();
    }
}
