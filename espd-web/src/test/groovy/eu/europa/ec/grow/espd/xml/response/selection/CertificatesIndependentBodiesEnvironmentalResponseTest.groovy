package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class CertificatesIndependentBodiesEnvironmentalResponseTest extends AbstractSelectionCriteriaFixture {

    def "33. should contain the 'Certificates by independent bodies about environmental management systems or standards' criterion"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutEnvironmental: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "8ed65e48-fd0d-444f-97bd-4f58da632999")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Certificates by independent bodies about environmental management systems or standards"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Will the economic operator be able to produce certificates drawn up by independent bodies attesting that the economic operator complies with the required environmental management systems or standards?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "62(2)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "82a59ce2-9c59-4075-af08-843ad89a45ec"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "b0aace10-fd73-46d1-ae78-289ee5cd42ca",
                "If not, please explain why and specify which other means of proof concerning the environmental management systems or standards can be provided:", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutEnvironmental: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'If not, please explain why and state which other means of proof can be provided:' requirements response"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutEnvironmental: new TechnicalProfessionalCriterion(exists: true,
                description: "explain description"))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "b0aace10-fd73-46d1-ae78-289ee5cd42ca",
                "If not, please explain why and specify which other means of proof concerning the environmental management systems or standards can be provided:", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "explain description"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutEnvironmental: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutEnvironmental: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_32.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_32.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutEnvironmental: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_32")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_32"
    }

}