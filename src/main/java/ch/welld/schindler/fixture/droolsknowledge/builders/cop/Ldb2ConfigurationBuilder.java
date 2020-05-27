package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.ldb2.Ldb2Configuration;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Ldb2ConfigurationBuilder extends AbstractConfigurationBuilder implements CopConfiguration {
    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "LDB2".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        Ldb2Configuration ldb2Configuration = new Ldb2Configuration();
        ldb2Configuration.setHiddenBox(NullableBoolean.from((Boolean) config.get("hiddenBox")));
        return Collections.singletonList(new ComponentConfiguration(ldb2Configuration, 1));
    }
}
