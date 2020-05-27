package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.fixtures.FixtureConfiguration;

import java.util.Map;
import java.util.Optional;

public abstract class FixtureConfigurationBuilder extends AbstractConfigurationBuilder {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Fixtures".equalsIgnoreCase((String) config.get("sections"));
    }

    protected final FixtureConfiguration createBaseConfiguration(Map<String, Object> config, String type) {
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
        configuration.setLdtO(NullableBoolean.from((Boolean) config.getOrDefault("ldtO", false)));
        configuration.setPushType(getPushType(config, type));
        configuration.setFixtureType(getFixtureType(config, type));
        configuration.setLabel(getLabel(config, type));

        return configuration;
    }

    protected abstract String getFixtureType(Map<String, Object> config, String type);

    protected abstract String getPushType(Map<String, Object> config, String type);

    protected abstract String getLabel(Map<String, Object> config, String type);
}
