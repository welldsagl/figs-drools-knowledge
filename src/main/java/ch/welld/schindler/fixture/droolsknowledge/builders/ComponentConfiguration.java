package ch.welld.schindler.fixture.droolsknowledge.builders;

import javax.json.bind.JsonbBuilder;

public class ComponentConfiguration {

    private final Object configuration;

    private final int count;

    public ComponentConfiguration(Object configuration, int count) {
        this.configuration = configuration;
        this.count = count;
    }

    public Object getConfiguration() {
        return configuration;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return JsonbBuilder.create().toJson(this);
    }
}
