package ZEventManager.Event.EventTrigger.Triggers;

import ZEventManager.Event.ZEvent;

public abstract class AEventTrigger {
    protected ZEvent _evt = null;
    protected Boolean active = false;
    protected TriggerType _type;

    AEventTrigger(TriggerType type) {
    }

    public void setEvt(ZEvent evt) {
        _evt = evt;
    }

    public TriggerType getType() {
        return _type;
    }

    public abstract Integer applyScore(Integer score);

    public void setOff() {
        active = false;
    }

    public void setOn() {
        active = true;
    }

    void closeTrigger() {
        _evt = null;
    }
}
