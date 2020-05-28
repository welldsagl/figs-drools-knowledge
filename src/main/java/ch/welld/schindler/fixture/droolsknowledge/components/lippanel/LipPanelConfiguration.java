package ch.welld.schindler.fixture.droolsknowledge.components.lippanel;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;

public class LipPanelConfiguration extends BaseConfiguration {

    static final long serialVersionUID = 1L;

    private Boolean withGlass;
    private String glass;
    private String panelPackage;
    private String lipType;
    private String indicatorFamily;

    public LipPanelConfiguration() {
    }

    public LipPanelConfiguration(
        Boolean withGlass,
        String glass,
        String panelPackage,
        String lipType,
        String indicatorFamily
    ) {
        this.withGlass = withGlass;
        this.glass = glass;
        this.panelPackage = panelPackage;
        this.lipType = lipType;
        this.indicatorFamily = indicatorFamily;
    }

    public Boolean getWithGlass() {
        return withGlass;
    }

    public void setWithGlass(Boolean withGlass) {
        this.withGlass = withGlass;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getPanelPackage() {
        return panelPackage;
    }

    public void setPanelPackage(String panelPackage) {
        this.panelPackage = panelPackage;
    }

    public String getLipType() {
        return lipType;
    }

    public void setLipType(String lipType) {
        this.lipType = lipType;
    }

    public String getIndicatorFamily() {
        return indicatorFamily;
    }

    public void setIndicatorFamily(String indicatorFamily) {
        this.indicatorFamily = indicatorFamily;
    }
}