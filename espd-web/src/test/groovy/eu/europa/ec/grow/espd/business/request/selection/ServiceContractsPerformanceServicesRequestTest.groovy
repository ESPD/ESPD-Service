package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ServiceContractsPerformanceServicesRequestTest extends AbstractEspdXmlMarshalling {

    def "15. should contain the 'For service contracts: performance of services of the specified type' criterion"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "5e506c16-26ab-4e32-bb78-b27f87dc0565")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "For service contracts: performance of services of the specified type"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "During the reference period, the economic operator has provided the following main services of the type specified. Contracting authorities may require up to three years and allow experience dating from more than three years."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}