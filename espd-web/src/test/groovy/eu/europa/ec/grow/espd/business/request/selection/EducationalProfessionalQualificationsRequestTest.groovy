package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class EducationalProfessionalQualificationsRequestTest extends AbstractRequestSelectionFixture {

    def "22. should contain the 'Educational and professional qualifications' criterion"() {
        given:
        def espd = new EspdDocument(educationalProfessionalQualifications: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "07301031-2270-41af-8e7e-66fe0c777107")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Educational and professional qualifications"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The following educational and professional qualifications are held by the service provider or the contractor itself, and/or (depending on the requirements set out in the relevant notice or the procurement documents by its managerial staff."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}