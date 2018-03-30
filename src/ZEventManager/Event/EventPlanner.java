package ZEventManager.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventPlanner {
    private EventParams _params = null;
    private List<String> _planOn = null;
    private Boolean _startedCurrentMin = false;

    EventPlanner(EventParams params, List<String> _date) {
        _planOn = _date;
        _params = params;
    }

    EventParams isTheTime() {
        DateFormat format = new SimpleDateFormat("dd HHhmm");
        String CurrentDate = format.format(new Date());
        for (String date : _planOn) {
            if (date.equals(CurrentDate)) {
                _startedCurrentMin = true;
                return _params;
            }
        }
        return null;
    }
}
