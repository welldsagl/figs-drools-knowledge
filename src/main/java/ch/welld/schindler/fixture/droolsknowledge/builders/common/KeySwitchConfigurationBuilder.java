package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.keyswitches.KeySwitchConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class KeySwitchConfigurationBuilder extends AbstractConfigurationBuilder {
    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "key switches".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        KeySwitchConfiguration ksConfiguration = new KeySwitchConfiguration();
        ksConfiguration.setKeySwitch(getUpperCaseString(config, "keySwitch"));
        ksConfiguration.setKeyType(getUpperCaseString(config, "keyType"));
        ksConfiguration.setKeyFunction((String) config.get("function"));
        ksConfiguration.setEngraving((String) config.getOrDefault("engraving", ""));
        ksConfiguration.setFixtureFamily((String) config.get("fixtureFamily"));
        ksConfiguration.setPosition((String) config.get("position"));
        return Collections.singletonList(new ComponentConfiguration(ksConfiguration, getQuantity(config)));
    }

    protected abstract int getQuantity(Map<String, Object> config);

}
