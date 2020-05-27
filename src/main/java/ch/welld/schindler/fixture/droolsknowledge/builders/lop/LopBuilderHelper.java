package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import java.util.Map;

public class LopBuilderHelper {

    enum LopType {
        WITHOUT,
        SINGLE,
        DOUBLE
    }

    static String getLopType(Map<String, Object> config) {
        String lopTypeString = ((String) config.get("lopType")).toLowerCase();
        if (lopTypeString.startsWith("without")) {
            return LopType.WITHOUT.toString();
        }
        if (lopTypeString.startsWith("single")) {
            return LopType.SINGLE.toString();
        }
        if (lopTypeString.startsWith("two")) {
            return LopType.DOUBLE.toString();
        }
        return null;
    }

    static Integer getButtonCount(Map<String, Object> config) {
        String position = ((String) config.get("position")).toLowerCase();
        if (position.equals("middle")) {
            return 2;
        }
        return 1;
    }

    public static String getIndicatorFamily(Map<String, Object> config) {
        return (String) config.get("indicatorFamily");
    }

}
