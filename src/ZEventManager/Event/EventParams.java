package ZEventManager.Event;

import ZEventManager.Event.EventInfo.EventDurationInfo;
import ZEventManager.Event.EventInfo.EventLoot;
import ZEventManager.Event.EventInfo.EventScoreInfo;
import ZEventManager.Event.EventTrigger.Triggers.AEventTrigger;

import java.util.ArrayList;

public class EventParams {
    public ArrayList<AEventTrigger> triggerList = null;

    public String EventName = null;
    public EventDurationInfo evtDuration = null;
    public EventScoreInfo evtScoreInfo = null;

    public EventParams() {
        triggerList = new ArrayList<>();
        loots = new EventLoot();
        evtScoreInfo = new EventScoreInfo();
        evtDuration = new EventDurationInfo();
    }
    public EventLoot loots = null;
}
