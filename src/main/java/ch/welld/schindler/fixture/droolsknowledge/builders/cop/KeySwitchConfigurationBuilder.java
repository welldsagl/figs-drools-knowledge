package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.keyswitches.KeySwitchConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class KeySwitchConfigurationBuilder extends AbstractConfigurationBuilder implements CopConfiguration {
    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "key switches".equalsIgnoreCase((String) config.get("elevator"));
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        KeySwitchConfiguration ksConfiguration = new KeySwitchConfiguration();
        ksConfiguration.setKeySwitch(getUpperCaseString(config, "keySwitch"));
        ksConfiguration.setKeyType(getUpperCaseString(config, "keyType"));
        ksConfiguration.setKeyFunction((String) config.get("function"));
        ksConfiguration.setEngraving((String) config.getOrDefault("engraving", ""));
        ksConfiguration.setFixtureFamily((String) config.get("fixtureFamily"));
        return Collections.singletonList(new ComponentConfiguration(ksConfiguration, 1));
    }

}
