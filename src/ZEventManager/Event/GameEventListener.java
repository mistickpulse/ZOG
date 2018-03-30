package ZEventManager.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameEventListener implements Listener {
    private ZEvent _currentEvt = null;

    GameEventListener(ZEvent evt) {
        _currentEvt = evt;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evt) {
        _currentEvt.addPlayer(evt.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt) {

    }
}
