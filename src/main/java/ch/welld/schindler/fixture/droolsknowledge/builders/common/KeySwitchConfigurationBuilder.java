package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;

import java.util.Map;

public abstract class KeySwitchConfigurationBuilder extends AbstractConfigurationBuilder {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "key switches".equalsIgnoreCase((String) config.get("sections"));
    }
}
