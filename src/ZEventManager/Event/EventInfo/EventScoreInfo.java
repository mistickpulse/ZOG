package ZEventManager.Event.EventInfo;


enum EventScoreMode {
    CASUAL,
    INFINITE
}

public class EventScoreInfo {
    public EventScoreMode mode = EventScoreMode.CASUAL;
    public Integer ScoreToWin = -1; // Ignored if mode is casual
}
