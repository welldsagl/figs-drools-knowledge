package ch.welld.schindler.fixture.droolsknowledge.builders.lopl;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.LampConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.lamps.LampConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopLConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class LopLLampConfigurationBuilder extends LampConfigurationBuilder implements LopLConfiguration {

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        Map<String, Map<String, String>> lopLSlots = (Map<String, Map<String, String>>) config.get("lamps");
        List<LampConfiguration> lampConfigurations = new ArrayList<>();

        lopLSlots.forEach((key, value) -> {
            LampConfiguration slotConfiguration = createBaseConfiguration(
                config,
                value.get("selection")
            );
            slotConfiguration.setPosition(key);
            lampConfigurations.add(slotConfiguration);
        });


        int floorsQuantity = FloorsQuantityHelper.getTotalFloorsCount(config);
        return lampConfigurations.stream().map(lConfig ->
            new ComponentConfiguration(
                lConfig,
                floorsQuantity
            )
        ).collect(Collectors.toList());
    }
}
