package ZEventManager.Event;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ScoreComparator implements Comparator<String> {
    private HashMap<String, PlayerEventData> _map = new HashMap<>();

    ScoreComparator(Map<String, PlayerEventData> map) {
        _map.putAll(map);
    }

    @Override
    public int compare(String s1, String s2) {
        if (_map.get(s1).getScore() < _map.get(s2).getScore()) {
            return 1;
        }
        return -1;
    }
}
