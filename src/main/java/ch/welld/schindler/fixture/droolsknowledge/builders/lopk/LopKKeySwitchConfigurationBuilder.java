package ch.welld.schindler.fixture.droolsknowledge.builders.lopk;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.KeySwitchConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.keyswitches.KeySwitchConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopKConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class LopKKeySwitchConfigurationBuilder extends KeySwitchConfigurationBuilder implements LopKConfiguration {

    private KeySwitchConfiguration createKeySwitchConfiguration(
        Map<String, Object> config,
        String keySwitch,
        String keyFunction,
        String engraving,
        String position
    ) {
        KeySwitchConfiguration ksConfiguration = new KeySwitchConfiguration();
        ksConfiguration.setKeyType(getUpperCaseString(config, "keyType"));
        ksConfiguration.setFixtureFamily((String) config.get("fixtureFamily"));
        ksConfiguration.setKeySwitch(keySwitch.toUpperCase());
        ksConfiguration.setKeyFunction(keyFunction);
        ksConfiguration.setEngraving(engraving);
        ksConfiguration.setPosition(position);
        return ksConfiguration;
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        int floorsQuantity = FloorsQuantityHelper.getTotalFloorsCount(config);
        if (floorsQuantity == 0) {
            return Collections.emptyList();
        }
        Map<String, Map<String, String>> lopKSlots = (Map<String, Map<String, String>>) config.get("keySwitch");
        List<KeySwitchConfiguration> keySwitchConfigurations = new ArrayList<>();

        lopKSlots.forEach((key, value) ->
            keySwitchConfigurations.add(
                createKeySwitchConfiguration(
                    config,
                    value.get("category"),
                    value.get("selection"),
                    value.get("text"),
                    key
                )
            )
        );

        return keySwitchConfigurations.stream().map(ksConfig ->
            new ComponentConfiguration(
                ksConfig,
                floorsQuantity
            )
        ).collect(Collectors.toList());
    }
}
