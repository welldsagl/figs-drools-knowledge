package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.mounting.MountingConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("mounting configuration builder")
public class TestMountingConfigurationBuilder {

    private MountingConfigurationBuilder builder = new MountingConfigurationBuilder() {
        @Override
        protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
            return null;
        }

        @Override
        public String getCableLengthKey() {
            return null;
        }

        @Override
        public String getComponentType() {
            return null;
        }

        @Override
        protected String getLopType(Map<String, Object> config) {
            return "WITHOUT";
        }

        @Override
        protected String getLipType(Map<String, Object> config) {
            return "HORIZONTAL";
        }
    };

    @Test
    @DisplayName("can convert a mounting configuration")
    public void testCanParseIndicatorConfiguration() {
        assertTrue(
            builder.canParseConfiguration(Collections.singletonMap("sections", "Mounting"))
        );
    }

    @Test
    @DisplayName("cannot convert a not mounting configuration")
    public void testCannotParseNonIndicatorConfiguration() {
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
    @DisplayName("convert a surface mounting configuration")
    public void testParseSurfaceConfiguration() {
        MountingConfiguration mc = builder.createBaseConfiguration(
            new Maps.Builder<String,Object>()
                .put("mounting", "surface")
                .build()
        );
        assertNotNull(mc);

        assertEquals("SURFACE", mc.getMountingType());
        assertEquals(NullableBoolean.NO, mc.getIpx3());
        assertEquals("WITHOUT", mc.getLopType());
        assertEquals("HORIZONTAL", mc.getLipType());
    }

    @Test
    @DisplayName("convert a wallbox with ipx3 mounting configuration")
    public void testParseWallboxWithIpx3Configuration() {
        MountingConfiguration mc = builder.createBaseConfiguration(
            new Maps.Builder<String,Object>()
                .put("mounting", "wallbox with ipx3")
                .build()
        );
        assertNotNull(mc);

        assertEquals("WALLBOX", mc.getMountingType());
        assertEquals(NullableBoolean.YES, mc.getIpx3());
        assertEquals("WITHOUT", mc.getLopType());
        assertEquals("HORIZONTAL", mc.getLipType());
    }

    @Test
    @DisplayName("convert a wallbox without ipx3 mounting configuration")
    public void testParseWallboxWithoutIpx3Configuration() {
        MountingConfiguration mc = builder.createBaseConfiguration(
            new Maps.Builder<String,Object>()
                .put("mounting", "wallbox without ipx3")
                .build()
        );
        assertNotNull(mc);

        assertEquals("WALLBOX", mc.getMountingType());
        assertEquals(NullableBoolean.NO, mc.getIpx3());
        assertEquals("WITHOUT", mc.getLopType());
        assertEquals("HORIZONTAL", mc.getLipType());
    }

}
