package eu.europa.ec.grow.espd.xml.request;

import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.xml.common.UblCriteriaTemplate;
import eu.europa.ec.grow.espd.xml.common.UblCriterionTypeTemplate;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Create the UBL {@link CriterionType} list of criteria for a ESPD Request, including exclusion, selection and award
 * criteria.
 * </p>
 * <p><b>The presence of a criterion in a {@link ESPDRequestType} is handled by the 'exists' flag on the {@link EspdDocument} model {@link Criterion}.</b></p>
 * <p/>
 * The criteria need to be present in the {@link ESPDRequestType} in the following way:
 * <ol>
 * <li>All exclusion criteria except 'Purely national grounds' must be present, unless it was selected as well.</li>
 * <li>CA selects "All section criteria" -> The request contains only "All selection criteria" and not the individual ones.</li>
 * <li>CA select individual selection criteria -> The request contains only the selected ones (and even not the "All selection criteria").</li>
 * <li>CA selects no selection criteria at all -> The request contains all the selection criteria (including "All selection criteria").</li>
 * <li>The request contains only one award criterion: "Meets the objective".</li>
 * </o>
 * <p></p>
 * Created by ratoico on 11/26/15 at 5:19 PM.
 */
class UblRequestCriteriaTransformer extends UblCriteriaTemplate {

    @Override
    protected UblCriterionTypeTemplate buildCriterionTypeTransformerTemplate() {
        return new UblRequestCriterionTransformer();
    }

    @Override
    protected List<CriterionType> buildAwardCriteria(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(1);
        // meets objective (reduction of numbers) is common to request and response
        addAlwaysUblCriterion(AwardCriterion.MEETS_OBJECTIVE, espdDocument, criterionTypes);
        return Collections.unmodifiableList(criterionTypes);
    }

}
