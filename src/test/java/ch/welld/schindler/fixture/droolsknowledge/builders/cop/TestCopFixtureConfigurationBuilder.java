package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.fixtures.FixtureConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test cop fixture configuration builder")
public class TestCopFixtureConfigurationBuilder {

    private CopFixtureConfigurationBuilder builder = new CopFixtureConfigurationBuilder();

    private Map<String, Object> createConfigurationRequest() {
        return new Maps.Builder<String,Object>()
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
                .build();
    }

    @Test
    @DisplayName("convert an alarm configuration")
    public void testParseAlarmConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("alarmButtons", BigDecimal.ONE);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        ComponentConfiguration config = configurations.get(0);
        assertEquals(1, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("ALARM", c.getFixtureType());
        assertEquals("PUSH", c.getPushType());
        assertEquals("🔔", c.getLabel());
    }

    @Test
    @DisplayName("convert an open door configuration")
    public void testParseOpenDoorConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("openButtons", BigDecimal.ONE);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        ComponentConfiguration config = configurations.get(0);
        assertEquals(1, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("DOOR", c.getFixtureType());
        assertEquals("PUSH", c.getPushType());
        assertEquals("◀|▶", c.getLabel());
    }

    @Test
    @DisplayName("convert a close door configuration")
    public void testParseCloseDoorConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("closeButtons", BigDecimal.ONE);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        ComponentConfiguration config = configurations.get(0);
        assertEquals(1, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("DOOR", c.getFixtureType());
        assertEquals("PUSH", c.getPushType());
        assertEquals("▶|◀", c.getLabel());
    }

    @Test
    @DisplayName("convert a floor configuration")
    public void testParseFloorConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("floorButtons", BigDecimal.ONE);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        ComponentConfiguration config = configurations.get(0);
        assertEquals(1, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("FLOOR", c.getFixtureType());
        assertEquals("PUSH", c.getPushType());
        assertEquals("-1,0,G,2", c.getLabel());
    }

    @Test
    @DisplayName("convert a main button configuration")
    public void testParseMainConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("mainFloorButton", true);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        ComponentConfiguration config = configurations.get(0);
        assertEquals(1, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("MAIN", c.getFixtureType());
        assertEquals("PUSH", c.getPushType());
        assertEquals("G", c.getLabel());
    }

    @Test
    @DisplayName("convert a hidden button configuration")
    public void testParseHiddenConfiguration() {
        Map<String, Object> configurationRequest = new Maps.Builder<String,Object>()
            .put("fixtureFamily", "hidden button")
            .put("designation", "-1,0,G,2")
            .put("quantity", new BigDecimal(4))
            .build();

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        assertNotNull(configurations);
        assertEquals(1, configurations.size());

        ComponentConfiguration config = configurations.get(0);
        assertEquals(4, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertEquals("HIDDEN", c.getFixtureFamily());
        assertEquals("HIDDEN", c.getFixtureType());
        assertEquals("-1,0,G,2", c.getLabel());
    }

    private ComponentConfiguration findConfigurationOfType(List<ComponentConfiguration> configurations, String type) {
        return configurations.stream().filter(c ->
                ((FixtureConfiguration) c.getConfiguration()).getFixtureType().equals(type)
        ).findFirst().get();
    }

    @Test
    @DisplayName("convert a combination of main and floor configurations")
    public void testParseFloorAndMainConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("mainFloorButton", true);
        configurationRequest.put("floorButtons", BigDecimal.valueOf(5));

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        assertNotNull(configurations);
        assertEquals(2, configurations.size());

        ComponentConfiguration mainConfig = findConfigurationOfType(configurations, "MAIN");
        ComponentConfiguration floorConfig = findConfigurationOfType(configurations, "FLOOR");

        assertEquals(1, mainConfig.getCount());
        assertEquals(5, floorConfig.getCount());

        assertEquals("-1,0,2", ((FixtureConfiguration) floorConfig.getConfiguration()).getLabel());
    }

    @Test
    @DisplayName("check that touch buttons are handled")
    public void testTouchButtonConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("closeButtons", BigDecimal.ONE);
        configurationRequest.put("pushType", "TOUCH");

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        assertEquals("TOUCH", ((FixtureConfiguration)configurations.get(0).getConfiguration()).getPushType());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                new Maps.Builder<String,Object>()
                    .put("mainFloorButton", true)
                    .put("backlight", "not a boolean")
                    .build()
            )
        );
    }

}
