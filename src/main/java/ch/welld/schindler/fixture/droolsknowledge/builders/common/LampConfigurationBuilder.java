package ch.welld.schindler.fixture.droolsknowledge.builders.common;

import ch.welld.schindler.fixture.droolsknowledge.builders.AbstractConfigurationBuilder;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;
import ch.welld.schindler.fixture.droolsknowledge.components.lamps.LampConfiguration;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

public abstract class LampConfigurationBuilder extends AbstractConfigurationBuilder {

    // FIXME: these should not be hardcoded (see https://gitlab.welld.io/schindler/figs/catalog/issues/13)
    protected static List<String> LAMPS = Lists.newArrayList(
            "LAGC",
            "LARC-Communication",
            "LARC-Phone",
            "LL-X",
            "LL-X-NA",
            "LL-V",
            "LBFC",
            "LBFC/EN81-72",
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

    protected LampConfiguration createBaseConfiguration(Map<String, Object> config, String lamp) {
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
        return lp;
    }


}
