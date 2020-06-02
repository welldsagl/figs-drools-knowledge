package ch.welld.schindler.fixture.droolsknowledge.types;

public interface LopLConfiguration extends ComponentType {

    default String getCableLengthKey() {
        return "lopCableLength";
    }

    default String getComponentType() {
        return "LOP_L";
    }

}
