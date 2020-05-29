package ch.welld.schindler.fixture.droolsknowledge.builders.lip;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.indicators.IndicatorConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LipConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class LipIndicatorConfigurationBuilder extends AbstractConfigurationBuilder implements LipConfiguration {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Indicator".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (LipBuilderHelper.getLipQuantity(config) == 0) {
            return Collections.emptyList();
        }
        IndicatorConfiguration ic = new IndicatorConfiguration();
        ic.setDisplayColor(Optional
            .ofNullable(config.get("color"))
            .map(text -> ((String) text).toUpperCase())
            .orElse(null));
        ic.setIndicatorType(LipBuilderHelper.getIndicatorFamily(config));
        ic.setLipType(LipBuilderHelper.getLipType(config));
        ic.setWithGong((Boolean) config.get("gong"));

        return Collections.singletonList(
            new ComponentConfiguration(
                ic,
                LipBuilderHelper.getLipQuantity(config)
            )
        );
    }

}
