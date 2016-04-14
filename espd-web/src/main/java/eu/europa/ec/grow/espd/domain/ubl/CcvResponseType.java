package eu.europa.ec.grow.espd.domain.ubl;

/**
 * Type of data expected in the response for a criterion requirement.
 * <p/>
 * Created by ratoico on 12/4/15 at 2:01 PM.
 */
public interface CcvResponseType<T> extends ResponseValueParser<T> {

    String getCode();

}
