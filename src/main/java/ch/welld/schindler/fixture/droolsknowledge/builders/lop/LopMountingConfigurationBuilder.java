package ch.welld.schindler.fixture.droolsknowledge.builders.lop;

import ch.welld.schindler.fixture.droolsknowledge.builders.common.MountingConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.types.LopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class LopMountingConfigurationBuilder extends MountingConfigurationBuilder implements LopConfiguration {

    @Override
    protected Integer getButtonCount(Map<String, Object> config) {
        return LopBuilderHelper.getButtonCount(config);
    }

    @Override
    protected String getLopType(Map<String, Object> config) {
        return LopBuilderHelper.getLopType(config);
    }
}
