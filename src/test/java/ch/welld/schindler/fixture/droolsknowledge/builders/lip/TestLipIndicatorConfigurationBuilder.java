package ch.welld.schindler.fixture.droolsknowledge.builders.lip;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.indicators.IndicatorConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestLipIndicatorConfigurationBuilder {

    private LipIndicatorConfigurationBuilder builder = new LipIndicatorConfigurationBuilder();

    @Test
    @DisplayName("can convert an indicator configuration")
    public void testCanParseIndicatorConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Indicator"))
        );
    }

    @Test
    @DisplayName("cannot convert a not indicator configuration")
    public void testCannotParseNonIndicatorConfiguration() {
        assertFalse(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Fixtures"))
        );
    }

    @Test
    @DisplayName("cannot convert a configuration with no 'sections' field")
    public void testCannotParseConfigurationWithNoSectionsKey() {
        assertFalse(
            builder.canParseConfiguration(Collections.emptyMap())
        );
    }

    @Test
    @DisplayName("convert into an empty list if lip quantity is zero")
    public void testEmptyConfigurationList() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String, Object>()
                .put("quantity", BigDecimal.ZERO)
                .build()
        );
        assertNotNull(configuration);
        assertTrue(configuration.isEmpty());
    }

    @Test
    @DisplayName("convert a correct configuration")
    public void testParseConfiguration() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String,Object>()
                .put("color", "amber")
                .put("indicatorFamily", "DMI")
                .put("lipType", "Horizontal")
                .put("quantity", new BigDecimal(3))
                .put("gong", true)
                .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(3, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof IndicatorConfiguration);
        IndicatorConfiguration indicatorConfiguration =
            (IndicatorConfiguration) configuration.get(0).getConfiguration();

        assertEquals("AMBER", indicatorConfiguration.getDisplayColor());
        assertEquals("DMI", indicatorConfiguration.getIndicatorType());
        assertEquals("HORIZONTAL", indicatorConfiguration.getLipType());
        assertEquals(true, indicatorConfiguration.getWithGong());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                new Maps.Builder<String,Object>()
                    .put("quantity", new BigDecimal(3))
                    .put("gong", "not a boolean")
                    .build()
            )
        );
    }

}
