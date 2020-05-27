package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.buzzers.BuzzerConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class BuzzerConfigurationBuilder extends AbstractConfigurationBuilder implements CopConfiguration {
    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Buzzer".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        BuzzerConfiguration bc = new BuzzerConfiguration();
        bc.setBuzzerType(Optional
                .ofNullable(config.get("buzzer"))
                .map(text -> ((String) text).toUpperCase())
                .orElse(null));
        bc.setFixtureFamily((String) config.get("fixtureFamily"));
        return Collections.singletonList(
                new ComponentConfiguration(
                    bc,
                    1
                )
        );
    }
}
