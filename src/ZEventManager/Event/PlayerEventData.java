package ZEventManager.Event;

import org.bukkit.entity.Player;

import java.util.List;

public class PlayerEventData {
    List<PlayerEventData> _topplayers = null;
    EventParams _params;
    private Player _currentPlayer = null;
    private Integer _score = null;
    private ScoreBoardWrapper _board = null;

    PlayerEventData(Player player, List<PlayerEventData> topPlayers, EventParams params) {
        _params = params;
        _board = new ScoreBoardWrapper(player, topPlayers, params);
        _topplayers = topPlayers;
        _currentPlayer = player;
        _score = 0;
    }

    public void update(EventState state, CoolDown timer) {
        _board.update(state, timer.getCount(), _score);
    }

    public void close() {
        _board.close();
    }

    public Player getPlayer() {
        return _currentPlayer;
    }

    public Integer getScore() {
        return _score;
    }

    public void setScore(Integer newScore) {
        _score = newScore;
    }

    public String getPlayerName() {
        return _currentPlayer.getName();
    }
}
