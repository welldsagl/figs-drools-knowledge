package ch.welld.schindler.fixture.droolsknowledge.components;

/**
 * A nullable boolean is an enum representing a nullable boolean choice. This could normally be achieved
 * through a `Boolean` class, instead of a `boolean` primitive type, but Drools' Business Central does not
 * see neither `boolean` nor `Boolean` as graphically nullable types.
 */
public enum NullableBoolean {
    YES,
    NO;

    public static NullableBoolean from(String from) {
        if(from == null) {
            return null;
        }
        if(from.equalsIgnoreCase("yes") || from.equalsIgnoreCase("true")) {
            return YES;
        }
        if(from.equalsIgnoreCase("no") || from.equalsIgnoreCase("false")) {
            return NO;
        }
        return null;
    }

    public static NullableBoolean from(Boolean from) {
        return from ? YES : NO;
    }
}
