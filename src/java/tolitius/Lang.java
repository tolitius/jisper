package tolitius;

import java.util.Date;

public final class Lang {

    private static final String EMPTY_STRING = "";

    public static Object or(Object val, Object dval) {
        if ((val != null) && (! EMPTY_STRING.equals(val))) {
            return val;
        } else {
            return dval;
        }
    }

    public static boolean and(Object... vals) {

        for (Object val : vals) {
            if ((val == null) || (EMPTY_STRING.equals(val))) {
                return false;
            }
        }

        return true;
    }

    public static Object or(Object... vals) {

        for (Object val : vals) {
            if ((val != null) && (! EMPTY_STRING.equals(val))) {
                return val;
            }
        }

        return null;
    }

    public static boolean any(Object... vals) {

        for (Object val : vals) {
            if ((val != null) && (! EMPTY_STRING.equals(val))) {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {

        System.out.println( Lang.or("", null, "val", "foo") );
        System.out.println( Lang.or("", null, "", "foo") );
        System.out.println( Lang.or("", null, 42, null) );
        System.out.println( Lang.or("", new Date(), null, 42, null) );
        System.out.println( Lang.or("", null, "", null) );

        System.out.println( Lang.any("", null, "val", "foo"));
        System.out.println( Lang.any("", null, "", new Date()));
        System.out.println( Lang.any("", null, "", null) );

        System.out.println( Lang.and("", null, "", "foo") );
        System.out.println( Lang.and(42, new Date(), "foo") );
        System.out.println( Lang.and("", null, "", null) );

    }
}

