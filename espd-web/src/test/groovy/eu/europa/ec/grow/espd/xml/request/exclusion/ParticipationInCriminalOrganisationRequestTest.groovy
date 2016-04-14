package eu.europa.ec.grow.espd.xml.request.exclusion

import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
import eu.europa.ec.grow.espd.domain.CriminalConvictionsCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/8/15 at 10:20 AM.
 */
class ParticipationInCriminalOrganisationRequestTest extends AbstractExclusionCriteriaFixture {

    def "01. should contain the 'Participation in a criminal organisation' criterion"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true))
        def idx = getRequestCriterionIndex(ExclusionCriterion.PARTICIPATION_CRIMINAL_ORGANISATION)

        when:
        def request = parseRequestXml(espd)

        then: "CriterionID element"
        checkCriterionId(request, idx, "005eb9ed-1347-4ca3-bb29-9bc0db64e1ab")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Participation in a criminal organisation"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for participation in a criminal orgnisation, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 2 of Council Framework Decision 2008/841/JHA of 24 October 2008 on the fight against organised crime (OJ L 300, 11.11.2008, p. 42)."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(1)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "7c637c0c-7703-4389-ba52-02997a055bd7"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 1
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 5

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")

        def r1_2 = request.Criterion[idx].RequirementGroup[0].Requirement[2]
        checkRequirement(r1_2, "7d35fb7c-da5b-4830-b598-4f347a04dceb", "Reason", "DESCRIPTION")

        def r1_3 = request.Criterion[idx].RequirementGroup[0].Requirement[3]
        checkRequirement(r1_3, "c5012430-14da-454c-9d01-34cedc6a7ded", "Who has been convicted", "DESCRIPTION")

        def r1_4 = request.Criterion[idx].RequirementGroup[0].Requirement[4]
        checkRequirement(r1_4, "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "PERIOD")

        then: "check the self-cleaning sub group"
        checkSelfCleaningRequirementGroup(request.Criterion[idx].RequirementGroup[0].RequirementGroup[0])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}