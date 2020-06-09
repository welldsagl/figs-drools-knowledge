package ch.welld.schindler.fixture.droolsknowledge.builders.lopl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("lop l builder helper")
public class TestLopLBuilderHelper {

    @Test
    @DisplayName("get a 100x110 lop k type")
    public void testGet100x110LopLType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopLType", "100x110");
        String lopType = LopLBuilderHelper.getLopLType(config);
        assertEquals("100x110", lopType);
    }

    @Test
    @DisplayName("get a 100x160 lop k type")
    public void testGet100x160LopLType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopLType", "100x160");
        String lopType = LopLBuilderHelper.getLopLType(config);
        assertEquals("100x160", lopType);
    }

    @Test
    @DisplayName("get a 180x110 lop k type")
    public void testGet180x110LopLType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopLType", "180x110");
        String lopType = LopLBuilderHelper.getLopLType(config);
        assertEquals("180x110", lopType);
    }

    @Test
    @DisplayName("get a 180x160 lop k type")
    public void testGet180x160LopLType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopLType", "180x160");
        String lopType = LopLBuilderHelper.getLopLType(config);
        assertEquals("180x160", lopType);
    }

    @Test
    @DisplayName("get null if requested lop k type is not supported")
    public void testExceptionForWrongLopLType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopLType", "another one");
        assertNull(LopLBuilderHelper.getLopLType(config));
    }

}
