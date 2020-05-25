package ch.welld.schindler.fixture.droolsknowledge.builders;

import ch.welld.schindler.fixture.droolsknowledge.types.ComponentType;

import java.util.List;
import java.util.Map;

/**
 * A component configuration builder is a class that can convert
 * a generic key-value map into a list of drools-known configurations.
 */
public interface ComponentConfigurationBuilder extends ComponentType {

    /**
     * Check if this builder can convert the given configuration.
     *
     * @param config The key-value map that must be converted
     * @return true if the builder can convert the `config` otherwise false
     */
    boolean canParseConfiguration(Map<String, Object> config);

    /**
     * Convert the key-value map into a list of drools-known configurations.
     *
     * @param config The key-value map that must be converted
     * @return A list of drools-known configurations
     */
    List<ComponentConfiguration> getConfigurations(Map<String, Object> config);

}
