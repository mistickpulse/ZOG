package ZEventManager.Event;

import ZEventManager.Event.EventInfo.EventDurationMode;
import ZEventManager.ZEventManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class CoolDown {
    private EventDurationMode _mode = EventDurationMode.CASUAL;
    private int count = 0;
    private BukkitTask task = null;

    public CoolDown(EventDurationMode mode) {
        if (_mode == EventDurationMode.CASUAL) {
            task = new BukkitRunnable() {
                public void run() {
                    if (count > 0) {
                        --count;
                    }
                    ZEventManager tmp = (ZEventManager) Bukkit.getPluginManager().getPlugin("ZEvent");
                    tmp.instance.updateEvent();
                }
            }.runTaskTimer(Bukkit.getPluginManager().getPlugin("ZEvent"), 20L, 20L);
        } else {
            task = new BukkitRunnable() {
                public void run() {
                    ZEventManager tmp = (ZEventManager) Bukkit.getPluginManager().getPlugin("ZEvent");
                    tmp.instance.Update();
                }
            }.runTaskTimer(Bukkit.getPluginManager().getPlugin("ZEvent"), 20L, 20L);
        }
    }

    public void Start(int Cooldown) {
        count = Cooldown;
    }

    public void Stop() {
        task.cancel();
        count = 0;
    }

    public int getCount() {
        return count;
    }
}
