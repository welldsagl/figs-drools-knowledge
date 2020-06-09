package ch.welld.schindler.fixture.droolsknowledge.components.loplpanel;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;

public class LopLPanelConfiguration extends BaseConfiguration {

    static final long serialVersionUID = 1L;

    private String lopLType;
    private String panelPackage;
    private String positions;

    public LopLPanelConfiguration() {
    }

    public LopLPanelConfiguration(
        String lopLType,
        String panelPackage,
        String positions
    ) {
        this.lopLType = lopLType;
        this.panelPackage = panelPackage;
        this.positions = positions;
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

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }
}