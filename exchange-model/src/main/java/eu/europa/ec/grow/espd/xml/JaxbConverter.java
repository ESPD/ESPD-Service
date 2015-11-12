package eu.europa.ec.grow.espd.xml;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author vigi
 */
public final class JaxbConverter {

    private JaxbConverter() {

    }

    /**
     * Converts a String representing a {@link XMLGregorianCalendar} to a
     * {@link Date}.
     *
     * @param xgc String to convert from
     *
     * @return Computed date
     */
    public static Date parseDate(String xgc) {

        return DatatypeConverter.parseDate(xgc).getTime();
    }

    /**
     * Converts a {@link Date} to a String representing a
     * {@link XMLGregorianCalendar}.
     *
     * @param date Date to convert from
     *
     * @return String representation
     */
    public static String printDate(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return DatatypeConverter.printDate(calendar);
    }
}
