package ch.welld.schindler.fixture.droolsknowledge.materials;

public class Cable extends BaseMaterial {

    private java.lang.Integer length;

    public Cable() {
        super();
    }

    public Cable(java.lang.String materialCode, java.lang.String familyCode,
                 java.util.Map<java.lang.String, java.lang.Object> metadata,
                 java.lang.Integer length) {
        super(materialCode, familyCode, metadata);
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
