package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.builders.cop.CopLampConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.lamps.LampConfiguration;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("test lamp configuration builder")
public class TestLampConfigurationBuilder {

    final private CopLampConfigurationBuilder builder = new CopLampConfigurationBuilder();

    @Test
    @DisplayName("can convert a lamp configuration")
    public void testCanParseLampConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Lamps"))
        );
    }

    @Test
    @DisplayName("cannot convert a not lamp configuration")
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
        LampConfiguration lampConfiguration = builder.createBaseConfiguration(
                new Maps.Builder<String,Object>()
                    .put("regulations", Lists.newArrayList("EN 81-20"))
                    .put("LEB", BigDecimal.ONE)
                    .put("LNO", BigDecimal.ONE)
                    .put("LBF", BigDecimal.ZERO)
                    .put("fixtureFamily", "DT")
                    .put("copIntercomStation", true)
                    .put("doppelDeckerCar", true)
                    .build(),
            "LEB"
        );
        assertNotNull(lampConfiguration);
        assertTrue(lampConfiguration.getEn8120());
        assertEquals("LEB", lampConfiguration.getLamp());
        assertEquals("DT", lampConfiguration.getFixtureFamily());
        assertEquals(NullableBoolean.YES, lampConfiguration.getCopIntercomStation());
        assertEquals(NullableBoolean.YES, lampConfiguration.getDoppelDeckerCar());
    }

    @Test
    @DisplayName("throws an exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            NullPointerException.class,
            () -> builder.createBaseConfiguration(
                Collections.singletonMap("copIntercomStation", "not a boolean"),
                "LEB"
            )
        );
    }

}
