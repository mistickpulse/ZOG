package ZEventManager.Event.EventTrigger.Triggers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class FillBucketTrigger extends AEventTrigger implements Listener {


    public FillBucketTrigger() {
        super(TriggerType.BUKKETFILL);
    }

    @Override
    public Integer applyScore(Integer score) {
        score += 1;
        return score;
    }

    @EventHandler
    void onFill(PlayerBucketFillEvent evt) {
        if (super._evt != null) {
            super._evt.notify(evt.getPlayer().getName(), super.getType());
        }
    }

}
