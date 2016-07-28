/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml.base
import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
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
        assert request.Criterion[idx].TypeCode.@listVersionID.text() == "1.0.2"
    }

    protected static void checkLegislationReference(def request, int idx, String expectedArticle) {
        def ref = request.Criterion[idx].LegislationReference

        assert ref.Title.text() == "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC"
        assert ref.JurisdictionLevelCode.text() == "EU_DIRECTIVE"
        assert ref.JurisdictionLevelCode.@listAgencyID.text() == "EU-COM-GROW"
        assert ref.JurisdictionLevelCode.@listID.text() == "CriterionJurisdictionLevel"
        assert ref.JurisdictionLevelCode.@listVersionID.text() == "1.0.2"
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
    protected static int getRequestCriterionIndex(ExclusionCriterion criterion) {
        // there are no mandatory exclusion criteria anymore (only in the UI)
        return 0
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
        // there are no mandatory exclusion criteria anymore (only in the UI)
        return 0
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
    protected static int getEoCriterionIndex(OtherCriterion criterion) {
        getMandatoryExclusionCriteriaSize() + SelectionCriterion.values().length + criterion.ordinal()
    }

    protected static int getMandatoryExclusionCriteriaSize() {
        // there are no mandatory exclusion criteria anymore (only in the UI)
        return 0;
    }

}
