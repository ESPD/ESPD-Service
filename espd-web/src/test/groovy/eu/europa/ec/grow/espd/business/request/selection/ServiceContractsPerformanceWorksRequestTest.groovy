package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ServiceContractsPerformanceWorksRequestTest extends AbstractEspdXmlMarshalling {

    def "13. should contain the 'For works contracts: performance of works of the specified type' criterion"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "cdd3bb3e-34a5-43d5-b668-2aab86a73822")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "For works contracts: performance of works of the specified type"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "During the reference period, the economic operator has performed the following works of the specified type. Contracting authorities may require up to five years and allow experience dating from more than five years."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}