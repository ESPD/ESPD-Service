package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Create the UBL {@link CriterionType} criteria for a ESPD Response, including both exclusion and selection
 * criteria.
 * <p/>
 * Created by ratoico on 11/27/15 at 11:40 AM.
 */
@Component
class EspdResponseCriteriaTransformer implements Function<EspdDocument, List<CriterionType>> {

    private final CcvCriterionToCriterionTypeTransformer ccvCriterionTransformer;

    @Autowired
    EspdResponseCriteriaTransformer(CcvCriterionToCriterionTypeTransformer ccvCriterionTransformer) {
        this.ccvCriterionTransformer = ccvCriterionTransformer;
    }

    @Override
    public List<CriterionType> apply(EspdDocument input) {
        List<CriterionType> criterionTypes = new ArrayList<>(
                ExclusionCriterion.values().length + SelectionCriterion.values().length + 1);
        criterionTypes.addAll(addExclusionCriteria(input));
        return Collections.unmodifiableList(criterionTypes);
    }

    private List<CriterionType> addExclusionCriteria(EspdDocument espdDocument) {
        // we need to do it in a hard coded way right now, unfortunately
        // THE ORDER OF CRITERIA IS VERY IMPORTANT AND IT SHOULD BE COVERED BY THE TESTS
        List<CriterionType> criterionTypes = new ArrayList<>(ExclusionCriterion.values().length + 1);
        markSelectedExclusionCriminalConvictions(espdDocument, criterionTypes);
//        markSelectedExclusionPaymentOfTaxes(espdDocument, criterionTypes);
//        markSelectedExclusionBreachOfObligations(espdDocument, criterionTypes);
//        criterionTypes.add(ccvCriterionTransformer.apply(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS));
        return criterionTypes;
    }

    private void markSelectedExclusionCriminalConvictions(EspdDocument espdDocument, List<CriterionType> criteria) {
//        criteria.add(buildCriminalConvictionsCriterion(espdDocument));
//        criteria.add(buildUblCriterion(CORRUPTION, espdDocument.getCorruption()));
//        criteria.add(buildUblCriterion(FRAUD, espdDocument.getFraud()));
//        criteria.add(buildUblCriterion(TERRORIST_OFFENCES, espdDocument.getTerroristOffences()));
//        criteria.add(buildUblCriterion(MONEY_LAUNDERING, espdDocument.getMoneyLaundering()));
//        criteria.add(buildUblCriterion(CHILD_LABOUR, espdDocument.getChildLabour()));
    }

//    private CriterionType buildCriminalConvictionsCriterion(EspdDocument espdDocument) {
//        CriterionType ublCriterion = buildUblCriterion(PARTICIPATION_CRIMINAL_ORGANISATION,
//                espdDocument.getCriminalConvictions());
//        return ublCriterion;
//    }
}
