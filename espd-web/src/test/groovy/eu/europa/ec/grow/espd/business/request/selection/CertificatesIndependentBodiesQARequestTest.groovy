package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class CertificatesIndependentBodiesQARequestTest extends AbstractRequestSelectionFixture {

    def "31. should contain the 'Certificates by independent bodies about quality assurance standards' criterion"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutQa: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "d726bac9-e153-4e75-bfca-c5385587766d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Certificates by independent bodies about quality assurance standards"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Will the economic operator be able to produce certificates drawn up by independent bodies attesting that the economic operator complies with the required quality assurance standards, including accessibility for disabled persons?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "62(2)")
    }

}