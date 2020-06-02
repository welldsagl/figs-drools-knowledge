package ch.welld.schindler.fixture.droolsknowledge.builders.lopk;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.MountingConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.types.LopKConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LopKMountingConfigurationBuilder extends MountingConfigurationBuilder implements LopKConfiguration {

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
        return LopKBuilderHelper.getLopKType(config);
    }

    @Override
    protected String getLopLType(Map<String, Object> config) {
        return null;
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (LopKBuilderHelper.getLopKQuantity(config) == 0) {
            return Collections.emptyList();
        }
        return Collections.singletonList(
            new ComponentConfiguration(
                createBaseConfiguration(config),
                LopKBuilderHelper.getLopKQuantity(config)
            )
        );
    }
}
