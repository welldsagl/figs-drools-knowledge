package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.thirdpartelement.ThirdPartElementConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ThirdPartElementConfigurationBuilder extends AbstractConfigurationBuilder implements CopConfiguration {
    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        // TODO IS IT OK?
        return "thirdpartelements".equalsIgnoreCase((String) config.get("elevator"));
    }

    @Override
    protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        ThirdPartElementConfiguration tpec = new ThirdPartElementConfiguration();
        int quantity = ((BigDecimal) config.getOrDefault("quantity", BigDecimal.ONE)).intValue();
        return Collections.singletonList(new ComponentConfiguration(tpec, quantity));
    }

}
