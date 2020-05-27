package ch.welld.schindler.fixture.droolsknowledge.materials;

import java.util.Map;

/**
 * `BaseMaterial` is the abstract superclass of cables and materials, which are the two kind of entities
 * that can be returned by Drools' knowledge sessions. Here common attributes are handled, such
 * as material/cable and family codes.
 */
public abstract class BaseMaterial implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private String materialCode;

    private String familyCode;

    private Map<String, Object> metadata;

    private Integer quantity = 1;

    public BaseMaterial() {
    }

    public String getMaterialCode() {
        return this.materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getFamilyCode() {
        return this.familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity == null ? 1 : quantity;
    }

    public BaseMaterial(
        String materialCode,
        String familyCode,
        Integer quantity,
        Map<String, Object> metadata
    ) {
        this.materialCode = materialCode;
        this.familyCode = familyCode;
        this.metadata = metadata;
        this.quantity = quantity == null ? 1 : quantity;
    }
}
