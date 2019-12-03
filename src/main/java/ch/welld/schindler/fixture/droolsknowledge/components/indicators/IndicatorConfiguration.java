package ch.welld.schindler.fixture.droolsknowledge.components.indicators;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class IndicatorConfiguration implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	@org.kie.api.definition.type.Label(value = "indicatorType")
	private java.lang.String indicatorType;
	@org.kie.api.definition.type.Label(value = "mountingType")
	private java.lang.String mountingType;
	@org.kie.api.definition.type.Label(value = "glassCoverColor")
	private java.lang.String glassCoverColor;
	@org.kie.api.definition.type.Label(value = "coverSize")
	private java.lang.String coverSize;
	@org.kie.api.definition.type.Label(value = "displayColor")
	private java.lang.String displayColor;
	@org.kie.api.definition.type.Label(value = "language")
	private java.lang.String language;
	@org.kie.api.definition.type.Label(value = "displayLayout")
	private java.lang.String displayLayout;
	@org.kie.api.definition.type.Label(value = "temperatureSensor")
	private java.lang.Boolean temperatureSensor;
	@org.kie.api.definition.type.Label(value = "displaySize")
	private java.lang.String displaySize;

	public IndicatorConfiguration() {
	}

	public java.lang.String getIndicatorType() {
		return this.indicatorType;
	}

	public void setIndicatorType(java.lang.String indicatorType) {
		this.indicatorType = indicatorType;
	}

	public java.lang.String getMountingType() {
		return this.mountingType;
	}

	public void setMountingType(java.lang.String mountingType) {
		this.mountingType = mountingType;
	}

	public java.lang.String getGlassCoverColor() {
		return this.glassCoverColor;
	}

	public void setGlassCoverColor(java.lang.String glassCoverColor) {
		this.glassCoverColor = glassCoverColor;
	}

	public java.lang.String getCoverSize() {
		return this.coverSize;
	}

	public void setCoverSize(java.lang.String coverSize) {
		this.coverSize = coverSize;
	}

	public java.lang.String getDisplayColor() {
		return this.displayColor;
	}

	public void setDisplayColor(java.lang.String displayColor) {
		this.displayColor = displayColor;
	}

	public java.lang.String getLanguage() {
		return this.language;
	}

	public void setLanguage(java.lang.String language) {
		this.language = language;
	}

	public java.lang.String getDisplayLayout() {
		return this.displayLayout;
	}

	public void setDisplayLayout(java.lang.String displayLayout) {
		this.displayLayout = displayLayout;
	}

	public java.lang.Boolean getTemperatureSensor() {
		return this.temperatureSensor;
	}

	public void setTemperatureSensor(java.lang.Boolean temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	public java.lang.String getDisplaySize() {
		return this.displaySize;
	}

	public void setDisplaySize(java.lang.String displaySize) {
		this.displaySize = displaySize;
	}

	public IndicatorConfiguration(java.lang.String indicatorType,
			java.lang.String mountingType, java.lang.String glassCoverColor,
			java.lang.String coverSize, java.lang.String displayColor,
			java.lang.String language, java.lang.String displayLayout,
			java.lang.Boolean temperatureSensor, java.lang.String displaySize) {
		this.indicatorType = indicatorType;
		this.mountingType = mountingType;
		this.glassCoverColor = glassCoverColor;
		this.coverSize = coverSize;
		this.displayColor = displayColor;
		this.language = language;
		this.displayLayout = displayLayout;
		this.temperatureSensor = temperatureSensor;
		this.displaySize = displaySize;
	}

}