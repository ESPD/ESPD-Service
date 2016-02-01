package eu.europa.ec.grow.espd.xml.base

import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion

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

    /**
     * At the moment, the ESPD request contains only the criteria which were selected plus an award criterion.
     * @return
     */
    protected static int getRequestNumberOfCriteria() {
//        return ExclusionCriterion.values().size() + SelectionCriterion.values().size() + 1
        return 1
    }

    /**
     * At the moment, the ESPD Response contains only the criteria which were selected by the CA plus all the
     * award criteria.
     * @return
     */
    protected static int getResponseNumberOfCriteria() {
        return 1 + AwardCriterion.values().size()
    }

    protected static void checkEvidence(def evidenceElement, String url) {
        assert evidenceElement.EvidenceDocumentReference.ID.text().length() == 36
        assert evidenceElement.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == url
    }

    /**
     * Only criteria with exists true are present.
     * @param criterion
     * @return
     */
    protected static int getRequestCriterionIndex(ExclusionCriterion criterion) {
        return 0
    }

    /**
     * Only criteria with exists true are present.
     * @param criterion
     * @return
     */
    protected static int getRequestCriterionIndex(SelectionCriterion criterion) {
        return 0
    }

    /**
     * There is only one award criterion on the request.
     * @return
     */
    protected static int getRequestCriterionIndex(AwardCriterion criterion) {
        return 0
    }

    /**
     * Only criteria with exists true are present.
     * @param criterion
     * @return
     */
    protected static int getResponseCriterionIndex(ExclusionCriterion criterion) {
        return 0
    }

    /**
     * Only criteria with exists true are present.
     * @param criterion
     * @return
     */
    protected static int getResponseCriterionIndex(SelectionCriterion criterion) {
        return 0
    }

    /**
     * All the award criteria are present so we load them by the enumeration ordinal.
     * @param criterion
     * @return
     */
    protected static int getResponseCriterionIndex(AwardCriterion criterion) {
        criterion.ordinal()
    }
}
