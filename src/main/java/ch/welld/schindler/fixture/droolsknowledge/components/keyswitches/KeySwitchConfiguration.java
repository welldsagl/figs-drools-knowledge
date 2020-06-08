package ch.welld.schindler.fixture.droolsknowledge.components.keyswitches;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;

public class KeySwitchConfiguration extends BaseConfiguration {

	static final long serialVersionUID = 1L;

	private String keyType;
	private String keySwitch;
	private String keyFunction;
	private String engraving;
	private String fixtureFamily;
	private String position;
	private Boolean critical;
	private Integer criticalQuantity;

	public KeySwitchConfiguration() {
	}

	public String getKeyType() {
		return this.keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getKeySwitch() {
		return this.keySwitch;
	}

	public void setKeySwitch(String keySwitch) {
		this.keySwitch = keySwitch;
	}

	public String getKeyFunction() {
		return this.keyFunction;
	}

	public void setKeyFunction(String keyFunction) {
		this.keyFunction = keyFunction;
	}

	public String getEngraving() {
		return this.engraving;
	}

	public void setEngraving(String engraving) {
		this.engraving = engraving;
	}

	public String getFixtureFamily() {
		return this.fixtureFamily;
	}

	public void setFixtureFamily(String fixtureFamily) {
		this.fixtureFamily = fixtureFamily;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Boolean getCritical() {
		return this.critical;
	}

	public void setCritical(Boolean critical) {
		this.critical = critical;
	}

	public Integer getCriticalQuantity() {
		return criticalQuantity;
	}

	public void setCriticalQuantity(Integer criticalQuantity) {
		this.criticalQuantity = criticalQuantity;
	}

	public KeySwitchConfiguration(
		String keyType,
		String keySwitch,
		String keyFunction,
		String engraving,
		String fixtureFamily,
		String position,
		Boolean critical,
		Integer criticalQuantity
	) {
		this.keyType = keyType;
		this.keySwitch = keySwitch;
		this.keyFunction = keyFunction;
		this.engraving = engraving;
		this.fixtureFamily = fixtureFamily;
		this.position = position;
		this.critical = critical;
		this.criticalQuantity = criticalQuantity;
	}


}