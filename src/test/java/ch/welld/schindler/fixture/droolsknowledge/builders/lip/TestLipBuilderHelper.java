package ch.welld.schindler.fixture.droolsknowledge.builders.lip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("lip builder helper")
public class TestLipBuilderHelper {

    @Test
    @DisplayName("get a horizontal lip type")
    public void testGetHorizontalLipType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lipType", "Horizontal");
        String lipType = LipBuilderHelper.getLipType(config);
        assertEquals("HORIZONTAL", lipType);
    }

    @Test
    @DisplayName("get a vertical lip type")
    public void testGetVerticalLipType() {
        Map<String, Object> config = new HashMap<>();
        config.put("lipType", "Vertical");
        String lipType = LipBuilderHelper.getLipType(config);
        assertEquals("VERTICAL", lipType);
    }

    @Test
    @DisplayName("get a NULL lip type")
    public void testGetLipTypeNull() {
        Map<String, Object> config = new HashMap<>();
        config.put("lipType", "Unknown");
        assertNull(LipBuilderHelper.getLipType(config));
    }

    @Test
    @DisplayName("get configuration's indicator family")
    public void testGetIndicatorFamily() {
        Map<String, Object> config = new HashMap<>();
        config.put("indicatorFamily", "the indicator family");
        assertEquals("the indicator family", LipBuilderHelper.getIndicatorFamily(config));
    }

    @Test
    @DisplayName("get lip quantity")
    public void testGetLipQuantity() {
        Map<String, Object> config = new HashMap<>();
        config.put("quantity", new BigDecimal(3));
        assertEquals(3, LipBuilderHelper.getLipQuantity(config));
    }

}
