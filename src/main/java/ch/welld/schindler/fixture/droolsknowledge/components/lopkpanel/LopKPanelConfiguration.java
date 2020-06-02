package ch.welld.schindler.fixture.droolsknowledge.components.lopkpanel;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;

public class LopKPanelConfiguration extends BaseConfiguration {

    static final long serialVersionUID = 1L;

    private String lopKType;
    private String panelPackage;

    public LopKPanelConfiguration() {
    }

    public LopKPanelConfiguration(
        String lopKType,
        String panelPackage
    ) {
        this.lopKType = lopKType;
        this.panelPackage = panelPackage;
    }

    public String getPanelPackage() {
        return panelPackage;
    }

    public void setPanelPackage(String panelPackage) {
        this.panelPackage = panelPackage;
    }

    public String getLopKType() {
        return lopKType;
    }

    public void setLopKType(String lopKType) {
        this.lopKType = lopKType;
    }
}