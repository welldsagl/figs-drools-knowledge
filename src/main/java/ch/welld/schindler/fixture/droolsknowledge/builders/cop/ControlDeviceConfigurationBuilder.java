package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.controldevices.ControlDeviceConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.controldevices.PositionSetup;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class ControlDeviceConfigurationBuilder extends AbstractConfigurationBuilder implements CopConfiguration {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Control Device".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        ControlDeviceConfiguration cdc = new ControlDeviceConfiguration();

        cdc.setType(((String) config.get("controlDevices")).toUpperCase());
        cdc.setVersion(Optional
            .ofNullable(config.get("version"))
            .map(text -> ((String) text).toUpperCase())
            .orElse(null));

        cdc.setPositions(getPositions(config));

        return Collections.singletonList(
                new ComponentConfiguration(
                    cdc,
                    1
                )
        );
    }

    private Map<String, List<PositionSetup>> getPositions(Map<String, Object> config) {
        Object onCommissionBoxObj = config.get("onCommissionBox");
        if (onCommissionBoxObj == null) {
            return null;
        }
        Map<String, Map<String, String>> onCommissionBox = (Map<String, Map<String, String>>) onCommissionBoxObj;

        Map<String, List<PositionSetup>> positionsSetupsByType = new HashMap<>();

        onCommissionBox.forEach((key, value) -> {
            String type = value.get("selection").toUpperCase();
            if (!positionsSetupsByType.containsKey(type)) {
                positionsSetupsByType.put(type, new ArrayList<>());
            }
            List<PositionSetup> typePositionSetups = positionsSetupsByType.get(type);
            typePositionSetups.add(new PositionSetup(key, value.get("text")));
        });

        return positionsSetupsByType;
    }
}
