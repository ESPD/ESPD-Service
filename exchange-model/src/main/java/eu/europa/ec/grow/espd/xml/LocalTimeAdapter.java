package eu.europa.ec.grow.espd.xml;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by vigi on 11/12/15:3:08 PM.
 */
public final class LocalTimeAdapter {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormat.forPattern("HH:mm:ss");

    private LocalTimeAdapter() {

    }

    public static LocalTime unmarshal(String v) {
        if (v == null || v.trim().isEmpty()) {
            return null;
        }
        return LocalTime.parse(v, TIME_FORMAT);
    }

    public static String marshal(LocalTime v) {
        if (v == null) {
            return null;
        }
        return v.toString(TIME_FORMAT);
    }
}
