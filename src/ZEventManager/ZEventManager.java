package ZEventManager;

import ZEventManager.Commands.CommandHandler;
import ZEventManager.Event.*;
import ZEventManager.Event.EventInfo.EventDurationMode;
import ZEventManager.Event.EventTrigger.TriggerFactory;
import ZEventManager.Event.EventTrigger.Triggers.AEventTrigger;
import ZEventManager.Event.EventTrigger.Triggers.TriggerTranslator;
import ZEventManager.Event.EventTrigger.Triggers.TriggerType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZEventManager extends JavaPlugin {
    public ZEventManager instance = null;
    List<EventPlanner> _planning = null;
    Map<String, EventParams> _eventParams = null;
    Map<TriggerType, AEventTrigger> _triggers = null;
    FileConfiguration _config = null;
    CoolDown _clock = null;
    ZEvent _currentEvt = null;
    private TriggerFactory _factory = null;

    public ZEventManager getInstance() {
        return instance;
    }

    public ZEventManager() {
    }

    public void onReload() {

    }

    public void createEvent(String eventId) {
        EventParams testParam = new EventParams();
        switch (eventId) {
            case "FillBucket":
                testParam.EventName = "Fill Bucket";
                String[] LootsBucket = {"give @p minecraft:stone 10", "give @p minecraft:book 3"};

                testParam.loots._loots.add(LootsBucket);
                testParam.triggerList.add(_triggers.get(TriggerType.BUKKETFILL));
                _currentEvt = new ZEvent(testParam);
                break;
            case "DropItem":
                testParam.EventName = "Drop Item";
                String[] LootsItem = {"give @p minecraft:dirt 10", "give @p minecraft:iron_ingot 3"};

                testParam.loots._loots.add(LootsItem);
                testParam.triggerList.add(_triggers.get(TriggerType.PLAYERDROP));
                _currentEvt = new ZEvent(testParam);
                break;
            case "KillEvent":
                testParam.EventName = "Pvp Event";
                String[] LootsPvp = {"give @p minecraft:diamond_chestplate", "give @p minecraft:gold_ingot 3"};
                testParam.loots._loots.add(LootsPvp);
                testParam.triggerList.add(_triggers.get(TriggerType.PLAYERKILL));
                _currentEvt = new ZEvent(testParam);
        }
    }

    public void Update() {
        if (_currentEvt != null && _currentEvt.isFinished() || _currentEvt == null) {

        }
    }

    public void updateEvent() {
        if (_currentEvt == null) {
            return;
        }

        _currentEvt.update();
        if (_currentEvt.getState() == EventState.AFTER_EVENT) {
            _currentEvt.applyLoots();
        }

        if (_currentEvt.isFinished()) {
            _currentEvt.closeEvent();
            _currentEvt = null;
        }
    }

    public ZEvent getCurrentEvent() {
        return _currentEvt;
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDisable() {
        _currentEvt = null;
        _eventParams = null;
    }

    @Override
    public void onEnable() {
        _planning = new ArrayList();
        instance = this;
        _triggers = new HashMap<>();
        _factory = new TriggerFactory();
        _eventParams = new HashMap<>();
        __registerCommand();
        __registerTrigers();
        _clock = new CoolDown(EventDurationMode.INFINITE);
        //__loadConfig();
        //__runTest();//not needed anymore (when config File should be finished)
    }

    // Private

    private void __loadConfig() {
        _config = getConfig();
    }

    private void __runTest() {

    }

    private void __registerTrigers() {
        for (TriggerTranslator trsltor : TriggerTranslator.values()) {
            _triggers.put(trsltor.getType(), _factory.generate(trsltor.getType()));
        }
    }

    private void __registerCommand() {
        this.getCommand("ZEvent").setExecutor(new CommandHandler());
    }
}
