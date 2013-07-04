package org.hm.search;

import java.util.HashMap;
import java.util.Map;

public class Estatistics {

    private Map<String, Integer> counters = new HashMap<String, Integer>();

    public void incCounter(String index) {
        if (!counters.containsKey(index)) {
            counters.put(index, 1);

        } else {
            counters.put(index, counters.get(index) + 1);
        }

    }

}
