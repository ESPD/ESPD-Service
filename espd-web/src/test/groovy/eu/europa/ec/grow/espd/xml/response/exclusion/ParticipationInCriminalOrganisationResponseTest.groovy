package eu.europa.ec.grow.espd.xml.response.exclusion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.CriminalConvictionsCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
import org.joda.time.LocalDate
/**
 * Created by ratoico on 12/8/15 at 10:20 AM.
 */
class ParticipationInCriminalOrganisationResponseTest extends AbstractExclusionCriteriaFixture {

    def "01. should contain the 'Participation in a criminal organisation' full responses"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true))
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

        then: "check the self-cleaning sub group"
        def selfCleaning = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        checkSelfCleaningRequirementGroup(selfCleaning)

        then: "info available electronically sub group"
        def info = request.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(info)
    }

    def "01. 'Participation in a criminal organisation' should not appear at all when the response is negative"() {
        given:
        def now = new Date()
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: false, dateOfConviction: now,
                reason: "Reason here", convicted: "Hodor was convicted", periodLength: "7 years",
                selfCleaning: new SelfCleaning(exists: true, description: "Hodor is clean"),
                availableElectronically: new AvailableElectronically(exists: true, url: "www.hodor.com", code: "INTERNATIONAL")))
        def idx = 0

        when:
        def request = parseResponseXml(espd)

        then: "main sub group requirements"
        request.Criterion[idx].size() == 0
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Date of conviction' requirement response"() {
        given:
        def now = new Date()
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true, dateOfConviction: now))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")
        req.Response.size() == 1
        req.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(now.time))
    }

    def "check the 'Reason' requirement response"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true, reason: "Reason_01 here"))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[2]
        checkRequirement(req, "7d35fb7c-da5b-4830-b598-4f347a04dceb", "Reason", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "Reason_01 here"
    }

    def "check the 'Who has been convicted' requirement response"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true, convicted: "Hodor_01 was convicted"))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[3]
        checkRequirement(req, "c5012430-14da-454c-9d01-34cedc6a7ded", "Who has been convicted", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "Hodor_01 was convicted"
    }

    def "check the 'Length of the period of exclusion' requirement response"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true, periodLength: "7 years"))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[4]
        checkRequirement(req, "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "PERIOD")
        req.Response.size() == 1
        req.Response[0].Period.Description[0].text() == "7 years"
    }

    def "check the 'Have you taken measures to demonstrate your reliability (\"Self-Cleaning\")' requirement response"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true,
                selfCleaning: new SelfCleaning(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Self cleaning description' requirement response"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true,
                selfCleaning: new SelfCleaning(description: "Hodor_01 is clean")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Description.text() == "Hodor_01 is clean"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_01.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "http://hodor_01.com"
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_01")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_01"
    }

}