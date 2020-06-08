package ch.welld.schindler.fixture.droolsknowledge.components.lopkpanel;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;

public class LopKPanelConfiguration extends BaseConfiguration {

    static final long serialVersionUID = 1L;

    private String lopKType;
    private String panelPackage;
    private String positions;

    public LopKPanelConfiguration() {
    }

    public LopKPanelConfiguration(
        String lopKType,
        String panelPackage,
        String positions
    ) {
        this.lopKType = lopKType;
        this.panelPackage = panelPackage;
        this.positions = positions;
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

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }
}