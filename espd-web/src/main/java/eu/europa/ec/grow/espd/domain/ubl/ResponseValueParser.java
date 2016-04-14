package eu.europa.ec.grow.espd.domain.ubl;

import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.ResponseType;

/**
 * Created by ratoico on 3/10/16 at 3:21 PM.
 */
public interface ResponseValueParser<T> {

    T parseValue(ResponseType responseType);

}
