package ch.welld.schindler.fixture.droolsknowledge.materials;

import java.util.Map;

public class Cable extends BaseMaterial {

    private Integer length;

    public Cable() {
        super();
    }

    public Cable(
        String materialCode,
        String familyCode,
        Integer length,
        Integer quantity,
        Map<String, Object> metadata
    ) {
        super(materialCode, familyCode, quantity, metadata);
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
