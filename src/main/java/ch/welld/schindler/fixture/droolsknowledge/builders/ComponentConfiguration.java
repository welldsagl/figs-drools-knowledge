package ch.welld.schindler.fixture.droolsknowledge.builders;

public class ComponentConfiguration {

    private final BaseConfiguration configuration;

    private final int count;

    public ComponentConfiguration(BaseConfiguration configuration, int count) {
        this.configuration = configuration;
        this.count = count;
    }

    public BaseConfiguration getConfiguration() {
        return configuration;
    }

    public int getCount() {
        return count;
    }
}
