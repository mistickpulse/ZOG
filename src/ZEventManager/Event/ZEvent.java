package ZEventManager.Event;

import ZEventManager.Event.EventTrigger.Triggers.AEventTrigger;
import ZEventManager.Event.EventTrigger.Triggers.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ZEvent {
    private EventState _state = EventState.BEFORE_EVENT;
    private boolean _finished = false;
    private Map<TriggerType, AEventTrigger> _eventTriggers = null;
    private List<PlayerEventData> _playerCollection = null;
    private TreeMap<String, PlayerEventData> _eventData = null; //<PlayerName, EventData>
    private EventParams _params = null;
    private CoolDown _timer = null;

    public ZEvent(EventParams params) {
        System.out.print("[ZOG]Building Event");
        _timer = new CoolDown(params.evtDuration.mode);
        _params = params;
        _eventTriggers = new HashMap();
        _eventData = new TreeMap();
        _playerCollection = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            addPlayer(player);
        }
        __buildTriggers(params.triggerList);
        _timer.Start(params.evtDuration.PreEventPhaseDuration);
        Bukkit.getPluginManager().registerEvents(new GameEventListener(this), Bukkit.getPluginManager().getPlugin("ZEvent"));
        __updateLeaderBoard();
    }

    public EventState getState() {
        return _state;
    }

    public boolean isFinished() {
        return _finished;
    }

    public void notify(String playerName, TriggerType typeTriggered) {
        __updateScore(playerName, typeTriggered);
    }

    public void addPlayer(Player player) {
        if (!player.hasPermission("ZEvent.acessEvent") || _state == EventState.AFTER_EVENT) {
            return;
        }
        if (!_eventData.containsKey(player.getName())) {
            _eventData.put(player.getName(), new PlayerEventData(player, _playerCollection, _params));
        }
    }

    public boolean update() {
        if (_timer.getCount() == 0) {
            switch (_state) {
                case BEFORE_EVENT:
                    if (_playerCollection.size() == 0) {
                        Bukkit.broadcastMessage("Cancel Event -> There is Nobody");
                        _finished = true;
                        break;
                    }
                    _timer.Start(_params.evtDuration.EventDuration);
                    _state = EventState.DURING_EVENT;
                    break;
                case DURING_EVENT:
                    _timer.Start(_params.evtDuration.PostEventPhaseDuration);
                    _state = EventState.AFTER_EVENT;
                    break;
                case AFTER_EVENT:
                    _finished = true;
                    break;
            }
        }
        for (Map.Entry<String, PlayerEventData> entry : _eventData.entrySet()) {
            entry.getValue().update(_state, _timer);
        }
        return isFinished();
    }

    public void closeEvent() {
        for (Map.Entry<String, PlayerEventData> entry : _eventData.entrySet()) {
            entry.getValue().close();
        }
        for (Map.Entry<TriggerType, AEventTrigger> entry : _eventTriggers.entrySet()) {
        }

        _eventData = null;
        _eventTriggers = null;
        _playerCollection = null;
        _eventData = null;
        _params = null;
        _timer = null;
    }

    public void applyLoots() {
        int PlayerIdx = 0;
        if (_playerCollection.size() == 0) {
            return;
        }
        __updateLeaderBoard();
        System.out.print("Player getted " + _playerCollection.get(0).getPlayer().getName());
        for (String[] commandLoot : _params.loots._loots) {
            for (String command : commandLoot) {
                command = command.replaceAll("@p", _playerCollection.get(PlayerIdx).getPlayer().getName());
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
            }
            ++PlayerIdx;
        }
        _params.loots._loots.clear();
    }


    //Private
    private void __buildTriggers(ArrayList<AEventTrigger> TriggerList) {
        for (AEventTrigger trigger : TriggerList) {
            _eventTriggers.put(trigger.getType(), trigger);
            trigger.setEvt(this);
        }
    }

    private void __updateScore(String playerName, TriggerType type) {
        System.out.print("Updating Score");
        PlayerEventData tmp = null;
        for (Map.Entry<String, PlayerEventData> entry : _eventData.entrySet()) {
            if (entry.getKey().equals(playerName)) {
                tmp = entry.getValue();
            }
        }
        tmp.setScore(_eventTriggers.get(type).applyScore(tmp.getScore()));
        __updateLeaderBoard();
    }

    private void __updateLeaderBoard() {
        Comparator<String> comparator = new ScoreComparator(_eventData);
        TreeMap<String, PlayerEventData> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(_eventData);
        _eventData = sortedMap;
        _playerCollection.clear();
        for (Map.Entry<String, PlayerEventData> entry : _eventData.entrySet()) {
            _playerCollection.add(entry.getValue());
        }
    }
}
