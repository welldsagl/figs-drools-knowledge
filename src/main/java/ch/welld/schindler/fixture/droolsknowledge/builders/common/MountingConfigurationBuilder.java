package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.mounting.MountingConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class MountingConfigurationBuilder extends AbstractConfigurationBuilder {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Mounting".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        MountingConfiguration mc = new MountingConfiguration();
        String mountingType = (String) config.get("mounting");
        mc.setMountingType(mountingType.equalsIgnoreCase("SURFACE") ? "SURFACE" : "WALLBOX");
        mc.setIpx3(NullableBoolean.from(
            !mountingType.equalsIgnoreCase("SURFACE") &&
            !mountingType.contains("without")
        ));
        mc.setLopType(getLopType(config));
        mc.setLipType(getLipType(config));
        mc.setFixtureFamily((String) config.get("fixtureFamily"));
        mc.setButtonCount(getButtonCount(config));
        return Collections.singletonList(
            new ComponentConfiguration(
                mc,
                1
            )
        );
    }

    protected abstract Integer getButtonCount(Map<String, Object> config);

    protected abstract String getLopType(Map<String, Object> config);

    protected abstract String getLipType(Map<String, Object> config);
}
