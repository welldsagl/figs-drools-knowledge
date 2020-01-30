package ch.welld.schindler.fixture.droolsknowledge.materials;

/**
 * `BaseMaterial` is the abstract superclass of cables and materials, which are the two kind of entities
 * that can be returned by Drools' knowledge sessions. Here common attributes are handled, such
 * as material/cable and family codes.
 */
public abstract class BaseMaterial implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private java.lang.String materialCode;

    private java.lang.String familyCode;

    private java.util.Map<java.lang.String, java.lang.Object> metadata;

    public BaseMaterial() {
    }

    public java.lang.String getMaterialCode() {
        return this.materialCode;
    }

    public void setMaterialCode(java.lang.String materialCode) {
        this.materialCode = materialCode;
    }

    public java.lang.String getFamilyCode() {
        return this.familyCode;
    }

    public void setFamilyCode(java.lang.String familyCode) {
        this.familyCode = familyCode;
    }

    public java.util.Map<java.lang.String, java.lang.Object> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(java.util.Map<java.lang.String, java.lang.Object> metadata) {
        this.metadata = metadata;
    }

    public BaseMaterial(java.lang.String materialCode, java.lang.String familyCode,
                    java.util.Map<java.lang.String, java.lang.Object> metadata) {
        this.materialCode = materialCode;
        this.familyCode = familyCode;
        this.metadata = metadata;
    }

}
