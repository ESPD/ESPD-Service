package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class EnrolmentProfessionalRegisterRequestTest extends AbstractRequestSelectionFixture {

    def "02. should contain the 'Enrolment in a relevant professional register' criterion"() {
        given:
        def espd = new EspdDocument(enrolmentProfessionalRegister: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.SUITABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Enrolment in a relevant professional register"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator is enrolled in relevant professional registers kept in the Member State of its establishment as described in Annex XI of Directive 2014/24/EU; economic operators from certain Member States may have to comply with other requirements set out in that Annex."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(2)")
    }

}