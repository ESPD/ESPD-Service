package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SupplyContractsSamplesWithCARequestTest extends AbstractRequestSelectionFixture {

    def "29. should contain the 'For supply contracts: samples, descriptions or photographs with certification of authenticity' criterion"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "7662b7a9-bcb8-4763-a0a7-7505d8e8470d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For supply contracts: samples, descriptions or photographs with certification of authenticity"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator will supply the required samples, descriptions or photographs of the products to be supplied and will provide certifications of authenticity where applicable."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}