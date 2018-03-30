package ZEventManager.Event.EventTrigger.Triggers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerKillTrigger extends AEventTrigger implements Listener {


    public PlayerKillTrigger() {
        super(TriggerType.PLAYERKILL);
    }

    @Override
    public Integer applyScore(Integer score) {
        score += 1;
        return score;
    }

    @EventHandler
    void onDeath(PlayerDeathEvent evt) {
        super._evt.notify(evt.getEntity().getKiller().getPlayer().getName(), super.getType());

    }

}
