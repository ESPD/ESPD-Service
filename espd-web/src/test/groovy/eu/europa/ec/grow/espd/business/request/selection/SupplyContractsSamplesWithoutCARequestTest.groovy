package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SupplyContractsSamplesWithoutCARequestTest extends AbstractEspdXmlMarshalling {

    def "28. should contain the 'For supply contracts: samples, descriptions or photographs without certification of authenticity' criterion"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithoutCa: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "bdf0601d-2480-4250-b870-658d0ee95be6")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "For supply contracts: samples, descriptions or photographs without certification of authenticity"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "The economic operator will supply the required samples, descriptions or photographs of the products to be supplied, which do not need to be accompanied by certifications of authenticity."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}