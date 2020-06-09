package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import com.sun.istack.NotNull;

import java.math.BigDecimal;
import java.util.Map;

public class FloorsQuantityHelper {

    public enum FloorPosition {
        TOP,
        MIDDLE,
        BOTTOM
    }

    private static int getQuantityByKey(@NotNull Map<String,Object> config, String key) {
        return ((BigDecimal) config.getOrDefault(key, BigDecimal.ZERO)).intValue();
    }

    public static Integer getFloorsCount(@NotNull Map<String,Object> config, FloorPosition floorPosition) {
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
