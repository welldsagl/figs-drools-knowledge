package ch.welld.schindler.fixture.droolsknowledge.materials;

import java.util.Map;

public class Material extends BaseMaterial {

	static final long serialVersionUID = 1L;

	public Material() {
		super();
	}

	public Material(
		String materialCode,
		String familyCode,
		Integer quantity,
		Map<String, Object> metadata
	) {
		super(materialCode, familyCode, quantity, metadata);
	}

}