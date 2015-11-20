package eu.europa.ec.grow.espd.business

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion

/**
 * Created by vigi on 11/19/15:3:32 PM.
 */
class EspdRequestSelectionCriteriaTest extends AbstractEspdXmlMarshalling {

    def "should contain a single selection Criterion element if the economic operator claims that it satisfies all the criteria"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SelectionCriterion(exists: true))
        def idx = 18

        when:
        marshaller.generateEspdRequest(espd, out)
        def req = new XmlSlurper().parseText(out.toString())

        then: "check the CriterionID"
        req.Criterion[idx].CriterionID.text() == "7e7db838-eeac-46d9-ab39-42927486f22d"
        req.Criterion[idx].CriterionID.@schemeAgencyID.text() == "EU-COM-GROW"
        req.Criterion[idx].CriterionID.@schemeVersionID.text() == "1.0"
        req.Criterion[idx].CriterionID.@schemeID.text() == "CriteriaID"

        then: "check the CriterionTypeCode"
        req.Criterion[idx].CriterionTypeCode.text() == "SELECTION.ALL_CRITERIA_SATISFIED"
        req.Criterion[idx].CriterionTypeCode.@listAgencyID.text() == "EU-COM-GROW"
        req.Criterion[idx].CriterionTypeCode.@listID.text() == "CriteriaTypeCode"
        req.Criterion[idx].CriterionTypeCode.@listVersionID.text() == "1.0"

        then: "check name and description"
        req.Criterion[idx].CriterionName.text() == "All Selection Criteria Will Be Satisfied"
        req.Criterion[idx].CriterionDescription.text() == "The economic operator satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice."

//        then: "check the CriterionRequirement"
//        req.Criterion[idx].CriterionRequirement.CriterionRequirementDescription.text() == "If the relevant documentation is available electronically, please indicate where to obtain the evidences: web address, issuing authority or body, precise reference of the documentation."
    }

}