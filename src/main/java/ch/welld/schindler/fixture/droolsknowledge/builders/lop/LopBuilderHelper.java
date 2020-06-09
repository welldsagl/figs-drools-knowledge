package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;

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
        return FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.TOP)
            + FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.BOTTOM)
            + 2 * FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.MIDDLE);
    }

    public static String getIndicatorFamily(Map<String, Object> config) {
        return (String) config.get("indicatorFamily");
    }

    public static String getFixtureFamily(Map<String, Object> config) {
        return (String) config.get("fixtureFamily");
    }

}
