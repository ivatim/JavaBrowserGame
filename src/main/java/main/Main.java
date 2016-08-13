package main;

import frontend.Frontend;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author Igor Ivankov
 */
public class Main {

    public static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        Frontend frontend = new Frontend();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(frontend), "/authform");

        Server server = new Server(PORT);
        server.setHandler(context);

        server.start();
        server.join();
    }
}