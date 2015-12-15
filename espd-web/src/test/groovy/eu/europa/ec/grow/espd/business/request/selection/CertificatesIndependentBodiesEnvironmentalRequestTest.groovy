package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class CertificatesIndependentBodiesEnvironmentalRequestTest extends AbstractRequestSelectionFixture {

    def "32. should contain the 'Certificates by independent bodies about environmental management systems or standards' criterion"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutEnvironmental: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "8ed65e48-fd0d-444f-97bd-4f58da632999")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Certificates by independent bodies about environmental management systems or standards"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Will the economic operator be able to produce certificates drawn up by independent bodies attesting that the economic operator complies with the required environmental management systems or standards?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "62(2)")
    }

}