package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;
import ch.welld.schindler.fixture.droolsknowledge.components.loppanel.LopPanelConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LopPanelConfigurationBuilder extends AbstractConfigurationBuilder implements LopConfiguration {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Panel".equalsIgnoreCase((String) config.get("sections"));
    }

    private LopPanelConfiguration createPanelConfiguration(Map<String, Object> config, String floorPosition) {
        LopPanelConfiguration lpc = new LopPanelConfiguration();
        lpc.setWithGlass(((String)config.get("panel")).equalsIgnoreCase("glass"));
        lpc.setWithLogo((Boolean) config.get("logo"));
        lpc.setDecoPanel(getUpperCaseString(config, "decoPanel"));
        lpc.setButtonPanel(getUpperCaseString(config, "buttonPanel"));
        lpc.setLopType(LopBuilderHelper.getLopType(config));
        lpc.setIndicatorFamily(LopBuilderHelper.getIndicatorFamily(config));
        lpc.setFloorPosition(floorPosition);
        return lpc;
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (FloorsQuantityHelper.getTotalFloorsCount(config) == 0) {
            return Collections.emptyList();
        }

        int topFloors = FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.TOP);
        int bottomFloors = FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.BOTTOM);
        int intermediateFloors = FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.MIDDLE);

        List<ComponentConfiguration> configurations = new ArrayList<>();
        if (topFloors > 0) {
            configurations.add(new ComponentConfiguration(createPanelConfiguration(config, "Top"), topFloors));
        }
        if (bottomFloors > 0) {
            configurations.add(new ComponentConfiguration(createPanelConfiguration(config, "Bottom"), bottomFloors));
        }
        if (intermediateFloors > 0) {
            configurations.add(new ComponentConfiguration(createPanelConfiguration(config, "Intermediate"), intermediateFloors));
        }
        return configurations;
    }
}
