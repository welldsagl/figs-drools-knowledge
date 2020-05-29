package ch.welld.schindler.fixture.droolsknowledge.components.mounting;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;

public class SealingFoamConfiguration extends BaseConfiguration {

    static final long serialVersionUID = 1L;

    private String mountingType;

    private NullableBoolean ipx3;

    private String fixtureFamily;

    private String lopType;

    public SealingFoamConfiguration() {
    }

    public String getMountingType() {
        return mountingType;
    }

    public void setMountingType(String mountingType) {
        this.mountingType = mountingType;
    }

    public NullableBoolean getIpx3() {
        return ipx3;
    }

    public void setIpx3(NullableBoolean ipx3) {
        this.ipx3 = ipx3;
    }

    public String getFixtureFamily() {
        return this.fixtureFamily;
    }

    public void setFixtureFamily(String fixtureFamily) {
        this.fixtureFamily = fixtureFamily;
    }

    public String getLopType() {
        return this.lopType;
    }

    public void setLopType(String lopType) {
        this.lopType = lopType;
    }

    public SealingFoamConfiguration(
        String mountingType,
        NullableBoolean ipx3,
        String fixtureFamily,
        String lopType
    ) {
        this.mountingType = mountingType;
        this.ipx3 = ipx3;
        this.fixtureFamily = fixtureFamily;
        this.lopType = lopType;
    }
}
