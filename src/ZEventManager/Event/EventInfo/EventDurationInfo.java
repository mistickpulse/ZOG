package ZEventManager.Event.EventInfo;



public class EventDurationInfo {
    // public EventDurationInfo(EventDurationMode mode, Integer PreEventDuration, Integer EventDuration, Integer AfterEventDuration)  {
    //
    // }

    public EventDurationMode mode = EventDurationMode.CASUAL;
    public Integer EventDuration = 20;//60 * 5; //Default 5 min (in seconds)
    public Integer PreEventPhaseDuration = 10;//60 * 5; // Default 5 min
    public Integer PostEventPhaseDuration = 15;//30; // Default 30 sec
}
