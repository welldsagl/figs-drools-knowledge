package ch.welld.schindler.fixture.droolsknowledge.builders.lip;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.MountingConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.types.LipConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LipMountingConfigurationBuilder extends MountingConfigurationBuilder implements LipConfiguration {

    @Override
    protected String getLopType(Map<String, Object> config) {
        return null;
    }

    @Override
    protected String getLipType(Map<String, Object> config) {
        return LipBuilderHelper.getLipType(config);
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (LipBuilderHelper.getLipQuantity(config) == 0) {
            return Collections.emptyList();
        }
        return Collections.singletonList(
            new ComponentConfiguration(
                createBaseConfiguration(config),
                LipBuilderHelper.getLipQuantity(config)
            )
        );
    }
}
