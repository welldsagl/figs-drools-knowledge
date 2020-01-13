package ch.welld.schindler.fixture.droolsknowledge.builders;

import ch.welld.schindler.fixture.droolsknowledge.components.lamps.LampConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test lamp configuration builder")
public class TestLampConfigurationBuilder {

    private LampConfigurationBuilder builder = new LampConfigurationBuilder();

    @Test
    @DisplayName("can convert a lamp configuration")
    public void testCanParseLampConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("elevator", "Lamps"))
        );
    }

    @Test
    @DisplayName("cannot convert a not lamp configuration")
    public void testCannotParseNonLampConfiguration() {
        assertFalse(
            builder.canParseConfiguration(Collections.singletonMap("elevator", "Fixtures"))
        );
    }

    @Test
    @DisplayName("cannot convert a configuration with no 'elevator' field")
    public void testCannotParseConfigurationWithNoElevatorKey() {
        assertFalse(
            builder.canParseConfiguration(Collections.emptyMap())
        );
    }

    @Test
    @DisplayName("convert a correct configuration")
    public void testParseConfiguration() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
                new Maps.Builder<String,Object>()
                    .put("fixtureFamily", "DT")
                    .put("EN 81-20", true)
                    .put("LEB", BigDecimal.ONE)
                    .put("LNO", BigDecimal.ONE)
                    .put("LBF", BigDecimal.ZERO)
                    .build()
        );
        assertNotNull(configuration);
        assertEquals(2, configuration.size());
        assertEquals(1, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof LampConfiguration);
        LampConfiguration lampConfiguration = (LampConfiguration) configuration.get(0).getConfiguration();
        assertEquals("LEB", lampConfiguration.getLamp());
        assertEquals("DT", lampConfiguration.getFixtureFamily());
        assertEquals(true, lampConfiguration.getEn8120());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                Collections.singletonMap("LEB", "not a big decimal")
            )
        );
    }

}
