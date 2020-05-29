package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.mounting.MountingConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.mounting.SealingFoamConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("lop mounting configuration builder")
public class TestLopMountingConfigurationBuilder {

    private LopMountingConfigurationBuilder builder = new LopMountingConfigurationBuilder();

    @Test
    @DisplayName("get the lop type")
    public void testGetLopType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lopType", "without indicators")
            .build();
        assertEquals("WITHOUT", builder.getLopType(config));
    }

    @Test
    @DisplayName("not get the lip type")
    public void testDontGetLipType() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("lipType", "anything")
            .build();
        assertNull(builder.getLipType(config));
    }

    @Test
    @DisplayName("convert into an empty list if floors quantity is zero")
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
    @DisplayName("get a mounting configuration and a sealing foam configuration")
    public void testGetConfigurations() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("topFloors", new BigDecimal(2))
            .put("middleFloors", new BigDecimal(3))
            .put("bottomFloors", new BigDecimal(4))
            .put("mounting", "wallbox with ipx3")
            .put("ipx3", true)
            .put("fixtureFamily", "DT")
            .put("lopType", "Without indicators")
            .build();
        List<ComponentConfiguration> builtConfigs = builder.getConfigurationsImpl(config);
        assertEquals(2, builtConfigs.size());
        ComponentConfiguration mountingConfig = builtConfigs.get(0);
        ComponentConfiguration sealFoamConfig = builtConfigs.get(1);
        assertTrue(mountingConfig.getConfiguration() instanceof MountingConfiguration);
        assertTrue(sealFoamConfig.getConfiguration() instanceof SealingFoamConfiguration);
        assertEquals(9, mountingConfig.getCount()); // 2 + 3 + 4
        assertEquals(12, sealFoamConfig.getCount()); // 2 + (3 * 2) + 4
        SealingFoamConfiguration sfc = (SealingFoamConfiguration) sealFoamConfig.getConfiguration();
        assertEquals("WALLBOX", sfc.getMountingType());
        assertEquals(NullableBoolean.YES, sfc.getIpx3());
        assertEquals("DT", sfc.getFixtureFamily());
        assertEquals("WITHOUT", sfc.getLopType());
    }

}
