package ch.welld.schindler.fixture.droolsknowledge.builders.lip;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.lippanel.LipPanelConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LipConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LipPanelConfigurationBuilder extends AbstractConfigurationBuilder implements LipConfiguration {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Panel".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (LipBuilderHelper.getLipQuantity(config) == 0) {
            return Collections.emptyList();
        }
        LipPanelConfiguration lpc = new LipPanelConfiguration();
        if (config.containsKey("panel")) {
            lpc.setWithGlass(((String)config.get("panel")).equalsIgnoreCase("glass"));
        }
        lpc.setGlass(getUpperCaseString(config, "glass"));
        lpc.setPanelPackage(getUpperCaseString(config, "panelPackage"));
        lpc.setLipType(LipBuilderHelper.getLipType(config));
        lpc.setIndicatorFamily(LipBuilderHelper.getIndicatorFamily(config));
        return Collections.singletonList(
            new ComponentConfiguration(
                lpc,
                LipBuilderHelper.getLipQuantity(config)
            )
        );
    }
}
