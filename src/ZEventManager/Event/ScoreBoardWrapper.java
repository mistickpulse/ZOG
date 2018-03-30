package ZEventManager.Event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class ScoreBoardWrapper {
    private EventParams _params = null;
    private Player _player = null;
    private Scoreboard _scoreboard = null;
    private Objective _sidebar = null;
    private List<PlayerEventData> _topPlayers = null;

    ScoreBoardWrapper(Player player, List<PlayerEventData> topPlayers, EventParams params) {
        _params = params;
        _player = player;
        _topPlayers = topPlayers;
        _scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    }


    public void update(EventState state, Integer time, Integer score) {
        if (_sidebar != null) {
            _sidebar.unregister();
        }
        _sidebar = _scoreboard.registerNewObjective("ZEvent", "dummy");
        _sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        _sidebar.setDisplayName(ChatColor.BLUE + "======" + ChatColor.RED + _params.EventName + ChatColor.BLUE + "======");
        switch (state) {
            case BEFORE_EVENT:
                __beforeEventDump(time, score);
                break;
            case DURING_EVENT:
                __duringEventDump(time, score);
                break;
            case AFTER_EVENT:
                __afterEventDump(time, score);
                break;
        }
        _player.setScoreboard(_scoreboard);
    }


    //private

    private void __beforeEventDump(Integer timer, Integer score) {
        _sidebar.getScore("").setScore(15);
        _sidebar.getScore(ChatColor.BLUE + " Time Before Event :  " + ChatColor.YELLOW + __getTimeFormat(timer)).setScore(14);
        _sidebar.getScore("").setScore(13);
        _sidebar.getScore(ChatColor.GREEN + " Use " + ChatColor.YELLOW + "/ZEvent desc" + ChatColor.GREEN + " to see").setScore(12);
        _sidebar.getScore(ChatColor.GREEN + " the event description").setScore(11);
    }

    private void __duringEventDump(Integer timer, Integer score) {
        _sidebar.getScore("").setScore(15);
        _sidebar.getScore(ChatColor.RED + " Time Left :   " + ChatColor.YELLOW + __getTimeFormat(timer)).setScore(14);
        _sidebar.getScore(ChatColor.GREEN + "------").setScore(13);
        _sidebar.getScore(ChatColor.GREEN + " Score :" + Integer.toString(score)).setScore(12);
        _sidebar.getScore(ChatColor.GREEN + "------").setScore(13);
        _sidebar.getScore(ChatColor.GREEN + " Position :" + Integer.toString(__getPlayerPos()) + "/" + Integer.toString(_topPlayers.size())).setScore(10);
        _sidebar.getScore(ChatColor.BLUE + "====================").setScore(9);
        _sidebar.getScore("").setScore(8);

        int offset = 7;
        for (int PlayerIdx = 0; offset > 0; --offset) {
            if (PlayerIdx == _topPlayers.size()) {
                break;
            }
            PlayerEventData tmp = _topPlayers.get(PlayerIdx);
            _sidebar.getScore(ChatColor.YELLOW + Integer.toString(PlayerIdx + 1) + ". " + tmp.getPlayer().getName() + " " + Integer.toString(tmp.getScore())).setScore(offset);
            ++PlayerIdx;
        }
    }

    private int __getPlayerPos() {
        int it = 1;
        for (PlayerEventData EvtData : _topPlayers) {
            if (_player.getName() == EvtData.getPlayer().getName()) {
                break;
            }
            ++it;
        }
        return it;
    }

    private void __afterEventDump(Integer timer, Integer score) {
        _sidebar.getScore("").setScore(15);
        _sidebar.getScore(ChatColor.BLUE + "Congrats You made it !").setScore(14);
        _sidebar.getScore(ChatColor.RED + "Your score: " + Integer.toString(score)).setScore(13);
        _sidebar.getScore(ChatColor.GREEN + "You are ranked:" + Integer.toString(__getPlayerPos()) + " in " + Integer.toString(_topPlayers.size())).setScore(12);
    }

    private String __getTimeFormat(Integer time) {
        return Integer.toString(time / 60) + ":" + Integer.toString(time % 60);
    }

    public void close() {
        _sidebar.unregister();
    }
}
