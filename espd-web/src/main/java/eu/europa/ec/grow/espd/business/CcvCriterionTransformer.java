package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import org.springframework.stereotype.Component;

/**
 * Created by vigi on 11/16/15:3:38 PM.
 */
@Component
class CcvCriterionTransformer implements Function<EspdDocument, CriterionType> {

    @Override
    public CriterionType apply(final EspdDocument input) {
        return null;
    }
}
