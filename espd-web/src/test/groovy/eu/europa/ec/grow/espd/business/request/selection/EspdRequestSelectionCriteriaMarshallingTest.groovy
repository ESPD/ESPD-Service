package eu.europa.ec.grow.espd.business.request.selection

import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
*  Created by vigi on 11/19/15:3:32 PM.
*/
class EspdRequestSelectionCriteriaMarshallingTest extends AbstractEspdXmlMarshalling {

    def "should contain a single selection Criterion element if the economic operator claims that it satisfies all the criteria"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SelectionCriterion(exists: true))
        // selection criteria come after exclusion criteria
        def idx = 0

        when:
        marshaller.generateEspdRequest(espd, xmlOutput)
        def request = new XmlSlurper().parseText(xmlOutput.toString())

        then: "check the CriterionID"
        request.Criterion[idx].CriterionID.text() == "7e7db838-eeac-46d9-ab39-42927486f22d"
        request.Criterion[idx].CriterionID.@schemeAgencyID.text() == "EU-COM-GROW"
        request.Criterion[idx].CriterionID.@schemeVersionID.text() == "1.0"
        request.Criterion[idx].CriterionID.@schemeID.text() == "CriteriaID"

        then: "check the CriterionTypeCode"
        request.Criterion[idx].CriterionTypeCode.text() == "SELECTION.ALL_CRITERIA_SATISFIED"
        request.Criterion[idx].CriterionTypeCode.@listAgencyID.text() == "EU-COM-GROW"
        request.Criterion[idx].CriterionTypeCode.@listID.text() == "CriteriaTypeCode"
        request.Criterion[idx].CriterionTypeCode.@listVersionID.text() == "1.0"

        then: "check name and description"
        request.Criterion[idx].CriterionName.text() == "All selection criteria will be satisfied"
        request.Criterion[idx].CriterionDescription.text() == "The economic operator satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice."
    }

}