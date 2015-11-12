package eu.europa.ec.grow.espd.xml;

import org.joda.time.LocalDate;

/**
 * @author vigi
 */
public final class LocalDateAdapter {

    private LocalDateAdapter() {

    }

    public static LocalDate unmarshal(String v) {
        return new LocalDate(v);
    }

    public static String marshal(LocalDate v) {
        return v.toString();
    }
}
