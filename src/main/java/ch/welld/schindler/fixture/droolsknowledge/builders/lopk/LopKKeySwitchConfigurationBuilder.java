package ch.welld.schindler.fixture.droolsknowledge.builders.lopk;

import ch.welld.schindler.fixture.droolsknowledge.builders.common.FloorsQuantityHelper;
import ch.welld.schindler.fixture.droolsknowledge.builders.common.KeySwitchConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.types.LopKConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class LopKKeySwitchConfigurationBuilder extends KeySwitchConfigurationBuilder implements LopKConfiguration {

    @Override
    protected int getQuantity(Map<String, Object> config) {
        return FloorsQuantityHelper.getTotalFloorsCount(config);
    }
}
