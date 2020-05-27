package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("get a button count of 2")
    public void testGetButtonCountEquals2() {
        Map<String, Object> config = new HashMap<>();
        config.put("position", "MIDDLE");
        assertEquals(2, LopBuilderHelper.getButtonCount(config));
    }

    @Test
    @DisplayName("get a button count of 1")
    public void testGetButtonCountEquals1() {
        Map<String, Object> config = new HashMap<>();
        config.put("position", "Unknown");
        assertEquals(1, LopBuilderHelper.getButtonCount(config));
    }

    @Test
    @DisplayName("get configuration's indicator family")
    public void testGetIndicatorFamily() {
        Map<String, Object> config = new HashMap<>();
        config.put("indicatorFamily", "the indicator family");
        assertEquals("the indicator family", LopBuilderHelper.getIndicatorFamily(config));
    }

}
