package entity.game;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GameSession {
    private final long startTime;
    private final Player first;
    private final Player second;

    private Map<String, Player> users = new HashMap<>();

    public GameSession(String user1, String user2) {
        startTime = new Date().getTime();
        Player Player1 = new Player(user1);
        Player1.setEnemyName(user2);

        Player Player2 = new Player(user2);
        Player2.setEnemyName(user1);

        users.put(user1, Player1);
        users.put(user2, Player2);

        this.first = Player1;
        this.second = Player2;
    }

    public Player getEnemy(String user) {
        String enemyName = users.get(user).getEnemyName();
        return users.get(enemyName);
    }

    public Player getSelf(String user) {
        return users.get(user);
    }

    public long getSessionTime(){
        return new Date().getTime() - startTime;
    }

    public Player getFirst() {
        return first;
    }

    public Player getSecond() {
        return second;
    }

    public  boolean isFirstWin(){
        return first.getMyScore() > second.getMyScore();
    }
}
