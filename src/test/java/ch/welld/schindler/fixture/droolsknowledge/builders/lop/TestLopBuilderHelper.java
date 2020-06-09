package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("lop builder helper")
public class TestLopBuilderHelper {

    @Test
    @DisplayName("get a without indicator lop type")
    public void testGetLopTypeWithoutIndicator() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopType", "Without indicator");
        String lopType = LopBuilderHelper.getLopType(config);
        assertEquals("WITHOUT", lopType);
    }

    @Test
    @DisplayName("get a single indicator lop type")
    public void testGetLopTypeSingleIndicator() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopType", "Single indicator");
        String lopType = LopBuilderHelper.getLopType(config);
        assertEquals("SINGLE", lopType);
    }

    @Test
    @DisplayName("get a double indicator lop type")
    public void testGetLopTypeDoubleIndicator() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopType", "Two indicators");
        String lopType = LopBuilderHelper.getLopType(config);
        assertEquals("DOUBLE", lopType);
    }

    @Test
    @DisplayName("get a NULL lop type")
    public void testGetLopTypeNull() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopType", "Unknown");
        assertNull(LopBuilderHelper.getLopType(config));
    }

    @Test
    @DisplayName("get a total button count equals to top floors quantity if it is the only provided quantity")
    public void testGetTopFloorsButtonCount() {
        Map<String, Object> config = new HashMap<>();
        config.put("topFloors", new BigDecimal(5));
        assertEquals(5, LopBuilderHelper.getButtonCount(config));
    }

    @Test
    @DisplayName("get a total button count equals to bottom floors quantity if it is the only provided quantity")
    public void testGetBottomFloorsButtonCount() {
        Map<String, Object> config = new HashMap<>();
        config.put("bottomFloors", new BigDecimal(2));
        assertEquals(2, LopBuilderHelper.getButtonCount(config));
    }

    @Test
    @DisplayName("get a total button count equals to the double of middle floors quantity if it is the only provided quantity")
    public void testGetMiddleFloorsButtonCount() {
        Map<String, Object> config = new HashMap<>();
        config.put("middleFloors", new BigDecimal(5));
        assertEquals(10, LopBuilderHelper.getButtonCount(config));
    }

    @Test
    @DisplayName("get a total button count equals to the sum of top, bottom and middle floors quantities")
    public void testGetAllFloorsButtonCount() {
        Map<String, Object> config = new HashMap<>();
        config.put("topFloors", new BigDecimal(2));
        config.put("middleFloors", new BigDecimal(5));
        config.put("bottomFloors", new BigDecimal(3));
        assertEquals(15, LopBuilderHelper.getButtonCount(config));
    }

    @Test
    @DisplayName("get configuration's indicator family")
    public void testGetIndicatorFamily() {
        Map<String, Object> config = new HashMap<>();
        config.put("indicatorFamily", "the indicator family");
        assertEquals("the indicator family", LopBuilderHelper.getIndicatorFamily(config));
    }

    @Test
    @DisplayName("get configuration's fixture family")
    public void testGetFixtureFamily() {
        Map<String, Object> config = new HashMap<>();
        config.put("fixtureFamily", "the fixture family");
        assertEquals("the fixture family", LopBuilderHelper.getFixtureFamily(config));
    }
}
