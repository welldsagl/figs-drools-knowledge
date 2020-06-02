package ch.welld.schindler.fixture.droolsknowledge.builders.lopl;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.LampConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.lamps.LampConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopLConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LopLLampConfigurationBuilder extends LampConfigurationBuilder implements LopLConfiguration {

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        String lamp = (String) config.get("lamp");

        LampConfiguration lampConfiguration = createBaseConfiguration(config, lamp);


        return Collections.singletonList(
            new ComponentConfiguration(
                lampConfiguration,
                LopLBuilderHelper.getLopLQuantity(config)
            )
        );
    }
}
