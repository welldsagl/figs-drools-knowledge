package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.keyswitches.KeySwitchConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("cop key Switch configuration builder")
public class TestCopKeySwitchConfigurationBuilder {

    private final CopKeySwitchConfigurationBuilder builder = new CopKeySwitchConfigurationBuilder();

    @Test
    @DisplayName("convert a correct configuration")
    public void testParseConfiguration() {
        List<ComponentConfiguration> configurations = builder.getConfigurations(
            new Maps.Builder<String,Object>()
                .put("keySwitch", "kaba")
                .put("keyType", "2 position")
                .put("function", "a function")
                .put("engraving", "an engraving")
                .put("fixtureFamily", "DT")
                .build()
        );
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        assertEquals(1, configurations.get(0).getCount());
        assertTrue(configurations.get(0).getConfiguration() instanceof KeySwitchConfiguration);
        KeySwitchConfiguration ksConfiguration = (KeySwitchConfiguration) configurations.get(0).getConfiguration();
        assertEquals("KABA", ksConfiguration.getKeySwitch());
        assertEquals("2 POSITION", ksConfiguration.getKeyType());
        assertEquals("a function", ksConfiguration.getKeyFunction());
        assertEquals("an engraving", ksConfiguration.getEngraving());
        assertEquals("DT", ksConfiguration.getFixtureFamily());
    }

    @Test
    @DisplayName("put an empty string in configuration if engraving is not passed")
    public void testMissingEngraving() {
        List<ComponentConfiguration> configurations = builder.getConfigurations(
            new Maps.Builder<String,Object>()
                .put("keySwitch", "kaba")
                .put("keyType", "2 position")
                .put("function", "a function")
                .put("fixtureFamily", "DT")
                .build()
        );
        KeySwitchConfiguration ksConfiguration = (KeySwitchConfiguration) configurations.get(0).getConfiguration();
        assertNotNull(configurations);
        assertEquals("", ksConfiguration.getEngraving());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                Collections.singletonMap("keySwitch", 1) // not a string
            )
        );
    }

}
