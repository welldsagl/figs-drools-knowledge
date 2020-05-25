package ch.welld.schindler.fixture.droolsknowledge.types;

public interface CopConfiguration extends ComponentType {

    default String getCableLengthKey() {
        return "internalCopCableLength";
    }

    default String getComponentType() {
        return "COP";
    }

}
