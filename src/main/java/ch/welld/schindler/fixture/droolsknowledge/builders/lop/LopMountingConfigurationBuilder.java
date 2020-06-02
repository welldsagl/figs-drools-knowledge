package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.MountingConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.mounting.MountingConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.mounting.SealingFoamConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.LopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LopMountingConfigurationBuilder extends MountingConfigurationBuilder implements LopConfiguration {

    @Override
    protected String getLopType(Map<String, Object> config) {
        return LopBuilderHelper.getLopType(config);
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
        return null;
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        if (LopBuilderHelper.getTotalFloorsCount(config) == 0) {
            return Collections.emptyList();
        }
        List<ComponentConfiguration> componentConfigurations = new ArrayList<>();
        // mounting configuration
        MountingConfiguration mc = createBaseConfiguration(config);
        componentConfigurations.add(
            new ComponentConfiguration(mc,LopBuilderHelper.getTotalFloorsCount(config))
        );
        //seal foam configuration
        SealingFoamConfiguration sfc = new SealingFoamConfiguration();
        sfc.setMountingType(mc.getMountingType());
        sfc.setIpx3(mc.getIpx3());
        sfc.setLopType(mc.getLopType());
        sfc.setFixtureFamily(LopBuilderHelper.getFixtureFamily(config));
        componentConfigurations.add(
            new ComponentConfiguration(
                sfc,
                LopBuilderHelper.getButtonCount(config)
            )
        );
        return componentConfigurations;
    }
}
