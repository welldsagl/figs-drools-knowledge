package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.KeySwitchConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.keyswitches.KeySwitchConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CopKeySwitchConfigurationBuilder extends KeySwitchConfigurationBuilder implements CopConfiguration {
    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        KeySwitchConfiguration ksConfiguration = new KeySwitchConfiguration();
        ksConfiguration.setKeyType(getUpperCaseString(config, "keyType"));
        ksConfiguration.setFixtureFamily((String) config.get("fixtureFamily"));
        ksConfiguration.setPosition((String) config.get("position"));
        ksConfiguration.setKeySwitch(getUpperCaseString(config, "keySwitch"));
        ksConfiguration.setKeyFunction((String) config.get("function"));
        ksConfiguration.setEngraving((String) config.getOrDefault("engraving", ""));
        return Collections.singletonList(
            new ComponentConfiguration(
                ksConfiguration,
                1
            )
        );
    }
}
