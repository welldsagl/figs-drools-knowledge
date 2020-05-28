package ch.welld.schindler.fixture.droolsknowledge.builders.lip;

import java.util.Map;

public class LipBuilderHelper {

    enum LipType {
        HORIZONTAL,
        VERTICAL
    }

    static String getLipType(Map<String, Object> config) {
        String lipTypeString = ((String) config.get("lipType")).toLowerCase();
        if (lipTypeString.equalsIgnoreCase("vertical")) {
            return LipType.VERTICAL.toString();
        }
        if (lipTypeString.equalsIgnoreCase("horizontal")) {
            return LipType.HORIZONTAL.toString();
        }
        return null;
    }

    public static String getIndicatorFamily(Map<String, Object> config) {
        return (String) config.get("indicatorFamily");
    }


}
