package ch.welld.schindler.fixture.droolsknowledge.builders.lopl;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;
import ch.welld.schindler.fixture.droolsknowledge.components.loplpanel.LopLPanelConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopLConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LopLPanelConfigurationBuilder extends AbstractConfigurationBuilder implements LopLConfiguration {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Panel".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (FloorsQuantityHelper.getTotalFloorsCount(config) == 0) {
            return Collections.emptyList();
        }
        LopLPanelConfiguration lpc = new LopLPanelConfiguration();
        lpc.setLopLType(LopLBuilderHelper.getLopLType(config));
        lpc.setPanelPackage(getUpperCaseString(config, "panelPackage"));
        return Collections.singletonList(
            new ComponentConfiguration(
                lpc,
                FloorsQuantityHelper.getTotalFloorsCount(config)
            )
        );
    }

}
