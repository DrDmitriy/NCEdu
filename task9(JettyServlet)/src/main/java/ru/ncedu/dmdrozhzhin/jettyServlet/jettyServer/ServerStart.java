package ru.ncedu.dmdrozhzhin.jettyServlet.jettyServer;

import ru.ncedu.dmdrozhzhin.jettyServlet.Servlet.ProcessingServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerStart {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        ServletHolder servletHolder = new ServletHolder(ProcessingServlet.class);
        contextHandler.addServlet(servletHolder, "/");
        server.setHandler(contextHandler);
        server.start();

    }
}
