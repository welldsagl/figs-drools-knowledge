package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("floors quantity helper")
public class TestFloorsQuantityHelper {

    @Test
    @DisplayName("get top floors count")
    public void testGetTopFloorsCount() {
        Map<String, Object> config = new HashMap<>();
        config.put("topFloors", new BigDecimal(4));
        assertEquals(4, FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.TOP));
    }

    @Test
    @DisplayName("get middle floors count")
    public void testGetMiddleFloorsCount() {
        Map<String, Object> config = new HashMap<>();
        config.put("middleFloors", new BigDecimal(4));
        assertEquals(4, FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.MIDDLE));
    }

    @Test
    @DisplayName("get bottom floors count")
    public void testGetBottomFloorsCount() {
        Map<String, Object> config = new HashMap<>();
        config.put("bottomFloors", new BigDecimal(4));
        assertEquals(4, FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.BOTTOM));
    }

    @Test
    @DisplayName("get total floors count")
    public void testGetTotalFloorsCount() {
        Map<String, Object> config = new HashMap<>();
        config.put("topFloors", new BigDecimal(1));
        config.put("middleFloors", new BigDecimal(2));
        config.put("bottomFloors", new BigDecimal(3));
        assertEquals(6, FloorsQuantityHelper.getTotalFloorsCount(config));
    }

}
