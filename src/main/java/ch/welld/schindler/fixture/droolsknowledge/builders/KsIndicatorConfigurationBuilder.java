package ch.welld.schindler.fixture.droolsknowledge.builders;

import ch.welld.schindler.fixture.droolsknowledge.components.ksindicators.KSIndicatorConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class KsIndicatorConfigurationBuilder extends AbstractConfigurationBuilder {
    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "KS Indicator".equalsIgnoreCase((String) config.get("elevator"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        KSIndicatorConfiguration ksc = new KSIndicatorConfiguration();
        ksc.setType((String) config.get("type"));
        ksc.setColor(Optional
                .ofNullable(config.get("color"))
                .map(text -> ((String) text).toUpperCase())
                .orElse(null));
        return Arrays.asList(
                new ComponentConfiguration(
                        ksc,
                        1
                )
        );
    }
}
