package ZEventManager.Event.EventTrigger;

import ZEventManager.Event.EventTrigger.Triggers.*;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class TriggerFactory {
    private HashMap<TriggerType, TriggerGenerator> _triggerModels = new HashMap();

    public TriggerFactory() {
        _triggerModels.put(TriggerType.BUKKETFILL, () -> {
            FillBucketTrigger tmp = new FillBucketTrigger();
            Bukkit.getPluginManager().registerEvents(tmp, Bukkit.getPluginManager().getPlugin("ZEvent"));
            return tmp;
        });
        _triggerModels.put(TriggerType.PLAYERKILL, () -> {
            PlayerKillTrigger tmp = new PlayerKillTrigger();
            Bukkit.getPluginManager().registerEvents(tmp, Bukkit.getPluginManager().getPlugin("ZEvent"));
            return tmp;
        });
         _triggerModels.put(TriggerType.PLAYERDROP, () -> {
            PlayerDropTrigger tmp = new PlayerDropTrigger();
            Bukkit.getPluginManager().registerEvents(tmp, Bukkit.getPluginManager().getPlugin("ZEvent"));
            return tmp;
        });
    }

    public AEventTrigger generate(TriggerType type) {
        return _triggerModels.get(type).gen();
    }

}
