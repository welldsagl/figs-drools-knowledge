package ch.welld.schindler.fixture.droolsknowledge.builders;

import java.util.List;
import java.util.Map;

/**
 * Abstract implementation of the component configuration builder interface.
 * Every candicate builder should extends this class in order to make every conversion
 * exception caught and wrapped into an application-well-known InvalidConfigurationFormatException
 */
public abstract class AbstractConfigurationBuilder implements ComponentConfigurationBuilder {

    /**
     * As 'getConfigurations' this should convert a generic key-value map into a list of
     * drools-known configurations. Every RuntimeException will be caught by 'getConfigurations'
     * method.
     *
     * @param config The key-value map that must be converted
     * @return A list of drools-known configurations
     */
    protected abstract List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config);

    @Override
    public List<ComponentConfiguration> getConfigurations(Map<String, Object> config) {
        try {
            return getConfigurationsImpl(config);
        } catch (RuntimeException e) {
            throw new InvalidConfigurationFormatException(e);
        }
    }
}
