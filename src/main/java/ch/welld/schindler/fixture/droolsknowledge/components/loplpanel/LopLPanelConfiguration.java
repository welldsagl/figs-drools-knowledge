package ch.welld.schindler.fixture.droolsknowledge.components.loplpanel;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;

public class LopLPanelConfiguration extends BaseConfiguration {

    static final long serialVersionUID = 1L;

    private String lopLType;
    private String panelPackage;

    public LopLPanelConfiguration() {
    }

    public LopLPanelConfiguration(
        String lopLType,
        String panelPackage
    ) {
        this.lopLType = lopLType;
        this.panelPackage = panelPackage;
    }

    public String getPanelPackage() {
        return panelPackage;
    }

    public void setPanelPackage(String panelPackage) {
        this.panelPackage = panelPackage;
    }

    public String getLopLType() {
        return lopLType;
    }

    public void setLopLType(String lopLType) {
        this.lopLType = lopLType;
    }
}