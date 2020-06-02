package ch.welld.schindler.fixture.droolsknowledge.builders.lopl;

import java.util.Map;

public class LopLBuilderHelper {

    enum LopLType {
        LOP_L_100x110,
        LOP_L_100x160,
        LOP_L_180x110,
        LOP_L_180x160;

        @Override
        public String toString() {
            return super.toString().substring(6);
        }
    }

    static String getLopLType(Map<String, Object> config) {
        String lopLTypeString = ((String) config.get("lopLType")).toLowerCase();
        if (lopLTypeString.equalsIgnoreCase("100x110")) {
            return LopLType.LOP_L_100x110.toString();
        }
        if (lopLTypeString.equalsIgnoreCase("100x160")) {
            return LopLType.LOP_L_100x160.toString();
        }
        if (lopLTypeString.equalsIgnoreCase("180x110")) {
            return LopLType.LOP_L_180x110.toString();
        }
        if (lopLTypeString.equalsIgnoreCase("180x160")) {
            return LopLType.LOP_L_180x160.toString();
        }
        return null;
    }
}
