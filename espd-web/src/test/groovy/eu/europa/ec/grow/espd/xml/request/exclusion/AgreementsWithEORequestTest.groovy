package eu.europa.ec.grow.espd.xml.request.exclusion
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.MisconductDistortionCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 1/18/16 at 9:54 AM.
 */
class AgreementsWithEORequestTest extends AbstractExclusionCriteriaFixture {

    def "19. should contain the 'Agreements with other economic operators aimed at distorting competition' criterion"() {
        given:
        def espd = new EspdDocument(agreementsWithOtherEO: new MisconductDistortionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.AGREEMENTS_WITH_OTHER_EO)

        then: "CriterionID element"
        request.Criterion.size() == getRequestNumberOfCriteria()
        checkCriterionId(request, idx, "56d13e3d-76e8-4f23-8af6-13e60a2ee356")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.DISTORTING_MARKET")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Agreements with other economic operators aimed at distorting competition"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator entered into agreements with other economic operators aimed at distorting competition?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "dd635133-2952-4cbf-a582-d6e61efd7c28"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 1
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")

        then: "self cleaninng"
        def sub1_1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        checkSelfCleaningRequirementGroup(sub1_1)
    }

}