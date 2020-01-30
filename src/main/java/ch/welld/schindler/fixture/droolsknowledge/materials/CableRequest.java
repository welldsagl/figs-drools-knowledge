package ch.welld.schindler.fixture.droolsknowledge.materials;

import java.io.Serializable;

/**
 * A request for asking Drools to retrieve an `on commission` cable given its `standard` version, or vice-versa.
 */
public class CableRequest implements Serializable {

    static final long serialVersionUID = 1L;

    private String familyCode;

    private String materialCode;

    private Integer length;

    public CableRequest() {

    }

    public CableRequest(String familyCode, String materialCode, Integer length) {
        this.familyCode = familyCode;
        this.materialCode = materialCode;
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }
}
