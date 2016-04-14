package eu.europa.ec.grow.espd.xml.request.selection
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.QualityAssuranceCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class CertificatesIndependentBodiesQARequestTest extends AbstractSelectionCriteriaFixture {

    def "32. should contain the 'Certificates by independent bodies about quality assurance standards' criterion"() {
        given:
        def espd = new EspdDocument(certificateIndependentBodiesAboutQa: new QualityAssuranceCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA)

        then: "CriterionID element"
        checkCriterionId(request, idx, "d726bac9-e153-4e75-bfca-c5385587766d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.QUALITY_ASSURANCE")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Certificates by independent bodies about quality assurance standards"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Will the economic operator be able to produce certificates drawn up by independent bodies attesting that the economic operator complies with the required quality assurance standards, including accessibility for disabled persons?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "62(2)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "0e88f63c-5642-4a17-833b-ae5800e1750a"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "8c5d1e13-54f7-4895-a65c-b8e09253130c",
                "If not, please explain why and specify which other means of proof concerning the quality assurance scheme can be provided:", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}