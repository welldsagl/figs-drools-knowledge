package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.fixtures.FixtureConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("lop fixture configuration builder")
public class TestLopFixtureConfigurationBuilder {

    LopFixtureConfigurationBuilder builder = new LopFixtureConfigurationBuilder();

    private Map<String, Object> createConfigurationRequest(String position) {
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
            .put("position", position)
            .build();
    }

    @Test
    @DisplayName("get a button configuration for top position lop")
    public void testTopPositionConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest("Top");
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        ComponentConfiguration config = configurations.get(0);
        assertEquals(1, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("FLOOR", c.getFixtureType());
        assertEquals("PUSH", c.getPushType());
        assertEquals("▼", c.getLabel());
    }

    @Test
    @DisplayName("get a button configuration for bottom position lop")
    public void testBottomPositionConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest("Bottom");
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        ComponentConfiguration config = configurations.get(0);
        assertEquals(1, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("FLOOR", c.getFixtureType());
        assertEquals("PUSH", c.getPushType());
        assertEquals("▲", c.getLabel());
    }

    @Test
    @DisplayName("get a button configuration for middle position lop")
    public void testMiddlePositionConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest("Middle");
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertEquals(2, configurations.size());
    }

}
