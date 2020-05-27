package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.builders.cop.BuzzerConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.buzzers.BuzzerConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test buzzer configuration builder")
public class TestBuzzerConfigurationBuilder {

    private BuzzerConfigurationBuilder builder = new BuzzerConfigurationBuilder();

    @Test
    @DisplayName("can convert a buzzer configuration")
    public void testCanParseBuzzerConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Buzzer"))
        );
    }

    @Test
    @DisplayName("cannot convert a not buzzer configuration")
    public void testCannotParseNonBuzzerConfiguration() {
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
                        .put("buzzer", "HAIRLINE")
                        .put( "fixtureFamily", "DT")
                        .build()
        );
        assertNotNull(configuration);
        assertEquals(1, configuration.size());
        assertEquals(1, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof BuzzerConfiguration);
        BuzzerConfiguration buzzerConfiguration = (BuzzerConfiguration) configuration.get(0).getConfiguration();
        assertEquals("HAIRLINE", buzzerConfiguration.getBuzzerType());
        assertEquals("DT", buzzerConfiguration.getFixtureFamily());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                Collections.singletonMap("buzzer", 1)
            )
        );
    }

}
