package eu.europa.ec.grow.espd.xml;

import org.joda.time.LocalTime;

/**
 * Created by vigi on 11/12/15:3:08 PM.
 */
public final class LocalTimeAdapter {

    private LocalTimeAdapter() {

    }

    public static LocalTime unmarshal(String v) {
        return new LocalTime(v);
    }

    public static String marshal(LocalTime v) {
        return v.toString("HH:mm:ss");
    }
}
