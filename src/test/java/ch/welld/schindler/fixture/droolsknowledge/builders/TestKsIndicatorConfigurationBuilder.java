package ch.welld.schindler.fixture.droolsknowledge.builders;

import static org.junit.jupiter.api.Assertions.*;

import ch.welld.schindler.fixture.droolsknowledge.components.ksindicators.KSIndicatorConfiguration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;

@DisplayName("test ks indicator configuration builder")
public class TestKsIndicatorConfigurationBuilder {

    private KsIndicatorConfigurationBuilder builder = new KsIndicatorConfigurationBuilder();

    @Test
    @DisplayName("can convert a ks indicator configuration")
    public void testCanParseKsIndicatorConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("elevator", "KS Indicator"))
        );
    }

    @Test
    @DisplayName("cannot convert a not ks indicator configuration")
    public void testCannotParseNonKsIndicatorConfiguration() {
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
                    .put("type", "TFT")
                    .put("color", "Amber")
                    .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(1, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof KSIndicatorConfiguration);
        KSIndicatorConfiguration ksIndicatorConfiguration =
                (KSIndicatorConfiguration) configuration.get(0).getConfiguration();
        assertEquals("TFT", ksIndicatorConfiguration.getType());
        assertEquals("AMBER", ksIndicatorConfiguration.getColor());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                    Collections.singletonMap("type", 1)
            )
        );
    }

}
