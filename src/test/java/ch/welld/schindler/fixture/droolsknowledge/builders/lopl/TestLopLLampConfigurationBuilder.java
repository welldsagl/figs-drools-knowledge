package ch.welld.schindler.fixture.droolsknowledge.builders.lopl;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.lamps.LampConfiguration;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("lop-l Lamp configuration builder")
public class TestLopLLampConfigurationBuilder {

    private final LopLLampConfigurationBuilder builder = new LopLLampConfigurationBuilder();

    private Map<String, Object> createSlot(String selection) {
        return new Maps.Builder<String,Object>()
            .put("selection", selection)
            .build();
    }

    private Map<String, Object> createConfigurationRequest(
        Map<String, Object> a1,
        Map<String, Object> a2,
        Map<String, Object> b1,
        Map<String, Object> b2
    ) {
        Map<String, Object> slots = new HashMap<>();
        if (a1 != null) slots.put("a1", a1);
        if (a2 != null) slots.put("a2", a2);
        if (b1 != null) slots.put("b1", b1);
        if (b2 != null) slots.put("b2", b2);
        return new Maps.Builder<String,Object>()
            .put( "fixtureFamily", "DT")
            .put( "lamp", slots)
            .put( "lopLType", "180x160")
            .put( "regulations", Lists.newArrayList("EN 81-20"))
            .put( "topFloors", new BigDecimal(2))
            .build();
    }

    @Test
    @DisplayName("convert into a list of lamp configurations")
    public void testCreateConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest(
            createSlot("sel_a1"),
            createSlot("sel_a2"),
            createSlot("sel_b1"),
            createSlot("sel_b2")
        );
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertEquals(4, configurations.size());
        configurations.forEach(componentConfiguration -> {
            assertTrue(componentConfiguration.getConfiguration() instanceof LampConfiguration);
            assertEquals(2, componentConfiguration.getCount());
            LampConfiguration lC = (LampConfiguration) componentConfiguration.getConfiguration();
            String position = lC.getPosition();
            assertEquals("DT", lC.getFixtureFamily());
            assertEquals("SEL_" + position.toUpperCase(), lC.getLamp());
        });
    }

    @Test
    @DisplayName("create a configuration for each requested slot")
    public void testGetAConfigurationForEachSlot() {
        Map<String, Object> configMap = createConfigurationRequest(
            createSlot("sel_a1"),
            null,
            createSlot("sel_b1"),
            null
        );
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertEquals(2, configurations.size());
    }

    @Test
    @DisplayName("convert into an empty list if no slots are provided")
    public void testEmptyConfigurationListForNoSlots() {
        Map<String, Object> configMap = createConfigurationRequest(
            null,
            null,
            null,
            null
        );
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertTrue(configurations.isEmpty());
    }

    @Test
    @DisplayName("convert into an empty list if floors quantity is zero")
    public void testEmptyConfigurationList() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
            new Maps.Builder<String, Object>()
                .put("topFloors", BigDecimal.ZERO)
                .build()
        );
        assertNotNull(configuration);
        assertTrue(configuration.isEmpty());
    }

}
