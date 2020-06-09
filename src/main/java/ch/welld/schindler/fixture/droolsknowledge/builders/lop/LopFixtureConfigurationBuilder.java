package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FixtureConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;
import ch.welld.schindler.fixture.droolsknowledge.types.LopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LopFixtureConfigurationBuilder extends FixtureConfigurationBuilder implements LopConfiguration {

    enum FixtureType {
        UP,
        DOWN
    }

    @Override
    protected String getFixtureType(Map<String, Object> config, String type) {
        return "FLOOR";
    }

    @Override
    protected String getPushType(Map<String, Object> config, String type) {
        return ((String) config.getOrDefault("pushType", "PUSH")).toUpperCase();
    }

    @Override
    protected String getLabel(Map<String, Object> config, String type) {
        FixtureType fixtureType = FixtureType.valueOf(type);
        switch (fixtureType) {
            case UP:
                return "▲";
            case DOWN:
                return "▼";
            default:
                return null;
        }
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        List<ComponentConfiguration> configList = new ArrayList<>();

        int topFloors = FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.TOP);
        int middleFloors = FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.MIDDLE);
        int bottomFloors = FloorsQuantityHelper.getFloorsCount(config, FloorsQuantityHelper.FloorPosition.BOTTOM);

        int upButtons = middleFloors + bottomFloors;
        int downButtons = middleFloors + topFloors;

        if (upButtons > 0) {
            configList.add(new ComponentConfiguration(createBaseConfiguration(config, FixtureType.UP.toString()), upButtons));
        }
        if (downButtons > 0) {
            configList.add(new ComponentConfiguration(createBaseConfiguration(config, FixtureType.DOWN.toString()), downButtons));
        }
        return configList;
    }
}
