package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import com.sun.istack.NotNull;

import java.math.BigDecimal;
import java.util.Map;

public class LopBuilderHelper {

    enum LopType {
        WITHOUT,
        SINGLE,
        DOUBLE
    }

    enum FloorPosition {
        TOP,
        MIDDLE,
        BOTTOM
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
        return getFloorsCount(config, FloorPosition.TOP)
            + getFloorsCount(config, FloorPosition.BOTTOM)
            + 2 * getFloorsCount(config, FloorPosition.MIDDLE);
    }

    public static String getIndicatorFamily(Map<String, Object> config) {
        return (String) config.get("indicatorFamily");
    }

    public static String getFixtureFamily(Map<String, Object> config) {
        return (String) config.get("fixtureFamily");
    }

    private static int getQuantityByKey(@NotNull Map<String,Object> config, String key) {
        return ((BigDecimal) config.getOrDefault(key, BigDecimal.ZERO)).intValue();
    }

    static Integer getFloorsCount(@NotNull Map<String,Object> config, FloorPosition floorPosition) {
        switch (floorPosition) {
            case TOP:
                return getQuantityByKey(config, "topFloors");
            case MIDDLE:
                return getQuantityByKey(config, "middleFloors");
            case BOTTOM:
                return getQuantityByKey(config, "bottomFloors");
            default:
                return 0;
        }
    }

    public static Integer getTotalFloorsCount(Map<String, Object> config) {
        return getFloorsCount(config, FloorPosition.TOP)
            + getFloorsCount(config, FloorPosition.BOTTOM)
            + getFloorsCount(config, FloorPosition.MIDDLE);
    }

}
