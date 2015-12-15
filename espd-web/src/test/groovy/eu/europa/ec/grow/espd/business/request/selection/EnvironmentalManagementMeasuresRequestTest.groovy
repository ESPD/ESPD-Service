package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class EnvironmentalManagementMeasuresRequestTest extends AbstractRequestSelectionFixture {

    def "23. should contain the 'Environmental management measures' criterion"() {
        given:
        def espd = new EspdDocument(environmentalManagementFeatures: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "9460457e-b43d-48a9-acd1-615de6ddd33e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Environmental management measures"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator will be able to apply the following environmental management measures when performing the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}