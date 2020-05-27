package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.indicators.IndicatorConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("lop indicator configuration builder")
public class TestLopIndicatorConfigurationBuilder {

    private LopIndicatorConfigurationBuilder builder = new LopIndicatorConfigurationBuilder();

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
    @DisplayName("convert a correct configuration")
    public void testParseConfiguration() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String,Object>()
                .put("color", "amber")
                .put("indicatorFamily", "TFT")
                .put("lopType", "Single Indicator")
                .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(1, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof IndicatorConfiguration);
        IndicatorConfiguration indicatorConfiguration =
            (IndicatorConfiguration) configuration.get(0).getConfiguration();

        assertEquals("AMBER", indicatorConfiguration.getDisplayColor());
        assertEquals("TFT", indicatorConfiguration.getIndicatorType());
        assertEquals("SINGLE", indicatorConfiguration.getLopType());
    }

    @Test
    @DisplayName("convert a correct configuration for a lop with two indicators (quantity = 2)")
    public void testParseConfigurationForDoubleIndicatorLop() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String,Object>()
                .put("color", "amber")
                .put("indicatorFamily", "TFT")
                .put("lopType", "Two Indicators")
                .build()
        );
        assertEquals(2, configuration.get(0).getCount());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                Collections.singletonMap("indicatorFamily", 1) // not a string
            )
        );
    }

}
