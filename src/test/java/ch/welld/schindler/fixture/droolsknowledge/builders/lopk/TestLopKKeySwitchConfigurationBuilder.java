package ch.welld.schindler.fixture.droolsknowledge.builders.lopk;

import ch.welld.schindler.fixture.droolsknowledge.builders.ComponentConfiguration;
import ch.welld.schindler.fixture.droolsknowledge.components.keyswitches.KeySwitchConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("lop key Switch configuration builder")
public class TestLopKKeySwitchConfigurationBuilder {

    private final LopKKeySwitchConfigurationBuilder builder = new LopKKeySwitchConfigurationBuilder();

    private Map<String, Object> createSlot(
        String category,
        String selection,
        String text,
        Boolean critical,
        BigDecimal criticalQuantity
    ) {
        return new Maps.Builder<String,Object>()
            .put("category", category)
            .put("selection", selection)
            .put("text", text)
            .put("critical", critical)
            .put("criticalQuantity", criticalQuantity)
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
            .put("keyType", "KABA")
            .put( "fixtureFamily", "DT")
            .put( "keySwitch", slots)
            .put( "topFloors", new BigDecimal(2))
            .build();
    }

    @Test
    @DisplayName("convert into a list of key switch configurations")
    public void testCreateConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest(
            createSlot("cat_a1", "sel_a1", "text_a1", false, null),
            createSlot("cat_a2", "sel_a2", "text_a2", false, null),
            createSlot("cat_b1", "sel_b1", "text_b1", false, null),
            createSlot("cat_b2", "sel_b2", "text_b2", false, null)
        );
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertNotNull(configurations);
        assertEquals(4, configurations.size());
        configurations.forEach(componentConfiguration -> {
            assertTrue(componentConfiguration.getConfiguration() instanceof KeySwitchConfiguration);
            assertEquals(2, componentConfiguration.getCount());
            KeySwitchConfiguration ksC = (KeySwitchConfiguration) componentConfiguration.getConfiguration();
            String position = ksC.getPosition();
            assertEquals("text_" + position, ksC.getEngraving());
            assertEquals("DT", ksC.getFixtureFamily());
            assertEquals("sel_" + position, ksC.getKeyFunction());
            assertEquals("CAT_" + position.toUpperCase(), ksC.getKeySwitch());
            assertEquals("KABA", ksC.getKeyType());
            assertEquals(false, ksC.getCritical());
            assertEquals(0, ksC.getCriticalQuantity());
        });
    }

    @Test
    @DisplayName("create a configuration for each requested slot")
    public void testGetAConfigurationForEachSlot() {
        Map<String, Object> configMap = createConfigurationRequest(
            createSlot("cat_a1", "sel_a1", "text_a1", false, null),
            null,
            createSlot("cat_b1", "sel_b1", "text_b1", false, null),
            null
        );
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertEquals(2, configurations.size());
    }

    @Test
    @DisplayName("create a configuration for a critical slot")
    public void testGetCriticalConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest(
            null,
            null,
            createSlot("cat_b1", "sel_b1", "text_b1", true, new BigDecimal(4)),
            null
        );
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertEquals(1, configurations.size());
        KeySwitchConfiguration criticalConfig = (KeySwitchConfiguration) configurations.get(0).getConfiguration();
        assertEquals("text_b1", criticalConfig.getEngraving());
        assertEquals("sel_b1", criticalConfig.getKeyFunction());
        assertEquals("CAT_B1", criticalConfig.getKeySwitch());
        assertEquals(true, criticalConfig.getCritical());
        assertEquals(4, criticalConfig.getCriticalQuantity());
    }

    @Test
    @DisplayName("create a non-critical configuration if critical quantity is zero")
    public void testGetNonCriticalWithZeroQuantityConfiguration() {
        Map<String, Object> configMap = createConfigurationRequest(
            null,
            null,
            createSlot("cat_b1", "sel_b1", "text_b1", true, new BigDecimal(0)),
            null
        );
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        KeySwitchConfiguration criticalConfig = (KeySwitchConfiguration) configurations.get(0).getConfiguration();
        assertEquals(false, criticalConfig.getCritical());
        assertEquals(0, criticalConfig.getCriticalQuantity());
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

    @Test
    @DisplayName("create non-critical configuration if critical flag is not provided")
    public void testGetCriticalConfigurationError() {
        Map<String, Object> configMap = createConfigurationRequest(
                null,
                null,
                createSlot("cat_b1", "sel_b1", "text_b1", null, new BigDecimal(4)),
                null
        );
        List<ComponentConfiguration> configurations = builder.getConfigurationsImpl(configMap);
        assertEquals(1, configurations.size());
        KeySwitchConfiguration criticalConfig = (KeySwitchConfiguration) configurations.get(0).getConfiguration();
        assertEquals(false, criticalConfig.getCritical());
    }

}
