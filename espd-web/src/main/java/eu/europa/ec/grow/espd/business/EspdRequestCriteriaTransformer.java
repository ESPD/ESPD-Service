package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.*;

/**
 * Create the UBL {@link CriterionType} criteria for a ESPD Request, including both exclusion and selection
 * criteria.
 * <p/>
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
        List<CriterionType> criterionTypes = new ArrayList<>(
                ExclusionCriterion.values().length + SelectionCriterion.values().length + 1);
        criterionTypes.addAll(addExclusionCriteria(espdDocument));
        criterionTypes.addAll(addSelectionCriteria(espdDocument));
        return Collections.unmodifiableList(criterionTypes);
    }

    private List<CriterionType> addExclusionCriteria(EspdDocument espdDocument) {
        // we need to do it in a hard coded way right now, unfortunately
        // THE ORDER OF CRITERIA IS VERY IMPORTANT AND IT SHOULD BE COVERED BY THE TESTS
        List<CriterionType> criterionTypes = new ArrayList<>(ExclusionCriterion.values().length + 1);
        markSelectedExclusionCriminalConvictions(espdDocument, criterionTypes);
        markSelectedExclusionPaymentOfTaxes(espdDocument, criterionTypes);
        markSelectedExclusionBreachOfObligations(espdDocument, criterionTypes);
        markSelectedExclusionNationalGrounds(espdDocument, criterionTypes);
        return criterionTypes;
    }

    private void markSelectedExclusionCriminalConvictions(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterionIfSelected(GROUNDS_CRIMINAL_CONVICTIONS, espdDocument.getCriminalConvictions(), criteria);
        addUblCriterionIfSelected(CORRUPTION, espdDocument.getCorruption(), criteria);
        addUblCriterionIfSelected(FRAUD, espdDocument.getFraud(), criteria);
        addUblCriterionIfSelected(TERRORIST_OFFENCES, espdDocument.getTerroristOffences(), criteria);
        addUblCriterionIfSelected(MONEY_LAUNDERING, espdDocument.getMoneyLaundering(), criteria);
        addUblCriterionIfSelected(CHILD_LABOUR, espdDocument.getChildLabour(), criteria);
    }

    private void markSelectedExclusionPaymentOfTaxes(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterionIfSelected(PAYMENT_OF_TAXES, espdDocument.getPaymentTaxes(), criteria);
        addUblCriterionIfSelected(PAYMENT_OF_SOCIAL_SECURITY, espdDocument.getPaymentSocsec(), criteria);
    }

    private void markSelectedExclusionBreachOfObligations(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterionIfSelected(BREACHING_OF_OBLIGATIONS, espdDocument.getBreachingObligations(), criteria);
        addUblCriterionIfSelected(BANKRUPTCY_INSOLVENCY, espdDocument.getBankruptSubject(), criteria);
        addUblCriterionIfSelected(GUILTY_OF_PROFESSIONAL_MISCONDUCT, espdDocument.getGuiltyGrave(), criteria);
        addUblCriterionIfSelected(AGREEMENTS_WITH_OTHER_EO, espdDocument.getAgreementsEo(), criteria);
        addUblCriterionIfSelected(CONFLICT_OF_INTEREST, espdDocument.getConflictInterest(), criteria);
        addUblCriterionIfSelected(INVOLVEMENT_PROCUREMENT_PROCEDURE, espdDocument.getInvolvementPreparation(),
                criteria);
        addUblCriterionIfSelected(EARLY_TERMINATION, espdDocument.getEarlyTermination(), criteria);
        addUblCriterionIfSelected(GUILTY_OF_MISINTERPRETATION, espdDocument.getGuiltyMisinterpretation(), criteria);
    }

    private void markSelectedExclusionNationalGrounds(EspdDocument espdDocument, List<CriterionType> criteria) {
        addUblCriterionIfSelected(NATIONAL_EXCLUSION_GROUNDS, espdDocument.getPurelyNationalGrounds(), criteria);
    }

    private List<CriterionType> addSelectionCriteria(EspdDocument espdDocument) {
        if (espdDocument.satisfiesAllCriteria()) {
            List<CriterionType> all = new ArrayList<>(1);
            addUblCriterionIfSelected(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED,
                    espdDocument.getSelectionSatisfiesAll(), all);
            return Collections.unmodifiableList(all);
        }
        // TODO add selection criteria
        return Collections.emptyList();
    }

    private void addUblCriterionIfSelected(CcvCriterion ccvCriterion, Criterion espdCriterion,
            List<CriterionType> ublCriteria) {
        if (isCriterionSelectedInEspd(espdCriterion)) {
            ublCriteria.add(ccvCriterionTransformer.apply(ccvCriterion));
        }
    }

    private boolean isCriterionSelectedInEspd(Criterion espdCriterion) {
        return espdCriterion != null && espdCriterion.getExists();
    }

}
