package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.indicators.IndicatorConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class CopIndicatorConfigurationBuilder extends AbstractConfigurationBuilder implements CopConfiguration {

    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Indicator".equalsIgnoreCase((String) config.get("sections"));
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        IndicatorConfiguration ic = new IndicatorConfiguration();
        ic.setCoverSize((String) config.get("coverSize"));
        ic.setDisplayColor(Optional
            .ofNullable(config.get("color"))
            .map(text -> ((String) text).toUpperCase())
            .orElse(null));
        ic.setDisplayLayout((String) config.get("displayLayout"));
        ic.setDisplaySize(Optional
            .ofNullable(config.get("displaySize"))
            .map(text -> ((String) text).substring(0, ((String) text).indexOf("\"")))
            .orElse(null));
        ic.setGlassCoverColor(Optional
            .ofNullable(config.get("glassCover"))
            .map(text -> ((String) text).toUpperCase())
            .orElse(null));
        ic.setIndicatorType((String) config.get("indicators"));
        ic.setMountingType((String) config.get("mountingType"));
        ic.setTemperatureSensor((Boolean)config.get("temperatureSensor"));
        ic.setLanguage((String) config.get("language"));
        ic.setEdsType(Optional
            .ofNullable(config.get("edsType"))
            .map(text -> ((String) text).toUpperCase())
            .orElse(null));

        return Collections.singletonList(new ComponentConfiguration(ic,1));
    }

}
