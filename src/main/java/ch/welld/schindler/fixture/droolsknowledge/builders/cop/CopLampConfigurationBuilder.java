package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.LampConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class CopLampConfigurationBuilder extends LampConfigurationBuilder implements CopConfiguration {

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        return LAMPS.stream()
            .filter(lamp -> config.containsKey(lamp) && ((BigDecimal) config.get(lamp)).intValue() == 1)
            .map(lamp -> new ComponentConfiguration(
                createBaseConfiguration(config, lamp),
                1
            ))
            .collect(Collectors.toList());
    }

}
