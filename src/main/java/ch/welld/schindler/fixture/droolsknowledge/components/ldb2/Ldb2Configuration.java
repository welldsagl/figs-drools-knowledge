package ch.welld.schindler.fixture.droolsknowledge.components.ldb2;

public class Ldb2Configuration implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private Boolean hiddenBox;

    public Ldb2Configuration() {}

    public Ldb2Configuration(Boolean hiddenBox) {
        this.hiddenBox = hiddenBox;
    }

    public Boolean getHiddenBox() {
        return hiddenBox;
    }

    public void setHiddenBox(Boolean hiddenBox) {
        this.hiddenBox = hiddenBox;
    }
}
