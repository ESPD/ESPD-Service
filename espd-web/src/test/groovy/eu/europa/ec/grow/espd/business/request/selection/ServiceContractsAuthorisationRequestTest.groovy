package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ServiceContractsAuthorisationRequestTest extends AbstractEspdXmlMarshalling {

    def "04. should contain the 'For service contracts: authorisation of particular organisation needed' criterion"() {
        given:
        def espd = new EspdDocument(serviceContractsAuthorisation: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "9eeb6d5c-0eb8-48e8-a4c5-5087a7c095a4")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.SUITABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For service contracts: authorisation of particular organisation needed"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Is a particular membership of a particular organisation needed in order to be able to perform the service in question in the country of establishment of the economic operator?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(2)")
    }

}