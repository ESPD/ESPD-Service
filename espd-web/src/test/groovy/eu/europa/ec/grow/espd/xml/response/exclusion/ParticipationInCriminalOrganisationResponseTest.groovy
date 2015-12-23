package eu.europa.ec.grow.espd.xml.request.exclusion

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.CriminalConvictions
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
import org.joda.time.LocalDate

/**
 * Created by ratoico on 12/8/15 at 10:20 AM.
 */
class ParticipationInCriminalOrganisationResponseTest extends AbstractExclusionCriteriaFixture {

    def "01. should contain the 'Participation in a criminal organisation' criterion"() {
        given:
        def now = new Date()
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictions(exists: true, dateOfConviction: now,
                reason: "Reason here", convicted: "Hodor was convicted", periodLength: "7 years",
                selfCleaning: new SelfCleaning(exists: true, description: "Hodor is clean"),
                availableElectronically: new AvailableElectronically(exists: true, description: "www.hodor.com")))
        def idx = 0

        when:
        def request = parseResponseXml(espd)

        then: "CriterionID element"
        request.Criterion.size() == 1
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
        r1_0.Response.size() == 1
        r1_0.Response[0].Indicator.text() == "true"

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")
        r1_1.Response.size() == 1
        r1_1.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(now.time))

        def r1_2 = request.Criterion[idx].RequirementGroup[0].Requirement[2]
        checkRequirement(r1_2, "7d35fb7c-da5b-4830-b598-4f347a04dceb", "Reason", "DESCRIPTION")
        r1_2.Response.size() == 1
        r1_2.Response[0].Description.text() == "Reason here"

        def r1_3 = request.Criterion[idx].RequirementGroup[0].Requirement[3]
        checkRequirement(r1_3, "c5012430-14da-454c-9d01-34cedc6a7ded", "Who has been convicted", "DESCRIPTION")
        r1_3.Response.size() == 1
        r1_3.Response[0].Description.text() == "Hodor was convicted"

        def r1_4 = request.Criterion[idx].RequirementGroup[0].Requirement[4]
        checkRequirement(r1_4, "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "TEXT")
        r1_4.Response.size() == 1
        r1_4.Response[0].Period.Description[0].text() == "7 years"

        then: "check the self-cleaning sub group"
        def selfCleaning = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        checkSelfCleaningRequirementGroup(selfCleaning)
        selfCleaning.Requirement[0].Response.size() == 1
        selfCleaning.Requirement[0].Response[0].Indicator == "true"
        selfCleaning.Requirement[1].Response.size() == 1
        selfCleaning.Requirement[1].Response[0].Description == "Hodor is clean"

        then: "info available electronically sub group"
        def info = request.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(info)
        info.Requirement[0].Response.size() == 1
        info.Requirement[0].Response[0].Indicator == "true"
        info.Requirement[1].Response.size() == 1
        info.Requirement[1].Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "www.hodor.com"
        // TODO
//        info.Requirement[2].Response.size() == 1
//        info.Requirement[2].Response[0].Code == "INTERNATIONAL"
    }

}