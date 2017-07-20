package main;

import interfaces.AccountService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import services.AccountServiceImpl;
import servlets.AdminServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import javax.servlet.Servlet;

/**
 * @author Igor Ivankov
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getSimpleName());

    public static final int DEFAULT_PORT = 8888;
    public static final String RESOURCE_BASE = "static";

    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountServiceImpl();

        Servlet signUpServlet = new SignUpServlet(accountService);
        Servlet signInServlet = new SignInServlet(accountService);
        Servlet adminServlet = new AdminServlet(accountService);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(signUpServlet), SignUpServlet.SIGN_UP_PAGE_URL);
        servletContextHandler.addServlet(new ServletHolder(signInServlet), SignInServlet.SIGN_IN_PAGE_URL);
        servletContextHandler.addServlet(new ServletHolder(adminServlet), AdminServlet.ADMIN_PAGE_URL);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(RESOURCE_BASE);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, servletContextHandler});

        int port = args.length == 1 ? Integer.valueOf(args[0]) : DEFAULT_PORT;
        Server server = new Server(port);
        server.setHandler(handlerList);

        logger.log(Level.TRACE,"tracefffff");
        logger.log(Level.DEBUG,"DEBUGaerf");
        logger.log(Level.INFO,"INFOerwer");
        logger.info("WIHTOUT LEVEL");
        logger.log(Level.WARN,"WARNwer");
        logger.log(Level.ERROR,"ERrowerr");
        logger.log(Level.FATAL,"FAtal");
        server.start();
        server.join();
    }
}
