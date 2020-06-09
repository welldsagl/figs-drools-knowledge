package ch.welld.schindler.fixture.droolsknowledge.builders.lip;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.mounting.MountingConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("lip mounting configuration builder")
public class TestLipMountingConfigurationBuilder {

    private final LipMountingConfigurationBuilder builder = new LipMountingConfigurationBuilder();

    @Test
    @DisplayName("not get the lop type")
    public void testGetLopType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lopType", "anything")
            .build();
        assertNull(builder.getLopType(config));
    }

    @Test
    @DisplayName("get the lip type")
    public void testDontGetLipType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lipType", "Horizontal")
            .build();
        assertEquals("HORIZONTAL", builder.getLipType(config));
    }

    @Test
    @DisplayName("not get the lop-k type")
    public void testGetLopKType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lopKType", "anything")
            .build();
        assertNull(builder.getLopKType(config));
    }

    @Test
    @DisplayName("not get the lop-l type")
    public void testGetLopLType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lopLType", "anything")
            .build();
        assertNull(builder.getLopLType(config));
    }

    @Test
    @DisplayName("convert into an empty list if lip quantity is zero")
    public void testEmptyConfigurationList() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String, Object>()
                .put("quantity", BigDecimal.ZERO)
                .build()
        );
        assertNotNull(configuration);
        assertTrue(configuration.isEmpty());
    }

    @Test
    @DisplayName("get a mounting configuration")
    public void testGetConfiguration() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("quantity", new BigDecimal(2))
            .put("mounting", "SURFACE")
            .put("lipType", "Horizontal")
            .build();
        List<ComponentConfiguration> builtConfigs = builder.getConfigurationsImpl(config);
        assertEquals(1, builtConfigs.size());
        ComponentConfiguration mountingConfig = builtConfigs.get(0);
        assertTrue(mountingConfig.getConfiguration() instanceof MountingConfiguration);
        assertEquals(2, mountingConfig.getCount());
    }

}
