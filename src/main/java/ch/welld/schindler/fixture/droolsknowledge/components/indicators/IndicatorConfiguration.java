package ch.welld.schindler.fixture.droolsknowledge.components.indicators;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;

public class IndicatorConfiguration extends BaseConfiguration {

	static final long serialVersionUID = 1L;

	private String indicatorType;

	private String mountingType;

	private String glassCoverColor;

	private String coverSize;

	private String displayColor;

	private String language;

	private String displayLayout;

	private Boolean temperatureSensor;

	private String displaySize;

	private String edsType;

	private String lopType;

	private String lipType;

	private Boolean withGong;

	public IndicatorConfiguration() {
	}

	public String getIndicatorType() {
		return this.indicatorType;
	}

	public void setIndicatorType(String indicatorType) {
		this.indicatorType = indicatorType;
	}

	public String getMountingType() {
		return this.mountingType;
	}

	public void setMountingType(String mountingType) {
		this.mountingType = mountingType;
	}

	public String getGlassCoverColor() {
		return this.glassCoverColor;
	}

	public void setGlassCoverColor(String glassCoverColor) {
		this.glassCoverColor = glassCoverColor;
	}

	public String getCoverSize() {
		return this.coverSize;
	}

	public void setCoverSize(String coverSize) {
		this.coverSize = coverSize;
	}

	public String getDisplayColor() {
		return this.displayColor;
	}

	public void setDisplayColor(String displayColor) {
		this.displayColor = displayColor;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDisplayLayout() {
		return this.displayLayout;
	}

	public void setDisplayLayout(String displayLayout) {
		this.displayLayout = displayLayout;
	}

	public Boolean getTemperatureSensor() {
		return this.temperatureSensor;
	}

	public void setTemperatureSensor(Boolean temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	public String getDisplaySize() {
		return this.displaySize;
	}

	public void setDisplaySize(String displaySize) {
		this.displaySize = displaySize;
	}

	public String getEdsType() {
		return this.edsType;
	}

	public void setEdsType(String edsType) {
		this.edsType = edsType;
	}

	public String getLopType() {
		return this.lopType;
	}

	public void setLopType(String lopType) {
		this.lopType = lopType;
	}

	public String getLipType() {
		return this.lipType;
	}

	public void setLipType(String lipType) {
		this.lipType = lipType;
	}

	public java.lang.Boolean getWithGong() {
		return this.withGong;
	}

	public void setWithGong(java.lang.Boolean withGong) {
		this.withGong = withGong;
	}

	public IndicatorConfiguration(
		String indicatorType,
		String mountingType,
		String glassCoverColor,
		String coverSize,
		String displayColor,
		String language,
		String displayLayout,
		Boolean temperatureSensor,
		String displaySize,
		String edsType,
		String lopType,
		String lipType,
		Boolean withGong
	) {
		this.indicatorType = indicatorType;
		this.mountingType = mountingType;
		this.glassCoverColor = glassCoverColor;
		this.coverSize = coverSize;
		this.displayColor = displayColor;
		this.language = language;
		this.displayLayout = displayLayout;
		this.temperatureSensor = temperatureSensor;
		this.displaySize = displaySize;
		this.edsType = edsType;
		this.lopType = lopType;
		this.lipType = lipType;
		this.withGong = withGong;
	}

}