package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.loppanel.LopPanelConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LopPanelConfigurationBuilder extends AbstractConfigurationBuilder implements LopConfiguration {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Panel".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (LopBuilderHelper.getTotalFloorsCount(config) == 0) {
            return Collections.emptyList();
        }
        LopPanelConfiguration lpc = new LopPanelConfiguration();
        lpc.setWithGlass(((String)config.get("panel")).equalsIgnoreCase("glass"));
        lpc.setWithLogo((Boolean) config.get("logo"));
        lpc.setDecoPanel(getUpperCaseString(config, "decoPanel"));
        lpc.setButtonPanel(getUpperCaseString(config, "buttonPanel"));
        lpc.setLopType(LopBuilderHelper.getLopType(config));
        lpc.setIndicatorFamily(LopBuilderHelper.getIndicatorFamily(config));
        return Collections.singletonList(
            new ComponentConfiguration(
                lpc,
                LopBuilderHelper.getTotalFloorsCount(config)
            )
        );
    }
}
