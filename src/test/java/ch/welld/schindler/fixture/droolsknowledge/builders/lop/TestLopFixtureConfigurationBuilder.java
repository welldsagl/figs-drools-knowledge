package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.fixtures.FixtureConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("lop fixture configuration builder")
public class TestLopFixtureConfigurationBuilder {

    LopFixtureConfigurationBuilder builder = new LopFixtureConfigurationBuilder();

    private Map<String, Object> createConfigurationRequest(int topFloors, int middleFloors, int bottomFloors) {
        return new Maps.Builder<String, Object>()
            .put("backlight", true)
            .put("color", "amber")
            .put("fixtureFamily", "DT")
            .put("buttons", "DT4")
            .put("hairlineInsert", true)
            .put("letterRaised", true)
            .put("braille", true)
            .put("illumination", true)
            .put("buzzer", true)
            .put("fiveDot", true)
            .put("mainFloor", "G")
            .put("floorDesignation", "-1,0,G,2")
            .put("ldtO", true)
            .put("topFloors", new BigDecimal(topFloors))
            .put("middleFloors", new BigDecimal(middleFloors))
            .put("bottomFloors", new BigDecimal(bottomFloors))
            .build();
    }

    @Test
    @DisplayName("get one button configuration for down buttons if only top floors are provided")
    public void testOnlyTopFloorsConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest(2,0,0);
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        ComponentConfiguration config = configurations.get(0);
        assertEquals(2, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("FLOOR", c.getFixtureType());
        assertEquals("PUSH", c.getPushType());
        assertEquals("▼", c.getLabel());
    }

    @Test
    @DisplayName("get one button configuration for up buttons if only bottom floors are provided")
    public void testOnlyBottomFloorsConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest(0,0,2);
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        ComponentConfiguration config = configurations.get(0);
        assertEquals(2, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("FLOOR", c.getFixtureType());
        assertEquals("PUSH", c.getPushType());
        assertEquals("▲", c.getLabel());
    }

    @Test
    @DisplayName("get one button configuration for up buttons and one for down buttons if only middle floors are provided")
    public void testOnlyMiddleFloorsConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest(0,2,0);
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertEquals(2, configurations.size());
        ComponentConfiguration upButtonsConfig = configurations.get(0);
        ComponentConfiguration downButtonsConfig = configurations.get(1);
        assertEquals(2, upButtonsConfig.getCount());
        assertEquals(2, downButtonsConfig.getCount());
        assertTrue(upButtonsConfig.getConfiguration() instanceof FixtureConfiguration);
        assertTrue(downButtonsConfig.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration upButtonsFixtureConfig = (FixtureConfiguration) upButtonsConfig.getConfiguration();
        FixtureConfiguration downButtonsFixtureConfig = (FixtureConfiguration) downButtonsConfig.getConfiguration();
        assertEquals("FLOOR", upButtonsFixtureConfig.getFixtureType());
        assertEquals("PUSH", upButtonsFixtureConfig.getPushType());
        assertEquals("▲", upButtonsFixtureConfig.getLabel());
        assertEquals("FLOOR", downButtonsFixtureConfig.getFixtureType());
        assertEquals("PUSH", downButtonsFixtureConfig.getPushType());
        assertEquals("▼", downButtonsFixtureConfig.getLabel());
    }

    @Test
    @DisplayName("get the sum of requested quantities if all floor types are provided")
    public void testAllFloorTypesConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest(2,3,4);
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertEquals(2, configurations.size());
        ComponentConfiguration upButtonsConfig = configurations.get(0);
        ComponentConfiguration downButtonsConfig = configurations.get(1);
        assertEquals(7, upButtonsConfig.getCount()); // 3 from middle floors and 4 from bottom floors
        assertEquals(5, downButtonsConfig.getCount()); // 3 from middle floors and 2 from up floors
        assertTrue(upButtonsConfig.getConfiguration() instanceof FixtureConfiguration);
        assertTrue(downButtonsConfig.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration upButtonsFixtureConfig = (FixtureConfiguration) upButtonsConfig.getConfiguration();
        FixtureConfiguration downButtonsFixtureConfig = (FixtureConfiguration) downButtonsConfig.getConfiguration();
        assertEquals("FLOOR", upButtonsFixtureConfig.getFixtureType());
        assertEquals("PUSH", upButtonsFixtureConfig.getPushType());
        assertEquals("▲", upButtonsFixtureConfig.getLabel());
        assertEquals("FLOOR", downButtonsFixtureConfig.getFixtureType());
        assertEquals("PUSH", downButtonsFixtureConfig.getPushType());
        assertEquals("▼", downButtonsFixtureConfig.getLabel());
    }

    @Test
    @DisplayName("convert into an empty list if button quantity is zero")
    public void testNoButtonsConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest(0,0,0);
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertTrue(configurations.isEmpty());
    }

}
