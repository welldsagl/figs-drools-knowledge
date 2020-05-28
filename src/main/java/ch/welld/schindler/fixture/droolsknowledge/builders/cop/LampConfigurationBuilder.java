package ch.welld.schindler.fixture.droolsknowledge.builders.cop;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.lamps.LampConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.types.CopConfiguration;
import com.google.common.collect.Lists;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class LampConfigurationBuilder extends AbstractConfigurationBuilder implements CopConfiguration {

    // FIXME: these should not be hardcoded (see https://gitlab.welld.io/schindler/figs/catalog/issues/13)
    private static List<String> LAMPS = Lists.newArrayList(
            "LAGC",
            "LARC-Communication",
            "LARC-Phone",
            "LL-X",
            "LL-X-NA",
            "LL-V",
            "LBFC",
            "LBFC-Fire",
            "LBFC-Full",
            "LRVC",
            "LREC",
            "LEB",
            "LEB-NA",
            "LEFC",
            "LNO",
            "LNC",
            "LBF",
            "LAB"
    );


    @Override
    public boolean canParseConfiguration(Map<String, Object> config) {
        return "Lamps".equalsIgnoreCase((String) config.get("sections"));
    }

    private ComponentConfiguration createBaseConfiguration(Map<String, Object> config, String lamp) {
        LampConfiguration lp = new LampConfiguration();
        lp.setEn8120(((List<String>) config.get("regulations")).contains("EN 81-20"));
        lp.setLamp(lamp.toUpperCase());
        lp.setFixtureFamily((String) config.get("fixtureFamily"));
        Boolean copIntercomStation = (Boolean) config.get("copIntercomStation");
        Boolean doppelDeckerCar = (Boolean) config.get("doppelDeckerCar");
        if(copIntercomStation != null) {
            lp.setCopIntercomStation(NullableBoolean.from(copIntercomStation));
        }
        if(doppelDeckerCar != null) {
            lp.setDoppelDeckerCar(NullableBoolean.from(doppelDeckerCar));
        }
        return new ComponentConfiguration(lp, 1);
    }

    @Override
    public List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {
        return LAMPS.stream()
                .filter(lamp -> config.containsKey(lamp) && ((BigDecimal) config.get(lamp)).intValue() == 1)
                .map(lamp -> createBaseConfiguration(config, lamp))
                .collect(Collectors.toList());
    }
}