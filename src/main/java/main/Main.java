package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import services.AccountService;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import javax.servlet.Servlet;

/**
 * @author Igor Ivankov
 */
public class Main {

    public static final int DEFAULT_PORT = 8888;
    public static final String RESOURCE_BASE = "public_html";

    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService();

        Servlet signUpServlet = new SignUpServlet(accountService);
        Servlet signInServlet = new SignInServlet(accountService);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(signUpServlet), "/signup");
        servletContextHandler.addServlet(new ServletHolder(signInServlet), "/signin");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(RESOURCE_BASE);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, servletContextHandler});

        int port = args.length == 1 ? Integer.valueOf(args[0]) : DEFAULT_PORT;
        Server server = new Server(port);
        server.setHandler(handlerList);

        server.start();
        server.join();
    }
}
