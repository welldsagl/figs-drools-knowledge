package ch.welld.schindler.fixture.droolsknowledge.types;

public interface LipConfiguration extends ComponentType {

    default String getCableLengthKey() {
        return "lipCableLength";
    }

    default String getComponentType() {
        return "LIP";
    }

}
