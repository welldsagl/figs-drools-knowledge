package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.common.KeySwitchConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ApplicationScoped
public class CopKeySwitchConfigurationBuilder extends KeySwitchConfigurationBuilder implements CopConfiguration {
    @Override
    protected int getQuantity(Map<String, Object> config) {
        return 1;
    }
}
