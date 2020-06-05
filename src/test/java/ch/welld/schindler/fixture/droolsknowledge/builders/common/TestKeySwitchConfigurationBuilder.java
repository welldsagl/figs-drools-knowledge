package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("key switch configuration builder")
public class TestKeySwitchConfigurationBuilder {

    private final KeySwitchConfigurationBuilder builder = new KeySwitchConfigurationBuilder() {
        @Override
        public String getCableLengthKey() {
            return null;
        }

        @Override
        public String getComponentType() {
            return null;
        }

        @Override
        protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
            return null;
        }
    };

    @Test
    @DisplayName("can convert a key switch configuration")
    public void testCanParseFixtureConfiguration() {
        Map<String, Object> configuration = new Maps.Builder<String,Object>()
            .put("sections", "Key Switches")
            .build();
        assertTrue(
            builder.canParseConfiguration(configuration)
        );
    }

    @Test
    @DisplayName("cannot convert a not key switch configuration")
    public void testCannotParseNonFixtureConfiguration() {
        assertFalse(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Lamps"))
        );
    }

    @Test
    @DisplayName("cannot convert a configuration with no 'sections' field")
    public void testCannotParseConfigurationWithNoSectionsKey() {
        assertFalse(
            builder.canParseConfiguration(Collections.emptyMap())
        );
    }
}
