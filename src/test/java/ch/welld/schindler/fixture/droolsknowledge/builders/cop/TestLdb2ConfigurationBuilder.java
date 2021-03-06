package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.builders.cop.Ldb2ConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.ldb2.Ldb2Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("test LDB2 configuration builder")
public class TestLdb2ConfigurationBuilder {

    private Ldb2ConfigurationBuilder builder = new Ldb2ConfigurationBuilder();

    @Test
    @DisplayName("can convert a LDB2 configuration")
    public void testCanParseLampConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "LDB2"))
        );
    }

    @Test
    @DisplayName("cannot convert a not LDB2 configuration")
    public void testCannotParseNonLampConfiguration() {
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
        List<ComponentConfiguration> configurations = builder.getConfigurations(
                new Maps.Builder<String,Object>()
                    .put("hiddenBox", false)
                    .build()
        );
        assertNotNull(configurations);
        assertEquals(1, configurations.size());
        assertEquals(1, configurations.get(0).getCount());
        assertTrue(configurations.get(0).getConfiguration() instanceof Ldb2Configuration);
        Ldb2Configuration ldb2Configuration = (Ldb2Configuration) configurations.get(0).getConfiguration();
        assertEquals(NullableBoolean.NO, ldb2Configuration.getHiddenBox());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                Collections.singletonMap("hiddenBox", "not a boolean")
            )
        );
    }

}
