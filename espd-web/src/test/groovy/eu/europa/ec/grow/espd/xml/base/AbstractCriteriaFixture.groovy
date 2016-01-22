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

    protected static int getRequestNumberOfCriteria() {
        return ExclusionCriterion.values().size() + SelectionCriterion.values().size() + 1
    }

    protected static int getResponseNumberOfCriteria() {
        // response has economic operator criteria plus meets objectives (reduction of candidates) which is common
        return getRequestNumberOfCriteria() + AwardCriterion.values().size() - 1
    }

    protected static void checkEvidence(def evidenceElement, String url) {
        assert evidenceElement.EvidenceDocumentReference.ID.text().length() == 36
        assert evidenceElement.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == url
    }

    protected static int getCriterionIndex(ExclusionCriterion criterion) {
        return criterion.ordinal()
    }

    protected static int getCriterionIndex(SelectionCriterion criterion) {
        return ExclusionCriterion.values().size() + criterion.ordinal()
    }

    protected static int getCriterionIndex(AwardCriterion criterion) {
        return ExclusionCriterion.values().size() + SelectionCriterion.values().size() + criterion.ordinal()
    }
}
