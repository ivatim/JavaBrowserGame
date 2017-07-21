package servlet;

import game.mechanics.GameMechanics;
import game.websocket.GameWebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import service.auth.AuthService;
import service.websocket.WebSocketService;

public class GameWebSocketServlet extends WebSocketServlet {
    public static final String GAME_WEB_SOCKET_URL = "/gameplay";

    private final static int IDLE_TIME = 60 * 1000;
    private AuthService authService;
    private GameMechanics gameMechanics;
    private WebSocketService webSocketService;

    public GameWebSocketServlet(AuthService authService,
                GameMechanics gameMechanics,
                WebSocketService webSocketService) {
            this.authService = authService;
            this.gameMechanics = gameMechanics;
            this.webSocketService = webSocketService;
        }

        @Override
        public void configure(WebSocketServletFactory factory) {
            factory.getPolicy().setIdleTimeout(IDLE_TIME);
            factory.setCreator(new GameWebSocketCreator(authService, gameMechanics, webSocketService));
        }
}
