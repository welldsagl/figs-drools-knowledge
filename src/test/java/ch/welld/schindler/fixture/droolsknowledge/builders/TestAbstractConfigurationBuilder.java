package ch.welld.schindler.fixture.droolsknowledge.builders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("test abstract configuration builder")
public class TestAbstractConfigurationBuilder {

    private AbstractConfigurationBuilder builder = new AbstractConfigurationBuilder() {
        @Override
        protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
            return null;
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

}
