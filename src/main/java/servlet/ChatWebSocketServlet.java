package servlet;

import chat.websocket.ChatWebSocketCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ChatWebSocketServlet", urlPatterns = {"/chat"})
public class ChatWebSocketServlet extends WebSocketServlet {
    static final Logger logger = LogManager.getLogger(ChatWebSocketServlet.class);
    private final static int LOGOUT_TIME = 10 * 60 * 1000;

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator(new ChatWebSocketCreator());
        logger.info("Socket servlet configured");
    }
}
