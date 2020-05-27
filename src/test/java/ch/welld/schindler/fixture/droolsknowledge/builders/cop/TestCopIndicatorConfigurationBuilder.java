package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.indicators.IndicatorConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test indicator configuration builder")
public class TestCopIndicatorConfigurationBuilder {

    private CopIndicatorConfigurationBuilder builder = new CopIndicatorConfigurationBuilder();

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
                .put("coverSize", "20")
                .put("color", "amber")
                .put("displayLayout", "2")
                .put("displaySize", "7\"")
                .put("glassCover", "red")
                .put("indicators", "EDS")
                .put("mountingType", "Surface")
                .put("temperatureSensor", true)
                .put("language", "English")
                .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(1, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof IndicatorConfiguration);
        IndicatorConfiguration indicatorConfiguration =
                (IndicatorConfiguration) configuration.get(0).getConfiguration();

        assertEquals("20", indicatorConfiguration.getCoverSize());
        assertEquals("AMBER", indicatorConfiguration.getDisplayColor());
        assertEquals("2", indicatorConfiguration.getDisplayLayout());
        assertEquals("7", indicatorConfiguration.getDisplaySize());
        assertEquals("RED", indicatorConfiguration.getGlassCoverColor());
        assertEquals("EDS", indicatorConfiguration.getIndicatorType());
        assertEquals("Surface", indicatorConfiguration.getMountingType());
        assertEquals(true, indicatorConfiguration.getTemperatureSensor());
        assertEquals("English", indicatorConfiguration.getLanguage());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                Collections.singletonMap("temperatureSensor", "not a boolean")
            )
        );
    }

}
