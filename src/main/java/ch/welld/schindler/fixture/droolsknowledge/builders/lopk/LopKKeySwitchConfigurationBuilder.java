package ch.welld.schindler.fixture.droolsknowledge.builders.lopk;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.KeySwitchConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.keyswitches.KeySwitchConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopKConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
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
        String position,
        Boolean critical,
        BigDecimal criticalQuantity
    ) {
        KeySwitchConfiguration ksConfiguration = new KeySwitchConfiguration();
        ksConfiguration.setKeyType(getUpperCaseString(config, "keyType"));
        ksConfiguration.setFixtureFamily((String) config.get("fixtureFamily"));
        ksConfiguration.setKeySwitch(keySwitch.toUpperCase());
        ksConfiguration.setKeyFunction(keyFunction);
        ksConfiguration.setEngraving(engraving);
        ksConfiguration.setPosition(position);
        int criticalQuantityValue = critical && criticalQuantity != null ? criticalQuantity.intValue() : 0;
        ksConfiguration.setCriticalQuantity(criticalQuantityValue);
        ksConfiguration.setCritical(criticalQuantityValue > 0);
        return ksConfiguration;
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        int floorsQuantity = FloorsQuantityHelper.getTotalFloorsCount(config);
        if (floorsQuantity == 0) {
            return Collections.emptyList();
        }
        Map<String, Map<String, Object>> lopKSlots = (Map<String, Map<String, Object>>) config.get("keySwitch");
        List<KeySwitchConfiguration> keySwitchConfigurations = new ArrayList<>();

        lopKSlots.forEach((key, value) ->
            keySwitchConfigurations.add(
                createKeySwitchConfiguration(
                    config,
                    (String) value.get("category"),
                    (String) value.get("selection"),
                    (String) value.get("text"),
                    key,
                    (Boolean) value.get("critical"),
                    (BigDecimal) value.get("criticalQuantity")
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
