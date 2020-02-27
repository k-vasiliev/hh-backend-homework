package ru.hh.backend;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class Application {

  private static Server createServer(int port) {
    Server server = new Server(port);

    // config resources, jersey servlet
    ServletContextHandler ctx = new ServletContextHandler();

    var applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.scan("ru.hh.backend.config", "ru.hh.backend.dao", "ru.hh.backend.resource", "ru.hh.backend.service");
    ctx.addEventListener(new ContextLoaderListener(applicationContext));
    server.setHandler(ctx);

    ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
    servletHolder.setInitParameter("jersey.config.server.provider.packages", "ru.hh.backend.resource");
    return server;
  }

  public static void main(String[] args) throws Exception {
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }
}
