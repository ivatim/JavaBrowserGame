package service.websocket;

import entity.game.Player;
import game.websocket.GameWebSocket;

public interface WebSocketService {
    void addPlayer(GameWebSocket user);
    void notifyMyNewScore(Player user);
    void notifyEnemyNewScore(Player user);
    void notifyStartGame(Player user);
    void notifyGameOver(Player user, boolean win);
}
