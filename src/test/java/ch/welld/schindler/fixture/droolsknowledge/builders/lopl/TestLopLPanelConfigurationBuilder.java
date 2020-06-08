package ch.welld.schindler.fixture.droolsknowledge.builders.lopl;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.loplpanel.LopLPanelConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestLopLPanelConfigurationBuilder {

    private final LopLPanelConfigurationBuilder builder = new LopLPanelConfigurationBuilder();

    private Map<String, Object> createSlot(String selection) {
        return new Maps.Builder<String,Object>()
            .put("selection", selection)
            .build();
    }

    @Test
    @DisplayName("can convert a lop l panel configuration")
    public void testCanParseLopLPanelConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Panel"))
        );
    }

    @Test
    @DisplayName("cannot convert a not lop l panel configuration")
    public void testCannotParseNonLopLPanelConfiguration() {
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
    @DisplayName("convert into an empty list if lop l quantity is zero")
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
        Map<String, Object> slots = new HashMap<>();
        slots.put("a1", createSlot("lamp_a1"));
        slots.put("b1", createSlot("lamp_b1"));
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String,Object>()
                .put("panel", "Mirror")
                .put("lopLType", "100x110")
                .put("topFloors", new BigDecimal(2))
                .put("lamp", slots)
                .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(2, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof LopLPanelConfiguration);
        LopLPanelConfiguration lopKPanelConfiguration =
            (LopLPanelConfiguration) configuration.get(0).getConfiguration();

        assertEquals("MIRROR", lopKPanelConfiguration.getPanelPackage());
        assertEquals("100x110", lopKPanelConfiguration.getLopLType());
        assertEquals("a1, b1", lopKPanelConfiguration.getPositions());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                new Maps.Builder<String,Object>()
                    .put("topFloors", "not a number")
                    .build()
            )
        );
    }

}
