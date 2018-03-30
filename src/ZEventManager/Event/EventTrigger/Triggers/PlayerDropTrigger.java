package ZEventManager.Event.EventTrigger.Triggers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropTrigger extends AEventTrigger implements Listener {

    public PlayerDropTrigger() {
        super(TriggerType.PLAYERDROP);
    }

    @Override
    public Integer applyScore(Integer score) {
        score += 1;
        return score;
    }

    @EventHandler
    void onFill(PlayerDropItemEvent evt) {
        if (super._evt != null) {
            super._evt.notify(evt.getPlayer().getName(), super.getType());
        }
    }

}
