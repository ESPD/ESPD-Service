package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import isa.names.specification.ubl.schema.xsd.ccv_commonbasiccomponents_1.IndicatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.*;

/**
 * Created by ratoico on 11/26/15 at 5:19 PM.
 */
@Component
class EspdRequestCriteriaTransformer implements Function<EspdDocument, List<CriterionType>> {

    private final CcvCriterionToCriterionTypeTransformer ccvCriterionTransformer;

    @Autowired
    EspdRequestCriteriaTransformer(CcvCriterionToCriterionTypeTransformer ccvCriterionTransformer) {
        this.ccvCriterionTransformer = ccvCriterionTransformer;
    }

    @Override
    public List<CriterionType> apply(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = addExclusionCriteria(espdDocument);
        criterionTypes.addAll(addSelectionCriteria(espdDocument));
        return Collections.unmodifiableList(criterionTypes);
    }

    private List<CriterionType> addExclusionCriteria(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(
                ExclusionCriterion.values().length + SelectionCriterion.values().length + 1);

        // don't use Guava Lists.transform because we want to add other values to the list
        // and we want to be able to mutate the criterion types to mark them as selected

        markSelectedExclusionCriteria(espdDocument, criterionTypes);
        return criterionTypes;
    }

    private void markSelectedExclusionCriteria(EspdDocument espdDocument, List<CriterionType> criterionTypes) {
        // we need to do it in a hard coded way right now, unfortunately
        // THE ORDER OF CRITERIA IS VERY IMPORTANT AND IT SHOULD BE COVERED BY THE TESTS
        markSelectedExclusionCriminalConvictions(espdDocument, criterionTypes);
        markSelectedExclusionPaymentOfTaxes(espdDocument, criterionTypes);
        markSelectedExclusionBreachOfObligations(espdDocument, criterionTypes);
        criterionTypes.add(ccvCriterionTransformer.apply(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS));
    }

    private void markSelectedExclusionCriminalConvictions(EspdDocument espdDocument, List<CriterionType> criteria) {
        criteria.add(buildCriterionType(GROUNDS_CRIMINAL_CONVICTIONS, espdDocument.getCriminalConvictions()));
        criteria.add(buildCriterionType(CORRUPTION, espdDocument.getCorruption()));
        criteria.add(buildCriterionType(FRAUD, espdDocument.getFraud()));
        criteria.add(buildCriterionType(TERRORIST_OFFENCES, espdDocument.getTerroristOffences()));
        criteria.add(buildCriterionType(MONEY_LAUNDERING, espdDocument.getMoneyLaundering()));
        criteria.add(buildCriterionType(CHILD_LABOUR, espdDocument.getChildLabour()));
    }

    private void markSelectedExclusionPaymentOfTaxes(EspdDocument espdDocument, List<CriterionType> criteria) {
        criteria.add(buildCriterionType(PAYMENT_OF_TAXES, espdDocument.getPaymentTaxes()));
        criteria.add(buildCriterionType(PAYMENT_OF_SOCIAL_SECURITY, espdDocument.getPaymentSocsec()));
    }

    private void markSelectedExclusionBreachOfObligations(EspdDocument espdDocument, List<CriterionType> criteria) {
        criteria.add(buildCriterionType(BREACHING_OF_OBLIGATIONS, espdDocument.getBreachingObligations()));
        criteria.add(buildCriterionType(BANKRUPTCY_INSOLVENCY, espdDocument.getBankruptSubject()));
        criteria.add(buildCriterionType(GUILTY_OF_PROFESSIONAL_MISCONDUCT, espdDocument.getGuiltyGrave()));
        criteria.add(buildCriterionType(AGREEMENTS_WITH_OTHER_EO, espdDocument.getAgreementsEo()));
        criteria.add(buildCriterionType(CONFLICT_OF_INTEREST, espdDocument.getConflictInterest()));
        criteria.add(buildCriterionType(INVOLVEMENT_PROCUREMENT_PROCEDURE, espdDocument.getInvolvementPreparation()));
        criteria.add(buildCriterionType(EARLY_TERMINATION, espdDocument.getEarlyTermination()));
        criteria.add(buildCriterionType(GUILTY_OF_MISINTERPRETATION, espdDocument.getGuiltyMisinterpretation()));
    }

    private CriterionType buildCriterionType(ExclusionCriterion exclusionCriterion, Criterion espdCriterion) {
        CriterionType ublCriterion = ccvCriterionTransformer.apply(exclusionCriterion);
        if (isCriterionSelectedInEspd(espdCriterion)) {
            markUblCriterionAsSelected(ublCriterion, true);
        }
        return ublCriterion;
    }

    private boolean isCriterionSelectedInEspd(Criterion criterion) {
        return criterion != null && criterion.getExists();
    }

    private void markUblCriterionAsSelected(CriterionType ublCriterion, boolean selected) {
        CriterionResponseType responseType = new CriterionResponseType();
        IndicatorType selectionIndicator = new IndicatorType();
        selectionIndicator.setValue(selected);
        responseType.setCriterionFulfillmentIndicator(selectionIndicator);
        ublCriterion.getCriterionResponse().add(responseType);
    }

    private List<CriterionType> addSelectionCriteria(EspdDocument espdDocument) {
        if (espdDocument.satisfiesAllCriteria()) {
            CriterionType satisfiesAllCriterion = ccvCriterionTransformer
                    .apply(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED);
            markUblCriterionAsSelected(satisfiesAllCriterion, true);
            return Collections.singletonList(satisfiesAllCriterion);
        }
        // TODO add selection criteria
        return Collections.emptyList();
    }
}
