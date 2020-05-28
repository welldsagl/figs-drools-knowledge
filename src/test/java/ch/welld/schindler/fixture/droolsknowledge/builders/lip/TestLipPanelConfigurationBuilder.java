package ch.welld.schindler.fixture.droolsknowledge.builders.lip;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.lippanel.LipPanelConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestLipPanelConfigurationBuilder {

    private LipPanelConfigurationBuilder builder = new LipPanelConfigurationBuilder();

    @Test
    @DisplayName("can convert a lip panel configuration")
    public void testCanParseIndicatorConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Panel"))
        );
    }

    @Test
    @DisplayName("cannot convert a not lip panel configuration")
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
                .put("panel", "glass")
                .put("glass", "Black")
                .put("panelPackage", "Mirror")
                .put("lipType", "vertical")
                .put("indicatorFamily", "DMI")
                .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(1, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof LipPanelConfiguration);
        LipPanelConfiguration lipPanelConfiguration =
            (LipPanelConfiguration) configuration.get(0).getConfiguration();

        assertEquals(true, lipPanelConfiguration.getWithGlass());
        assertEquals("BLACK", lipPanelConfiguration.getGlass());
        assertEquals("MIRROR", lipPanelConfiguration.getPanelPackage());
        assertEquals("VERTICAL", lipPanelConfiguration.getLipType());
        assertEquals("DMI", lipPanelConfiguration.getIndicatorFamily());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                Collections.singletonMap("panel", 2) // not a string
            )
        );
    }

}
