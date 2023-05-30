package cse.java2.project.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PairParser {

    private PairParser() {

    }

    public static List<Pair> MapToList(Map<String, Integer> map) {
        List<Pair> pairs = new ArrayList<>();
        map.forEach((s, i) -> {
            pairs.add(new Pair(s, i));
        });
        return pairs;
    }
}
