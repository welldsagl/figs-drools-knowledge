package ch.welld.schindler.fixture.droolsknowledge.builders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.welld.schindler.fixture.droolsknowledge.materials.CableLengthType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;


@DisplayName("test abstract configuration builder")
public class TestAbstractConfigurationBuilder {

    private AbstractConfigurationBuilder builder = new AbstractConfigurationBuilder() {
        @Override
        public String getCableLengthKey() {
            return "copCableLength";
        }

        @Override
        public String getComponentType() {
            return "COP";
        }

        @Override
        protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
            List<ComponentConfiguration> configurations = new ArrayList<>();
            BaseConfiguration aConfig = new BaseConfiguration() {};
            configurations.add(new ComponentConfiguration(aConfig, 1));
            return configurations;
        }

        @Override
        public boolean canParseConfiguration(Map<String, Object> config) {
            return true;
        }
    };

    @Test
    @DisplayName("convert a string to upper case if present")
    public void testGetUpperCaseString() {
        Map<String, Object> config = new HashMap<>();
        config.put("key", "value");
        String upperCaseValue = builder.getUpperCaseString(config, "key");
        assertEquals("VALUE", upperCaseValue);
    }

    @Test
    @DisplayName("return null if trying to convert to upper case a missing key")
    public void testNullGetUpperCaseString() {
        Map<String, Object> config = new HashMap<>();
        config.put("key", "value");
        String upperCaseValue = builder.getUpperCaseString(config, "key2");
        assertNull(upperCaseValue);
    }

    @Test
    @DisplayName("set cable and component attributes in created configurations")
    public void testCommonAttributes() {
        Map<String, Object> config = new Maps.Builder<String,Object>()
            .put("cableType", "UL")
            .put("copCableLength", new BigDecimal(10))
            .put("cableLengthType", "STANDARD")
            .build();
        List<ComponentConfiguration> result = builder.getConfigurations(config);
        BaseConfiguration c = result.get(0).getConfiguration();
        assertEquals(10, c.getCableLength());
        assertEquals("UL", c.getCableType());
        assertEquals(CableLengthType.STANDARD, c.getCableLengthType());
        assertEquals("COP", c.getComponentType());
    }

}
