package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

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
    @DisplayName("convert into an empty list if floors quantity is zero")
    public void testEmptyConfigurationList() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String, Object>()
                .put("topFloors", BigDecimal.ZERO)
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
                .put("indicatorFamily", "TFT")
                .put("lopType", "Single Indicator")
                .put("topFloors", new BigDecimal(2))
                .put("middleFloors", new BigDecimal(3))
                .put("bottomFloors", new BigDecimal(4))
                .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(9, configuration.get(0).getCount()); // 2 + 3 + 4
        assertTrue(configuration.get(0).getConfiguration() instanceof IndicatorConfiguration);
        IndicatorConfiguration indicatorConfiguration =
            (IndicatorConfiguration) configuration.get(0).getConfiguration();

        assertEquals("AMBER", indicatorConfiguration.getDisplayColor());
        assertEquals("TFT", indicatorConfiguration.getIndicatorType());
        assertEquals("SINGLE", indicatorConfiguration.getLopType());
    }

    @Test
    @DisplayName("convert a correct configuration for a lop with two indicators (double quantity)")
    public void testParseConfigurationForDoubleIndicatorLop() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String,Object>()
                .put("color", "amber")
                .put("indicatorFamily", "TFT")
                .put("lopType", "Two Indicators")
                .put("topFloors", new BigDecimal(2))
                .put("middleFloors", new BigDecimal(3))
                .put("bottomFloors", new BigDecimal(4))
                .build()
        );
        assertEquals(18, configuration.get(0).getCount()); // 2 * (2 + 3 + 4)
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                new Maps.Builder<String, Object>()
                    .put("topFloors", BigDecimal.ONE)
                    .put("indicatorFamily", 1) // not a string
                    .build()
            )
        );
    }

}
