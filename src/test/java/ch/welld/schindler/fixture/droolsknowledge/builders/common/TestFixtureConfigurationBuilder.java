package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.fixtures.FixtureConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test fixture configuration builder")
public class TestFixtureConfigurationBuilder {

    private FixtureConfigurationBuilder builder = new FixtureConfigurationBuilder() {
        @Override
        public String getCableLengthKey() {
            return null;
        }

        @Override
        public String getComponentType() {
            return null;
        }

        @Override
        protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
            return null;
        }

        @Override
        protected String getFixtureType(Map<String, Object> config, String type) {
            return "fixture type";
        }

        @Override
        protected String getPushType(Map<String, Object> config, String type) {
            return "push type";
        }

        @Override
        protected String getLabel(Map<String, Object> config, String type) {
            return "label";
        }
    };

    @Test
    @DisplayName("can convert a fixture configuration")
    public void testCanParseFixtureConfiguration() {
        Map<String, Object> configuration = new Maps.Builder<String,Object>()
            .put("elevator", "Fixtures")
            .build();
        assertTrue(
            builder.canParseConfiguration(configuration)
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
                .put("ldtO", true)
                .build();
    }

    @Test
    @DisplayName("convert a main button configuration")
    public void testParseMainConfiguration() {
        Map<String, Object> configurationRequest = createConfigurationRequest();

        FixtureConfiguration c = builder.createBaseConfiguration(configurationRequest, "a type");
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

        assertEquals(c.getFixtureType(), "fixture type");
        assertEquals(c.getPushType(), "push type");
        assertEquals(c.getLabel(), "label");
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
