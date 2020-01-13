package ch.welld.schindler.fixture.droolsknowledge.builders;

import ch.welld.schindler.fixture.droolsknowledge.components.fixtures.FixtureConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class FixtureConfigurationBuilder extends AbstractConfigurationBuilder {

    enum FixtureType {
        ALARM,
        CLOSE,
        OPEN,
        FLOOR,
        MAIN
    }

    private String mapFixtureTypeToDroolsFixtureType(FixtureType type) {
        switch (type) {
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

    private String mapFixtureTypeToLabel(Map<String, Object> config, FixtureType type) {
        switch (type) {
            case MAIN:
                return (String) config.get("mainFloor");
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

    private boolean hasMainFloor(Map<String, Object> config) {
        return (Boolean) config.getOrDefault("mainFloorButton", false);
    }

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Fixtures".equalsIgnoreCase((String) config.get("elevator"));
    }

    private ComponentConfiguration createBaseConfiguration(Map<String, Object> config, FixtureType type, int count) {
        FixtureConfiguration configuration = new FixtureConfiguration();
        configuration.setBacklight((Boolean) config.get("backlight"));

        configuration.setButtonColor(Optional
                .ofNullable(config.get("color"))
                .map(text -> ((String) text).toUpperCase())
                .orElse(null));

        configuration.setFixtureFamily((String) config.get("fixtureFamily"));
        configuration.setFixtureSubfamily((String) config.get("buttons"));
        configuration.setHairlineInsert((Boolean) config.get("hairlineInsert"));
        configuration.setLetterRaised((Boolean) config.get("letterRaised"));
        configuration.setBraille((Boolean) config.get("braille"));
        configuration.setIllumination((Boolean) config.get("illumination"));
        configuration.setBuzzer((Boolean) config.get("buzzer"));
        configuration.setFiveDot((Boolean) config.get("fiveDot"));
        configuration.setPushType(((String) config.getOrDefault("pushType", "PUSH")).toUpperCase());
        configuration.setFixtureType(mapFixtureTypeToDroolsFixtureType(type));
        configuration.setLabel(mapFixtureTypeToLabel(config, type));
        if (type == FixtureType.OPEN || type == FixtureType.CLOSE) {
            configuration.setDoorButtonType(type.toString());
        }

        return new ComponentConfiguration(configuration, count);
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        List<ComponentConfiguration> configList = new ArrayList<>();
        int alarmButtons = ((BigDecimal) config.getOrDefault("alarmButtons", BigDecimal.ZERO)).intValue();
        int openButtons = ((BigDecimal) config.getOrDefault("openButtons", BigDecimal.ZERO)).intValue();
        int closeButtons = ((BigDecimal) config.getOrDefault("closeButtons", BigDecimal.ZERO)).intValue();
        int floorButtons = ((BigDecimal) config.getOrDefault("floorButtons", BigDecimal.ZERO)).intValue();

        if (hasMainFloor(config)) {
            configList.add(createBaseConfiguration(config, FixtureType.MAIN, 1));
        }
        if (alarmButtons > 0) {
            configList.add(createBaseConfiguration(config, FixtureType.ALARM, alarmButtons));
        }
        if (openButtons > 0) {
            configList.add(createBaseConfiguration(config, FixtureType.OPEN, openButtons));
        }
        if (closeButtons > 0) {
            configList.add(createBaseConfiguration(config, FixtureType.CLOSE, closeButtons));
        }
        if (floorButtons > 0) {
            configList.add(createBaseConfiguration(config, FixtureType.FLOOR, floorButtons));
        }
        return configList;
    }
}
