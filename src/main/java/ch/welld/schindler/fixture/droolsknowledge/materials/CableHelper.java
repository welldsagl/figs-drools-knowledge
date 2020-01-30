package ch.welld.schindler.fixture.droolsknowledge.materials;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;
import org.drools.core.spi.Tuple;

/**
 * Utility class for Drools' `then` part of rules. This enables to call a different logic of creation of a cable
 * given the `BaseConfiguration` or `CableRequest` received.
 */
public class CableHelper {

    private CableHelper() {}

    private static Cable createCableFromBaseConfiguration(
            BaseConfiguration config,
            String familyCode,
            String standardCableCode,
            Integer standardCableLength,
            String onCommissionCableCode
    ) {
        Cable cable = new Cable();
        cable.setFamilyCode(familyCode);

        if (config.getCableLengthType() == CableLengthType.ON_COMMISSION && !onCommissionCableCode.equals("null")) {
            cable.setMaterialCode(onCommissionCableCode);
            cable.setLength(config.getCableLength());
        } else {
            cable.setMaterialCode(standardCableCode);
            cable.setLength(standardCableLength);
        }

        return cable;
    }
    
    private static Cable createCableFromCableRequest(
            CableRequest cableRequest,
            String familyCode,
            String standardCableCode,
            Integer standardCableLength,
            String onCommissionCableCode
    ) {
        Cable cable = new Cable();
        cable.setFamilyCode(familyCode);

        // Switching from standard cable to on commission one
        if (cableRequest.getMaterialCode().equals(standardCableCode) && !onCommissionCableCode.equals("null")) {
            cable.setMaterialCode(onCommissionCableCode);
            cable.setLength(cableRequest.getLength());
            return cable;
        }
        // Switching from on commission cable to standard one
        if (cableRequest.getMaterialCode().equals(onCommissionCableCode)){
            cable.setMaterialCode(standardCableCode);
            cable.setLength(standardCableLength);
            return cable;
        }

        return null;
    }

    /**
     * Create a cable from the fact that triggered a rule.
     * @param tuple An object containing the fact that triggered the rule. In our case it can be a
     *              class extending `BaseConfiguration` or a specific `CableRequest`
     * @param familyCode The family code of the cable returned by the rule
     * @param standardCableCode The code of the standard version of the cable returned by the rule
     * @param standardCableLength The length of the standard version of the cable returned by the rule
     * @param onCommissionCableCode The code of the on commission version of the cable returned by the rule
     * @return A cable with the given family code and the appropriate material code and length
     */
    public static Cable createCable(Tuple tuple, String familyCode, String standardCableCode, Integer standardCableLength, String onCommissionCableCode) {
        Object request = tuple.toObjects()[0];

        Cable cable = new Cable();
        cable.setFamilyCode(familyCode);

        // "Classic" call for getting cables needed for a given component configuration
        if (request instanceof BaseConfiguration) {
            return createCableFromBaseConfiguration((BaseConfiguration) request, familyCode, standardCableCode, standardCableLength, onCommissionCableCode);
        }
        // Call for switching from a standard cable to the corresponding on commission one, or vice-versa
        if (request instanceof CableRequest) {
            return createCableFromCableRequest((CableRequest) request, familyCode, standardCableCode, standardCableLength, onCommissionCableCode);
        }

        return null;
    }
}
