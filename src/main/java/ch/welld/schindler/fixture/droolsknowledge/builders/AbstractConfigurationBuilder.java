package ch.welld.schindler.fixture.droolsknowledge.builders;

import ch.welld.schindler.fixture.droolsknowledge.materials.CableLengthType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Abstract implementation of the component configuration builder interface.
 * Every candidate builder should extend this class in order to make every conversion
 * exception caught and wrapped into an application-well-known InvalidConfigurationFormatException
 */
public abstract class AbstractConfigurationBuilder implements ComponentConfigurationBuilder {

    protected String getUpperCaseString(Map<String, Object> config, String key) {
        return Optional
            .ofNullable(config.get(key))
            .map(text -> ((String) text).toUpperCase())
            .orElse(null);
    }

    /**
     * As 'getConfigurations' this should convert a generic key-value map into a list of
     * drools-known configurations. Every RuntimeException will be caught by 'getConfigurations'
     * method.
     *
     * @param config The key-value map that must be converted
     * @return A list of drools-known configurations
     */
    protected abstract List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config);

    /**
     * Utility method that fill all cable-related attributes of the created `BaseConfiguration`.
     * @param config The key-value map that must be converted
     * @param outputConfig The drools-known configuration that can retrieve both materials and cables
     */
    protected void setCableAttributes(Map<String, Object> config, BaseConfiguration outputConfig) {
        outputConfig.setCableType((String) config.get("cableType"));
        String cableLengthKey = getCableLengthKey();
        outputConfig.setCableLength(((BigDecimal) config.getOrDefault(cableLengthKey, BigDecimal.ZERO)).intValue());
        outputConfig.setCableLengthType(CableLengthType.valueOf((String) config.getOrDefault("cableLengthType", "STANDARD")));
    }

    @Override
    public List<ComponentConfiguration> getConfigurations(Map<String, Object> config) {
        try {
            List<ComponentConfiguration> configurationsImpl = getConfigurationsImpl(config);
            configurationsImpl.forEach(
                c -> {
                    c.getConfiguration().setComponentType(getComponentType());
                    setCableAttributes(config, c.getConfiguration());
                }
            );
            return configurationsImpl;
        } catch (RuntimeException e) {
            throw new InvalidConfigurationFormatException(e);
        }
    }
}
