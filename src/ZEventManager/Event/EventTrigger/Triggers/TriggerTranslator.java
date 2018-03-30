package ZEventManager.Event.EventTrigger.Triggers;

public enum TriggerTranslator {
    BUKKET("BUKKETFILL", TriggerType.BUKKETFILL),
    PLAYERKILL("PLAYERKILL", TriggerType.PLAYERKILL),
    PLAYERDROP("PLAYERDROP", TriggerType.PLAYERDROP);


    private String _TriggerId;
    private TriggerType _type;

    TriggerTranslator(String id, TriggerType type) {
        _TriggerId = id;
        _type = type;
    }

    public String getId() {
        return _TriggerId;
    }

    public TriggerType getType() {
        return _type;
    }
}
