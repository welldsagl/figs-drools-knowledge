package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.loppanel.LopPanelConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("lop panel configuration builder")
public class TestLopPanelConfigurationBuilder {

    private LopPanelConfigurationBuilder builder = new LopPanelConfigurationBuilder();

    @Test
    @DisplayName("can convert a lop panel configuration")
    public void testCanParseLopPanelConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Panel"))
        );
    }

    @Test
    @DisplayName("cannot convert a not lop panel configuration")
    public void testCannotParseNonLopPanelConfiguration() {
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
                .put("panel", "GLASS")
                .put("logo", true)
                .put("decoPanel", "Black")
                .put("buttonPanel", "Mirror")
                .put("lopType", "Two indicators")
                .put("indicatorFamily", "DMI")
                .put("topFloors", new BigDecimal(2))
                .put("middleFloors", new BigDecimal(3))
                .put("bottomFloors", new BigDecimal(4))
                .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(9, configuration.get(0).getCount()); // 2 + 3 + 4
        assertTrue(configuration.get(0).getConfiguration() instanceof LopPanelConfiguration);
        LopPanelConfiguration lopPanelConfiguration =
            (LopPanelConfiguration) configuration.get(0).getConfiguration();

        assertTrue(lopPanelConfiguration.getWithGlass());
        assertTrue(lopPanelConfiguration.getWithLogo());
        assertEquals("BLACK", lopPanelConfiguration.getDecoPanel());
        assertEquals("MIRROR", lopPanelConfiguration.getButtonPanel());
        assertEquals("DOUBLE", lopPanelConfiguration.getLopType());
        assertEquals("DMI", lopPanelConfiguration.getIndicatorFamily());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                new Maps.Builder<String, Object>()
                    .put("topFloors", BigDecimal.ONE)
                    .put("indicatorFamily", BigDecimal.ZERO) // not a string
                    .build()
            )
        );
    }

}
