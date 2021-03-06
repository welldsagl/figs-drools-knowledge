package ch.welld.schindler.fixture.droolsknowledge.builders.lopk;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;
import ch.welld.schindler.fixture.droolsknowledge.components.lopkpanel.LopKPanelConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopKConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LopKPanelConfigurationBuilder extends AbstractConfigurationBuilder implements LopKConfiguration {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Panel".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (FloorsQuantityHelper.getTotalFloorsCount(config) == 0) {
            return Collections.emptyList();
        }
        LopKPanelConfiguration lpc = new LopKPanelConfiguration();
        lpc.setLopKType(LopKBuilderHelper.getLopKType(config));
        lpc.setPanelPackage(getUpperCaseString(config, "panel"));
        Map<String, Object> lopKSlots = (Map<String, Object>) config.get("keySwitch");
        lpc.setPositions(String.join(", ", lopKSlots.keySet()));
        return Collections.singletonList(
            new ComponentConfiguration(
                lpc,
                FloorsQuantityHelper.getTotalFloorsCount(config)
            )
        );
    }

}
