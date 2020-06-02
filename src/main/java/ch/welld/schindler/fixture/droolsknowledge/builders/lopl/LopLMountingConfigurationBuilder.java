package ch.welld.schindler.fixture.droolsknowledge.builders.lopl;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.MountingConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.types.LopLConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LopLMountingConfigurationBuilder extends MountingConfigurationBuilder implements LopLConfiguration {

    @Override
    protected String getLopType(Map<String, Object> config) {
        return null;
    }

    @Override
    protected String getLipType(Map<String, Object> config) {
        return null;
    }

    @Override
    protected String getLopKType(Map<String, Object> config) {
        return null;
    }

    @Override
    protected String getLopLType(Map<String, Object> config) {
        return LopLBuilderHelper.getLopLType(config);
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (LopLBuilderHelper.getLopLQuantity(config) == 0) {
            return Collections.emptyList();
        }
        return Collections.singletonList(
            new ComponentConfiguration(
                createBaseConfiguration(config),
                LopLBuilderHelper.getLopLQuantity(config)
            )
        );
    }
}
