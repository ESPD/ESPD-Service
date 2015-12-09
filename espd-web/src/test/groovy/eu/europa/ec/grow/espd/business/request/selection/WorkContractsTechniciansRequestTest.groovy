package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class WorkContractsTechniciansRequestTest extends AbstractEspdXmlMarshalling {

    def "17. should contain the 'For works contracts: technicians or technical bodies to carry out the work' criterion"() {
        given:
        def espd = new EspdDocument(workContractsTechnicians: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "c599c130-b29f-461e-a187-4e16c7d40db7")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "For works contracts: technicians or technical bodies to carry out the work"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "In the case of public works contracts, the economic operator will be able to call on the following technicians or technical bodies to carry out the work."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}