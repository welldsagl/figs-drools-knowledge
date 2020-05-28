package ch.welld.schindler.fixture.droolsknowledge.builders.lip;

import ch.welld.schindler.fixture.droolsknowledge.builders.common.MountingConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.types.LipConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class LipMountingConfigurationBuilder extends MountingConfigurationBuilder implements LipConfiguration {

    @Override
    protected Integer getButtonCount(Map<String, Object> config) {
        return 0;
    }

    @Override
    protected String getLopType(Map<String, Object> config) {
        return null;
    }

    @Override
    protected String getLipType(Map<String, Object> config) {
        return LipBuilderHelper.getLipType(config);
    }
}
