package eu.europa.ec.grow.espd.business.response;

import eu.europa.ec.grow.espd.business.common.UblCriteriaTemplate;
import eu.europa.ec.grow.espd.business.common.UblCriterionTypeTemplate;
import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * <p>
 * Create the UBL {@link CriterionType} list of criteria for a ESPD Request, including exclusion, selection and award
 * criteria.
 * </p>
 * <p><b>The presence of a criterion in a {@link ESPDResponseType} is handled by the 'exists' flag on the {@link EspdDocument} model {@link Criterion}.</b></p>
 * <p/>
 * The criteria need to be present in the {@link ESPDResponseType} in the following way:
 * <ol>
 * <li>All exclusion criteria except 'Purely national grounds' must be present, unless it was selected as well.</li>
 * <li>CA selects "All section criteria" -> The request contains only "All selection criteria" and not the individual ones.</li>
 * <li>CA select individual selection criteria -> The request contains only the selected ones (and even not the "All selection criteria").</li>
 * <li>CA selects no selection criteria at all -> The request contains all the selection criteria (including "All selection criteria").</li>
 * <li>The award criteria of the response are always present.</li>
 * </o>
 * <p></p>
 * Create the UBL {@link CriterionType} criteria for a ESPD Response, including both exclusion and selection
 * criteria.
 * <p/>
 * Created by ratoico on 11/27/15 at 11:40 AM.
 */
class UblResponseCriteriaTransformer extends UblCriteriaTemplate {

    @Override
    protected UblCriterionTypeTemplate buildCriterionTypeTransformerTemplate() {
        return new UblResponseCriterionTransformer();
    }

    @Override
    protected List<CriterionType> buildAwardCriteria(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(AwardCriterion.values().length + 1);
        for (AwardCriterion criterion : AwardCriterion.values()) {
            addAlwaysUblCriterion(criterion, espdDocument, criterionTypes);
        }
        return Collections.unmodifiableList(criterionTypes);
    }

}
