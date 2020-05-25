package ch.welld.schindler.fixture.droolsknowledge.types;

/**
 * This interface exposes information common to all builders for a specific component type (COP, LOP, LIP, ..)
 */
public interface ComponentType {

    /**
     * Gets the key of the cable length value in the project configuration map.
     * @return The key of the cable length value in the project configuration map
     */
    String getCableLengthKey();

    /**
     * Gets the specific component type (COP, LOP, LIP, ..).
     * @return The specific component type
     */
    String getComponentType();

}
