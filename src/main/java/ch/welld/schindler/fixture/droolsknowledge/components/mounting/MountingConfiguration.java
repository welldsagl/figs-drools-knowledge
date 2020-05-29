package ch.welld.schindler.fixture.droolsknowledge.components.mounting;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;

public class MountingConfiguration extends BaseConfiguration {

    static final long serialVersionUID = 1L;

    private String mountingType;

    private NullableBoolean ipx3;

    private String lopType;

    private String lipType;

    public MountingConfiguration() {
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

    public String getLopType() {
        return this.lopType;
    }

    public void setLopType(String lopType) {
        this.lopType = lopType;
    }

    public String getLipType() {
        return this.lipType;
    }

    public void setLipType(String lipType) {
        this.lipType = lipType;
    }

    public MountingConfiguration(
        String mountingType,
        NullableBoolean ipx3,
        String lopType,
        String lipType
    ) {
        this.mountingType = mountingType;
        this.ipx3 = ipx3;
        this.lopType = lopType;
        this.lipType = lipType;
    }
}
