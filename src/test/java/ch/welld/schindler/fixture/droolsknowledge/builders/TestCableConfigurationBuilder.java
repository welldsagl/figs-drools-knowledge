package ch.welld.schindler.fixture.droolsknowledge.builders;

import ch.welld.schindler.fixture.droolsknowledge.materials.CableLengthType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.soup.commons.util.Maps;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test cable configuration builder")
public class TestCableConfigurationBuilder {

    private AbstractConfigurationBuilder builder = new AbstractConfigurationBuilder() {
        @Override
        protected List<ComponentConfiguration> getConfigurationsImpl(Map<String, Object> config) {

            BaseConfiguration outputConfiguration = new BaseConfiguration() {};
            return Collections.singletonList(
                    new ComponentConfiguration(
                            outputConfiguration,
                            1
                    )
            );
        }

        @Override
        public boolean canParseConfiguration(Map<String, Object> config) {
            return true;
        }
    };

    @Test
    @DisplayName("convert a correct configuration")
    public void testParseConfiguration() {
        List<ComponentConfiguration> configuration = builder.getConfigurations(
                new Maps.Builder<String,Object>()
                        .put("cableType", "PVC/CCC")
                        .put( "internalCopCableLength", BigDecimal.valueOf(123))
                        .put( "cableLengthType", "ON_COMMISSION")
                        .build()
        );
        assertNotNull(configuration);
        BaseConfiguration cableConfiguration = configuration.get(0).getConfiguration();
        assertEquals("PVC/CCC", cableConfiguration.getCableType());
        assertEquals(123, cableConfiguration.getCableLength());
        assertEquals(CableLengthType.ON_COMMISSION, cableConfiguration.getCableLengthType());
    }

}
