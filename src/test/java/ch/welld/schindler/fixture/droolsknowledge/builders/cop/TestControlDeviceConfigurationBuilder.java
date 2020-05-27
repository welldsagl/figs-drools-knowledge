package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.builders.cop.ControlDeviceConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.controldevices.ControlDeviceConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.controldevices.PositionSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test control device configuration builder")
public class TestControlDeviceConfigurationBuilder {

    private ControlDeviceConfigurationBuilder builder = new ControlDeviceConfigurationBuilder();

    @Test
    @DisplayName("can convert a control device configuration")
    public void testCanParseControlDeviceConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Control Device"))
        );
    }

    @Test
    @DisplayName("cannot convert a not control device configuration")
    public void testCannotParseNonControlDeviceConfiguration() {
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
                        .put("controlDevices", "without attendant")
                        .put("version", "v1")
                        .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(1, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof ControlDeviceConfiguration);
        ControlDeviceConfiguration controlDeviceConfiguration = (ControlDeviceConfiguration) configuration.get(0).getConfiguration();
        assertEquals("WITHOUT ATTENDANT", controlDeviceConfiguration.getType());
        assertEquals("V1", controlDeviceConfiguration.getVersion());
    }

    @Test
    @DisplayName("convert on commission position setups")
    public void testConvertPositionSetups() {
        Map<String, Map<String, String>> positionsSetup = new HashMap<>();
        Map<String, String> setup1 = new HashMap<>();
        setup1.put("selection", "KEY");
        setup1.put("text", "position 1");
        Map<String, String> setup2 = new HashMap<>();
        setup2.put("selection", "CPB");
        setup2.put("text", "position 2");
        Map<String, String> setup3 = new HashMap<>();
        setup3.put("selection", "CPB");
        setup3.put("text", "position 3");
        positionsSetup.put("1", setup1);
        positionsSetup.put("2", setup2);
        positionsSetup.put("3", setup3);
        List<ComponentConfiguration> configuration = builder.getConfigurations(
                new Maps.Builder<String,Object>()
                        .put("controlDevices", "without attendant")
                        .put("version", "v1")
                        .put("onCommissionBox", positionsSetup)
                        .build()
        );
        Map<String, List<PositionSetup>> positions = ((ControlDeviceConfiguration) configuration.get(0).getConfiguration()).getPositions();
        assertTrue(positions.containsKey("CPB"));
        assertTrue(positions.containsKey("KEY"));
        assertEquals(2, positions.get("CPB").size());
        assertEquals(1, positions.get("KEY").size());
        assertEquals("2", positions.get("CPB").get(0).getPosition());
        assertEquals("position 2", positions.get("CPB").get(0).getEngraving());
        assertEquals("3", positions.get("CPB").get(1).getPosition());
        assertEquals("position 3", positions.get("CPB").get(1).getEngraving());
        assertEquals("1", positions.get("KEY").get(0).getPosition());
        assertEquals("position 1", positions.get("KEY").get(0).getEngraving());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                Collections.singletonMap("version", 1) // should be a string
            )
        );
    }

}
