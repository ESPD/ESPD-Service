package eu.europa.ec.grow.espd.xml;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author vigi
 */
public final class LocalDateAdapter {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("YYYY-MM-dd");

    private LocalDateAdapter() {

    }

    public static LocalDate unmarshal(String v) {
        if (v == null) {
            return null;
        }
        return LocalDate.parse(v, DATE_FORMAT);
    }

    public static String marshal(LocalDate v) {
        if (v == null) {
            return null;
        }
        return v.toString(DATE_FORMAT);
    }
}
