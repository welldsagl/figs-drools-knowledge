package ch.welld.schindler.fixture.droolsknowledge.builders;

import ch.welld.schindler.fixture.droolsknowledge.components.buzzers.BuzzerConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class BuzzerConfigurationBuilder extends AbstractConfigurationBuilder {
    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Buzzer".equalsIgnoreCase((String) config.get("elevator"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        BuzzerConfiguration bc = new BuzzerConfiguration();
        bc.setBuzzerType(Optional
                .ofNullable(config.get("buzzer"))
                .map(text -> ((String) text).toUpperCase())
                .orElse(null));
        bc.setFixtureFamily((String) config.get("fixtureFamily"));
        return Arrays.asList(
                new ComponentConfiguration(
                        bc,
                        1
                )
        );
    }
}
