package ch.welld.schindler.fixture.droolsknowledge.builders;

import org.junit.jupiter.api.DisplayName;

//import ch.welld.schindler.fixture.droolsknowledge.components.ksindicators.KSIndicatorConfiguration;

@DisplayName("test ks indicator configuration builder")
public class TestKsIndicatorConfigurationBuilder {

//    private KsIndicatorConfigurationBuilder builder = new KsIndicatorConfigurationBuilder();
//
//    @Test
//    @DisplayName("can convert a ks indicator configuration")
//    public void testCanParseKsIndicatorConfiguration() {
//        assertTrue(
//            builder.canParseConfiguration(Maps.of("elevator", "KS Indicator"))
//        );
//    }
//
//    @Test
//    @DisplayName("cannot convert a not ks indicator configuration")
//    public void testCannotParseNonKsIndicatorConfiguration() {
//        assertFalse(
//            builder.canParseConfiguration(Maps.of("elevator", "Fixtures"))
//        );
//    }
//
//    @Test
//    @DisplayName("cannot convert a configuration with no 'elevator' field")
//    public void testCannotParseConfigurationWithNoElevatorKey() {
//        assertFalse(
//            builder.canParseConfiguration(Collections.emptyMap())
//        );
//    }
//
//    @Test
//    @DisplayName("convert a correct configuration")
//    public void testParseConfiguration() {
//        List<ComponentConfiguration> configuration = builder.getConfigurations(
//                Maps.of(
//                        "type", "TFT",
//                        "color", "Amber"
//                )
//        );
//        assertNotNull(configuration);
//        assertEquals(1, configuration.size());
//        assertEquals(1, configuration.get(0).getCount());
//        assertTrue(configuration.get(0).getConfiguration() instanceof KSIndicatorConfiguration);
//        KSIndicatorConfiguration ksIndicatorConfiguration =
//                (KSIndicatorConfiguration) configuration.get(0).getConfiguration();
//        assertEquals("TFT", ksIndicatorConfiguration.getType());
//        assertEquals("AMBER", ksIndicatorConfiguration.getColor());
//    }
//
//    @Test
//    @DisplayName("throws the correct exception with an incorrect configuration")
//    public void testThrowsInvalidConfigurationFormatException() {
//        assertThrows(
//            InvalidConfigurationFormatException.class,
//            () -> builder.getConfigurations(
//                Maps.of("type", 1)
//            )
//        );
//    }

}
