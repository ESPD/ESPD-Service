package eu.europa.ec.grow.espd.xml.base

import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/15/15 at 3:54 PM.
 */
class AbstractCriteriaFixture extends AbstractEspdXmlMarshalling {

    protected static void checkCriterionId(def request, int idx, String expectedId) {
        assert request.Criterion[idx].ID.text() == expectedId
        assert request.Criterion[idx].ID.@schemeAgencyID.text() == "EU-COM-GROW"
        assert request.Criterion[idx].ID.@schemeVersionID.text() == "1.0"
        assert request.Criterion[idx].ID.@schemeID.text() == "CriteriaID"
    }

    protected static void checkCriterionTypeCode(def request, int idx, String expectedTypeCode) {
        assert request.Criterion[idx].TypeCode.text() == expectedTypeCode
        assert request.Criterion[idx].TypeCode.@listAgencyID.text() == "EU-COM-GROW"
        assert request.Criterion[idx].TypeCode.@listID.text() == "CriteriaTypeCode"
        assert request.Criterion[idx].TypeCode.@listVersionID.text() == "1.0"
    }

    protected static void checkLegislationReference(def request, int idx, String expectedArticle) {
        def ref = request.Criterion[idx].LegislationReference

        assert ref.Title.text() == "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC"
        assert ref.JurisdictionLevelCode.text() == "EU_DIRECTIVE"
        assert ref.JurisdictionLevelCode.@listAgencyID.text() == "EU-COM-GROW"
        assert ref.JurisdictionLevelCode.@listID.text() == "CriterionJurisdictionLevelCode"
        assert ref.JurisdictionLevelCode.@listVersionID.text() == "1.0"
        assert ref.Article.text() == expectedArticle
        assert ref.URI.text() == "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024"
    }

    protected static void checkRequirement(def requirementType, String expectedId, String expectedDescription,
                                           String expectedResponseType) {
        assert requirementType.ID.text() == expectedId
        assert requirementType.Description.text() == expectedDescription
        assert requirementType.@responseDataType.text() == expectedResponseType
        assert requirementType.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert requirementType.ID.@schemeVersionID.text() == "1.0"
    }

    protected static void checkEvidence(def evidenceElement, String url) {
        assert evidenceElement.EvidenceDocumentReference.ID.text().length() == 36
        assert evidenceElement.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == url
    }

    /**
     *  The criteria need to be present in the ESPDRequest in the following way:
     * <ol>
     * <li>All exclusion criteria except 'Purely national grounds' must be present, unless it was selected as well.</li>
     * <li>CA selects "All section criteria" -> The request contains only "All selection criteria" and not the individual ones.</li>
     * <li>CA select individual selection criteria -> The request contains only the selected ones (and even not the "All selection criteria").</li>
     * <li>CA selects no selection criteria at all -> The request contains all the selection criteria (including "All selection criteria").</li>
     * <li>The request contains only one award criterion: "Meets the objective".</li>
     * </o>
     * <p></p>
     * @return
     */
    protected static int getRequestTotalNumberOfCriteria(EspdDocument espdDocument) {
        if (!espdDocument.atLeastOneSelectionCriterionWasSelected()) {
            // Option 3:
            // CA selects no selection criteria -> EO sees all selection criteria (including "All selection criteria")
            return ExclusionCriterion.values().length - 1 + SelectionCriterion.values().length + 1
        } else if (espdDocument.selectionSatisfiesAll != null && espdDocument.setupEconomicOperator.exists) {
            // Option 1:
            // CA selects "All section criteria" -> EO sees only "All selection criteria" and not the individual ones.
            ExclusionCriterion.values().length - 1 + 1 + 1
        } else {
            // Option 2:
            // CA select individual selection criteria -> EO sees only the selected ones (and even not the "All selection criteria")
            ExclusionCriterion.values().length - 1 + 1 + 1
        }
    }

    /**
     *  The criteria need to be present in the ESPDRequest in the following way:
     * <ol>
     * <li>All exclusion criteria except 'Purely national grounds' must be present, unless it was selected as well.</li>
     * <li>CA selects "All section criteria" -> The request contains only "All selection criteria" and not the individual ones.</li>
     * <li>CA select individual selection criteria -> The request contains only the selected ones (and even not the "All selection criteria").</li>
     * <li>CA selects no selection criteria at all -> The request contains all the selection criteria (including "All selection criteria").</li>
     * <li>The request contains only one award criterion: "Meets the objective".</li>
     * </o>
     * <p></p>
     * @return
     */
    protected static int getRequestCriterionIndex(ExclusionCriterion criterion) {
        return criterion.ordinal()
    }

    /**
     *  The criteria need to be present in the ESPDRequest in the following way:
     * <ol>
     * <li>All exclusion criteria except 'Purely national grounds' must be present, unless it was selected as well.</li>
     * <li>CA selects "All section criteria" -> The request contains only "All selection criteria" and not the individual ones.</li>
     * <li>CA select individual selection criteria -> The request contains only the selected ones (and even not the "All selection criteria").</li>
     * <li>CA selects no selection criteria at all -> The request contains all the selection criteria (including "All selection criteria").</li>
     * <li>The request contains only one award criterion: "Meets the objective".</li>
     * </o>
     * <p></p>
     * @return
     */
    protected static int getRequestCriterionIndex(SelectionCriterion criterion) {
        // only for the case when one selection is selected
        return getMandatoryExclusionCriteriaSize()
    }

    /**
     * Only criteria with exists true are present.
     * @param criterion
     * @return
     */
    protected static int getResponseCriterionIndex(ExclusionCriterion criterion) {
        return criterion.ordinal()
    }

    /**
     * Only criteria with exists true are present.
     * @param criterion
     * @return
     */
    protected static int getResponseCriterionIndex(SelectionCriterion criterion) {
        return getMandatoryExclusionCriteriaSize()
    }

    /**
     * All the award criteria are present so we load them by the enumeration ordinal.
     * @param criterion
     * @return
     */
    protected static int getResponseCriterionIndex(AwardCriterion criterion) {
       getTotalMandatoryCriteriaNoSelectionCriteriaPresent() + criterion.ordinal()
    }

    protected static int getTotalMandatoryCriteriaNoSelectionCriteriaPresent() {
        return getMandatoryExclusionCriteriaSize() + SelectionCriterion.values().length
    }

    /**
     * All exclusion criteria minus 'Purely national grounds' are mandatory
     * @return
     */
    protected static int getMandatoryExclusionCriteriaSize() {
        return ExclusionCriterion.values().length - 1;
    }

}
