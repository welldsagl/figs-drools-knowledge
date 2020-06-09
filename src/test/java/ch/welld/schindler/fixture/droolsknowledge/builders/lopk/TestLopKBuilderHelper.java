package ch.welld.schindler.fixture.droolsknowledge.builders.lopk;

import ch.welld.schindler.fixture.droolsknowledge.builders.lop.LopBuilderHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("lop k builder helper")
public class TestLopKBuilderHelper {

    @Test
    @DisplayName("get a 100x110 lop k type")
    public void testGet100x110LopKType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopKType", "100x110");
        String lopType = LopKBuilderHelper.getLopKType(config);
        assertEquals("100x110", lopType);
    }

    @Test
    @DisplayName("get a 100x160 lop k type")
    public void testGet100x160LopKType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopKType", "100x160");
        String lopType = LopKBuilderHelper.getLopKType(config);
        assertEquals("100x160", lopType);
    }

    @Test
    @DisplayName("get a 180x110 lop k type")
    public void testGet180x110LopKType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopKType", "180x110");
        String lopType = LopKBuilderHelper.getLopKType(config);
        assertEquals("180x110", lopType);
    }

    @Test
    @DisplayName("get a 180x160 lop k type")
    public void testGet180x160LopKType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopKType", "180x160");
        String lopType = LopKBuilderHelper.getLopKType(config);
        assertEquals("180x160", lopType);
    }

    @Test
    @DisplayName("get null if requested lop k type is not supported")
    public void testExceptionForWrongLopKType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lopKType", "another one");
        assertNull(LopKBuilderHelper.getLopKType(config));
    }

}
