package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;
import ch.welld.schindler.fixture.droolsknowledge.components.indicators.IndicatorConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class LopIndicatorConfigurationBuilder extends AbstractConfigurationBuilder implements LopConfiguration {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Indicator".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (FloorsQuantityHelper.getTotalFloorsCount(config) == 0) {
            return Collections.emptyList();
        }
        IndicatorConfiguration ic = new IndicatorConfiguration();
        ic.setDisplayColor(Optional
            .ofNullable(config.get("color"))
            .map(text -> ((String) text).toUpperCase())
            .orElse(null));
        ic.setIndicatorType((String) config.get("indicatorFamily"));
        String lopType = LopBuilderHelper.getLopType(config);
        ic.setLopType(lopType);

        int quantity = FloorsQuantityHelper.getTotalFloorsCount(config)
            * ("DOUBLE".equalsIgnoreCase(lopType) ? 2 : 1);

        return Collections.singletonList(
            new ComponentConfiguration(
                ic,
                quantity
            )
        );
    }

}
