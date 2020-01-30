package ch.welld.schindler.fixture.droolsknowledge.components.ldb2;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.NullableBoolean;

public class Ldb2Configuration extends BaseConfiguration {

    static final long serialVersionUID = 1L;

    private NullableBoolean hiddenBox;

    public Ldb2Configuration() {}

    public Ldb2Configuration(NullableBoolean hiddenBox) {
        this.hiddenBox = hiddenBox;
    }

    public NullableBoolean getHiddenBox() {
        return hiddenBox;
    }

    public void setHiddenBox(NullableBoolean hiddenBox) {
        this.hiddenBox = hiddenBox;
    }
}
