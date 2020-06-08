package ch.welld.schindler.fixture.droolsknowledge.builders.lopk;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.lopkpanel.LopKPanelConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestLopKPanelConfigurationBuilder {

    private final LopKPanelConfigurationBuilder builder = new LopKPanelConfigurationBuilder();

    private Map<String, Object> createSlot(
        String category,
        String selection,
        String text
    ) {
        return new Maps.Builder<String,Object>()
            .put("category", category)
            .put("selection", selection)
            .put("text", text)
            .put("critical", false)
            .build();
    }

    @Test
    @DisplayName("can convert a lop k panel configuration")
    public void testCanParseLopKPanelConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Panel"))
        );
    }

    @Test
    @DisplayName("cannot convert a not lop k panel configuration")
    public void testCannotParseNonLopKPanelConfiguration() {
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
    @DisplayName("convert into an empty list if lop k quantity is zero")
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
        slots.put("a1", createSlot("cat_a1", "sel_a1", "text_a1"));
        slots.put("b1", createSlot("cat_b1", "sel_b1", "text_b1"));
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String,Object>()
                .put("panel", "Mirror")
                .put("lopKType", "100x110")
                .put("topFloors", new BigDecimal(2))
                .put( "keySwitch", slots)
                .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(2, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof LopKPanelConfiguration);
        LopKPanelConfiguration lopKPanelConfiguration =
            (LopKPanelConfiguration) configuration.get(0).getConfiguration();

        assertEquals("MIRROR", lopKPanelConfiguration.getPanelPackage());
        assertEquals("100x110", lopKPanelConfiguration.getLopKType());
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
