package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.InvalidConfigurationFormatException;
import ch.welld.schindler.fixture.droolsknowledge.components.lamps.LampConfiguration;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test lamp configuration builder")
public class TestLampConfigurationBuilder {

    private final CopLampConfigurationBuilder builder = new CopLampConfigurationBuilder();

    @Test
    @DisplayName("convert a correct configuration")
    public void testParseConfiguration() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
                new Maps.Builder<String,Object>()
                    .put("fixtureFamily", "DT")
                    .put("regulations", Lists.newArrayList("EN 81-20"))
                    .put("LEB", BigDecimal.ONE)
                    .put("LNO", BigDecimal.ONE)
                    .put("LBF", BigDecimal.ZERO)
                    .build()
        );
        assertNotNull(configuration);
        assertEquals(2, configuration.size());
        assertEquals(1, configuration.get(0).getCount());
        assertTrue(configuration.get(0).getConfiguration() instanceof LampConfiguration);
        LampConfiguration lampConfigurationLEB = (LampConfiguration) configuration.get(0).getConfiguration();
        LampConfiguration lampConfigurationLNO = (LampConfiguration) configuration.get(1).getConfiguration();
        assertEquals("LEB", lampConfigurationLEB.getLamp());
        assertEquals("LNO", lampConfigurationLNO.getLamp());
    }

    @Test
    @DisplayName("throws the correct exception with an incorrect configuration")
    public void testThrowsInvalidConfigurationFormatException() {
        assertThrows(
            InvalidConfigurationFormatException.class,
            () -> builder.getConfigurations(
                Collections.singletonMap("LEB", "not a big decimal")
            )
        );
    }

}
