package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FixtureConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.fixtures.FixtureConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class CopFixtureConfigurationBuilder extends FixtureConfigurationBuilder implements CopConfiguration {

    enum FixtureType {
        ALARM,
        CLOSE,
        OPEN,
        FLOOR,
        MAIN
    }

    private boolean hasMainFloor(Map<String, Object> config) {
        return (Boolean) config.getOrDefault("mainFloorButton", false);
    }

    @Override
    protected String getFixtureType(Map<String, Object> config, String type) {
        switch (FixtureType.valueOf(type)) {
            case ALARM:
                return "ALARM";
            case CLOSE:
            case OPEN:
                return "DOOR";
            case MAIN:
                return "MAIN";
            case FLOOR:
            default:
                return "FLOOR";
        }
    }

    @Override
    protected String getPushType(Map<String, Object> config, String type) {
        return type.equals(FixtureType.ALARM.toString())
            ? "PUSH"
            : ((String) config.getOrDefault("pushType", "PUSH")).toUpperCase();
    }

    @Override
    protected String getLabel(Map<String, Object> config, String type) {
        switch (FixtureType.valueOf(type)) {
            case MAIN:
                return (String) config.get("mainFloor");
            case OPEN:
                return "◀|▶";
            case CLOSE:
                return "▶|◀";
            case ALARM:
                return "🔔";
            case FLOOR: {
                String floors = (String) config.get("floorDesignation");
                boolean hasMainFloor = hasMainFloor(config);
                String mainFloor = (String) config.get("mainFloor");

                // We remove the main floor (if its label is defined) from the list of required button labels
                // We assumed that the list is comma separated
                return Arrays
                    .stream(floors.split(","))
                    .filter(floor -> !hasMainFloor || !floor.equals(mainFloor))
                    .collect(Collectors.joining(","));
            }
            default:
                return null;
        }
    }

    private boolean isHiddenButtonConfiguration(Map<String, Object> config) {
        return "hidden button".equalsIgnoreCase((String) config.get("fixtureFamily"));
    }

    private List<ComponentConfiguration> getHiddenButtonConfiguration(Map<String, Object> config) {
        FixtureConfiguration fc = new FixtureConfiguration();
        fc.setLabel(((String) config.get("designation")));
        fc.setFixtureFamily("HIDDEN");
        fc.setFixtureType("HIDDEN");
        int quantity = ((BigDecimal) config.getOrDefault("quantity", BigDecimal.ONE)).intValue();
        return Collections.singletonList(new ComponentConfiguration(fc, quantity));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (isHiddenButtonConfiguration(config)) {
            return getHiddenButtonConfiguration(config);
        }

        List<ComponentConfiguration> configList = new ArrayList<>();
        int alarmButtons = ((BigDecimal) config.getOrDefault("alarmButtons", BigDecimal.ZERO)).intValue();
        int openButtons = ((BigDecimal) config.getOrDefault("openButtons", BigDecimal.ZERO)).intValue();
        int closeButtons = ((BigDecimal) config.getOrDefault("closeButtons", BigDecimal.ZERO)).intValue();
        int floorButtons = ((BigDecimal) config.getOrDefault("floorButtons", BigDecimal.ZERO)).intValue();

        if (hasMainFloor(config)) {
            configList.add(new ComponentConfiguration(createBaseConfiguration(config, FixtureType.MAIN.toString()),1));
        }
        if (alarmButtons > 0) {
            configList.add(new ComponentConfiguration(createBaseConfiguration(config, FixtureType.ALARM.toString()), alarmButtons));
        }
        if (openButtons > 0) {
            configList.add(new ComponentConfiguration(createBaseConfiguration(config, FixtureType.OPEN.toString()), openButtons));
        }
        if (closeButtons > 0) {
            configList.add(new ComponentConfiguration(createBaseConfiguration(config, FixtureType.CLOSE.toString()), closeButtons));
        }
        if (floorButtons > 0) {
            configList.add(new ComponentConfiguration(createBaseConfiguration(config, FixtureType.FLOOR.toString()), floorButtons));
        }
        return configList;
    }
}
