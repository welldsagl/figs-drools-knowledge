package ch.welld.schindler.fixture.droolsknowledge.builders.lopl;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.mounting.MountingConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("lop k mounting configuration builder")
public class TestLopLMountingConfigurationBuilder {

    private final LopLMountingConfigurationBuilder builder = new LopLMountingConfigurationBuilder();

    @Test
    @DisplayName("not get the lop type")
    public void testGetLopType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lopType", "anything")
            .build();
        assertNull(builder.getLopType(config));
    }

    @Test
    @DisplayName("not get the lip type")
    public void testDontGetLipType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lipType", "Horizontal")
            .build();
        assertNull(builder.getLipType(config));
    }

    @Test
    @DisplayName("get the lop-k type")
    public void testGetLopKType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lopKType", "100x110")
            .build();
        assertNull(builder.getLopKType(config));
    }

    @Test
    @DisplayName("not get the lop-l type")
    public void testGetLopLType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lopLType", "100x110")
            .build();
        assertEquals("100x110", builder.getLopLType(config));
    }

    @Test
    @DisplayName("convert into an empty list if lop-l quantity is zero")
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
    @DisplayName("get a mounting configuration")
    public void testGetConfiguration() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("topFloors", new BigDecimal(2))
            .put("mounting", "SURFACE")
            .put("lopLType", "100x110")
            .build();
        List<ComponentConfiguration> builtConfigs = builder.getConfigurationsImpl(config);
        assertEquals(1, builtConfigs.size());
        ComponentConfiguration mountingConfig = builtConfigs.get(0);
        assertTrue(mountingConfig.getConfiguration() instanceof MountingConfiguration);
        assertEquals(2, mountingConfig.getCount());
    }

}
