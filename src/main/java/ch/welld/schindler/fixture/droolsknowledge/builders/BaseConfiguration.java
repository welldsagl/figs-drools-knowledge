package ch.welld.schindler.fixture.droolsknowledge.builders;

import ch.welld.schindler.fixture.droolsknowledge.materials.CableLengthType;

import java.io.Serializable;

/**
 * Abstract superclass of all drools-known configuration classes. This handles all common attributes,
 * such as cable-related ones.
 */
public abstract class BaseConfiguration implements Serializable {

    static final long serialVersionUID = 1L;

    protected CableLengthType cableLengthType;

    protected Integer cableLength;

    protected java.lang.String cableType;

    protected BaseConfiguration() {

    }


    public Integer getCableLength() {
        return cableLength;
    }

    public void setCableLength(Integer cableLength) {
        this.cableLength = cableLength;
    }

    public CableLengthType getCableLengthType() {
        return cableLengthType;
    }

    public void setCableLengthType(CableLengthType cableLengthType) {
        this.cableLengthType = cableLengthType;
    }

    public String getCableType() {
        return cableType;
    }

    public void setCableType(String cableType) {
        this.cableType = cableType;
    }
}
