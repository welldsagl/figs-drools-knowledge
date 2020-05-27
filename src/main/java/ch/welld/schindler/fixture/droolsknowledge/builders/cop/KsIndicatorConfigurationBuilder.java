package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.ksindicators.KSIndicatorConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class KsIndicatorConfigurationBuilder extends AbstractConfigurationBuilder implements CopConfiguration {
    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "KS Indicator".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        KSIndicatorConfiguration ksc = new KSIndicatorConfiguration();
        ksc.setType((String) config.get("type"));
        ksc.setColor(Optional
                .ofNullable(config.get("color"))
                .map(text -> ((String) text).toUpperCase())
                .orElse(null));
        return Collections.singletonList(
                new ComponentConfiguration(
                        ksc,
                        1
                )
        );
    }
}
