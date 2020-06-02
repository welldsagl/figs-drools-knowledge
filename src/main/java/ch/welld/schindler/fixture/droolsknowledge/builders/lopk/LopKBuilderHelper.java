package ch.welld.schindler.fixture.droolsknowledge.builders.lopk;

import java.util.Map;

public class LopKBuilderHelper {

    enum LopKType {
        LOP_K_100x110,
        LOP_K_100x160,
        LOP_K_180x110,
        LOP_K_180x160;

        @Override
        public String toString() {
            return super.toString().substring(6);
        }
    }

    static String getLopKType(Map<String, Object> config) {
        String lopKTypeString = ((String) config.get("lopKType")).toLowerCase();
        if (lopKTypeString.equalsIgnoreCase("100x110")) {
            return LopKType.LOP_K_100x110.toString();
        }
        if (lopKTypeString.equalsIgnoreCase("100x160")) {
            return LopKType.LOP_K_100x160.toString();
        }
        if (lopKTypeString.equalsIgnoreCase("180x110")) {
            return LopKType.LOP_K_180x110.toString();
        }
        if (lopKTypeString.equalsIgnoreCase("180x160")) {
            return LopKType.LOP_K_180x160.toString();
        }
        return null;
    }
}
