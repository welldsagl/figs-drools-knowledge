package ch.welld.schindler.fixture.droolsknowledge.builders;

import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.fixtures.FixtureConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test fixture configuration builder")
public class TestFixtureConfigurationBuilder {

    private FixtureConfigurationBuilder builder = new FixtureConfigurationBuilder();

    @Test
    @DisplayName("can convert a fixture configuration")
    public void testCanParseFixtureConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("elevator", "Fixtures"))
        );
    }

    @Test
    @DisplayName("cannot convert a not fixture configuration")
    public void testCannotParseNonFixtureConfiguration() {
        assertFalse(
            builder.canParseConfiguration(Collections.singletonMap("elevator", "Lamps"))
        );
    }

    @Test
    @DisplayName("cannot convert a configuration with no 'elevator' field")
    public void testCannotParseConfigurationWithNoElevatorKey() {
        assertFalse(
            builder.canParseConfiguration(Collections.emptyMap())
        );
    }

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
                .put("ldtO", "true")
                .build();
    }

    private void commonCheck(
            List<ComponentConfiguration> configurations,
            String fixtureType,
            String doorButtonType,
            String label,
            String pushType) {
        assertNotNull(configurations);
        assertEquals(1, configurations.size());

        ComponentConfiguration config = configurations.get(0);
        assertEquals(1, config.getCount());
        assertTrue(config.getConfiguration() instanceof FixtureConfiguration);
        FixtureConfiguration c = (FixtureConfiguration) config.getConfiguration();
        assertTrue(c.getBacklight());
        assertTrue(c.getHairlineInsert());
        assertTrue(c.getLetterRaised());
        assertTrue(c.getBacklight());
        assertTrue(c.getIllumination());
        assertTrue(c.getBuzzer());
        assertTrue(c.getFiveDot());
        assertEquals(NullableBoolean.YES, c.getLdtO());
        assertEquals("AMBER", c.getButtonColor());
        assertEquals("DT", c.getFixtureFamily());
        assertEquals("DT4", c.getFixtureSubfamily());

        if (fixtureType != null) {
            assertEquals(fixtureType, c.getFixtureType());
        }
        if (doorButtonType != null) {
            assertEquals(doorButtonType, c.getDoorButtonType());
        }
        if (pushType != null) {
            assertEquals(pushType, c.getPushType());
        }
        assertEquals(label, c.getLabel());
    }

    @Test
    @DisplayName("convert an alarm configuration")
    public void testParseAlarmConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("alarmButtons", BigDecimal.ONE);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        commonCheck(configurations, "ALARM", null, null, "PUSH");
    }

    @Test
    @DisplayName("convert an open door configuration")
    public void testParseOpenDoorConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("openButtons", BigDecimal.ONE);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        commonCheck(configurations, "DOOR", "OPEN", null, "PUSH");
    }

    @Test
    @DisplayName("convert a close door configuration")
    public void testParseCloseDoorConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("closeButtons", BigDecimal.ONE);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        commonCheck(configurations, "DOOR", "CLOSE", null, "PUSH");
    }

    @Test
    @DisplayName("convert a floor configuration")
    public void testParseFloorConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("floorButtons", BigDecimal.ONE);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        commonCheck(configurations, "FLOOR", null, "-1,0,G,2", "PUSH");
    }

    @Test
    @DisplayName("convert a main button configuration")
    public void testParseMainConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();
        configurationRequest.put("mainFloorButton", true);

        List<ComponentConfiguration> configurations = builder.getConfigurations(configurationRequest);
        commonCheck(configurations, "MAIN", null, "G", "PUSH");
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
        commonCheck(configurations, "DOOR", "CLOSE", null, "TOUCH");
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
