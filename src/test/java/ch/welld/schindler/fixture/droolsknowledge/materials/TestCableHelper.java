package ch.welld.schindler.fixture.droolsknowledge.materials;

import ch.welld.schindler.fixture.droolsknowledge.builders.BaseConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test cable helper")
public class TestCableHelper {

    private BaseConfiguration generateConfiguration(CableLengthType lengthType) {
        BaseConfiguration baseConfiguration = new BaseConfiguration() {};
        baseConfiguration.setCableLength(ON_COMMISSION_LENGTH);
        baseConfiguration.setCableLengthType(lengthType);
        return baseConfiguration;
    }

    private static final String FAMILY_CODE = "fc";
    private static final String STANDARD_CABLE_CODE = "st";
    private static final String ON_COMMISSION_CABLE_CODE = "oc";
    private static final Integer STANDARD_LENGTH = 100;
    private static final Integer ON_COMMISSION_LENGTH = 200;

    @Test
    @DisplayName("get a standard cable from a standard configuration")
    public void testGetStandardCableFromStandardConfig() {
        BaseConfiguration config = generateConfiguration(CableLengthType.STANDARD);
        Cable cable = CableHelper.createCableFromBaseConfiguration(
                config,
                FAMILY_CODE,
                STANDARD_CABLE_CODE,
                STANDARD_LENGTH,
                ON_COMMISSION_CABLE_CODE);
        assertNotNull(cable);
        assertEquals(FAMILY_CODE, cable.getFamilyCode());
        assertEquals(STANDARD_CABLE_CODE, cable.getMaterialCode());
        assertEquals(STANDARD_LENGTH, cable.getLength());
    }

    @Test
    @DisplayName("get an on commission cable from an on commission configuration")
    public void testGetOnCommissionCableFromOnCommissionConfig() {
        BaseConfiguration config = generateConfiguration(CableLengthType.ON_COMMISSION);
        Cable cable = CableHelper.createCableFromBaseConfiguration(
                config,
                FAMILY_CODE,
                STANDARD_CABLE_CODE,
                STANDARD_LENGTH,
                ON_COMMISSION_CABLE_CODE);
        assertNotNull(cable);
        assertEquals(FAMILY_CODE, cable.getFamilyCode());
        assertEquals(ON_COMMISSION_CABLE_CODE, cable.getMaterialCode());
        assertEquals(ON_COMMISSION_LENGTH, cable.getLength());
    }

    @Test
    @DisplayName("get an on commission cable from a standard configuration if the standard cable is not available")
    public void testGetOnCommissionCableFromStandardConfig() {
        BaseConfiguration config = generateConfiguration(CableLengthType.STANDARD);
        Cable cable = CableHelper.createCableFromBaseConfiguration(
                config,
                FAMILY_CODE,
                null,
                null,
                ON_COMMISSION_CABLE_CODE);
        assertNotNull(cable);
        assertEquals(FAMILY_CODE, cable.getFamilyCode());
        assertEquals(ON_COMMISSION_CABLE_CODE, cable.getMaterialCode());
        assertEquals(ON_COMMISSION_LENGTH, cable.getLength());
    }

    @Test
    @DisplayName("get a standard cable from an on commission configuration if the on commission cable is not available")
    public void testGetStandardCableFromOnCommissionConfig() {
        BaseConfiguration config = generateConfiguration(CableLengthType.ON_COMMISSION);
        Cable cable = CableHelper.createCableFromBaseConfiguration(
                config,
                FAMILY_CODE,
                STANDARD_CABLE_CODE,
                STANDARD_LENGTH,
                null);
        assertNotNull(cable);
        assertEquals(FAMILY_CODE, cable.getFamilyCode());
        assertEquals(STANDARD_CABLE_CODE, cable.getMaterialCode());
        assertEquals(STANDARD_LENGTH, cable.getLength());
    }

    @Test
    @DisplayName("get an on commission cable given its sibling standard cable")
    public void testGetOnCommissionCableFromStandardCable() {
        CableRequest cableRequest = new CableRequest(
                FAMILY_CODE,
                STANDARD_CABLE_CODE,
                300
        );
        Cable cable = CableHelper.createCableFromCableRequest(
                cableRequest,
                FAMILY_CODE,
                STANDARD_CABLE_CODE,
                STANDARD_LENGTH,
                ON_COMMISSION_CABLE_CODE);
        assertNotNull(cable);
        assertEquals(FAMILY_CODE, cable.getFamilyCode());
        assertEquals(ON_COMMISSION_CABLE_CODE, cable.getMaterialCode());
        assertEquals(300, cable.getLength());
    }

    @Test
    @DisplayName("get a standard cable given its sibling on commission cable")
    public void testGetStandardCableFromOnCommissionCable() {
        CableRequest cableRequest = new CableRequest(
                FAMILY_CODE,
                ON_COMMISSION_CABLE_CODE,
                300
        );
        Cable cable = CableHelper.createCableFromCableRequest(
                cableRequest,
                FAMILY_CODE,
                STANDARD_CABLE_CODE,
                STANDARD_LENGTH,
                ON_COMMISSION_CABLE_CODE);
        assertNotNull(cable);
        assertEquals(FAMILY_CODE, cable.getFamilyCode());
        assertEquals(STANDARD_CABLE_CODE, cable.getMaterialCode());
        assertEquals(STANDARD_LENGTH, cable.getLength());
    }

    @Test
    @DisplayName("get null given a standard cable not having a sibling on commission cable")
    public void testCannotFindSiblingFromStandardCable() {
        CableRequest cableRequest = new CableRequest(
                FAMILY_CODE,
                STANDARD_CABLE_CODE,
                300
        );
        Cable cable = CableHelper.createCableFromCableRequest(
                cableRequest,
                FAMILY_CODE,
                STANDARD_CABLE_CODE,
                STANDARD_LENGTH,
                null);
        assertNull(cable);
    }

    @Test
    @DisplayName("get null given an on commission cable not having a sibling standard cable")
    public void testCannotFindSiblingFromOnCommissionCable() {
        CableRequest cableRequest = new CableRequest(
                FAMILY_CODE,
                ON_COMMISSION_CABLE_CODE,
                300
        );
        Cable cable = CableHelper.createCableFromCableRequest(
                cableRequest,
                FAMILY_CODE,
                null,
                null,
                ON_COMMISSION_CABLE_CODE);
        assertNull(cable);
    }

}
