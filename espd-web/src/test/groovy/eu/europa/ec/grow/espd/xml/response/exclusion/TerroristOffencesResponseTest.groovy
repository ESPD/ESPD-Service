package eu.europa.ec.grow.espd.xml.response.exclusion

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
import eu.europa.ec.grow.espd.domain.CriminalConvictionsCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import org.joda.time.LocalDate

/**
 * Created by ratoico on 12/9/15 at 11:55 AM.
 */
class TerroristOffencesResponseTest extends AbstractExclusionCriteriaFixture {

    def "04. should contain the 'Terrorist offences or offences linked to terrorist activities' criterion"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "d486fb70-86b3-4e75-97f2-0d71b5697c7d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Terrorist offences or offences linked to terrorist activities"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for terrorist offences or offences linked to terrorist activities, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Articles 1 and 3 of Council Framework Decision of 13 June 2002 on combating terrorism (OJ L 164, 22.6.2002, p. 3). This exclusion ground also includes inciting or aiding or abetting or attempting to commit an offence, as referred to in Article 4 of that Framework Decision."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(1)")


        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "2380efc6-2c86-4aa6-8645-c56cb87ad5a1"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 1
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 5

        then: "check the self-cleaning sub group"
        checkSelfCleaningRequirementGroup(request.Criterion[idx].RequirementGroup[0].RequirementGroup[0])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

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
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true, dateOfConviction: now))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")
        req.Response.size() == 1
        req.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(now.time))
    }

    def "check the 'Reason' requirement response"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true, reason: "Reason_04 here"))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[2]
        checkRequirement(req, "7d35fb7c-da5b-4830-b598-4f347a04dceb", "Reason", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "Reason_04 here"
    }

    def "check the 'Who has been convicted' requirement response"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true, convicted: "Hodor_04 was convicted"))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[3]
        checkRequirement(req, "c5012430-14da-454c-9d01-34cedc6a7ded", "Who has been convicted", "DESCRIPTION")
        req.Response.size() == 1
        req.Response[0].Description.text() == "Hodor_04 was convicted"
    }

    def "check the 'Length of the period of exclusion' requirement response"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true, periodLength: "7 years"))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[4]
        checkRequirement(req, "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "PERIOD")
        req.Response.size() == 1
        req.Response[0].Period.Description[0].text() == "7 years"
    }

    def "check the 'Have you taken measures to demonstrate your reliability (\"Self-Cleaning\")' requirement response"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true,
                selfCleaning: new SelfCleaning(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Self cleaning description' requirement response"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true,
                selfCleaning: new SelfCleaning(description: "Hodor_04 is clean")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Description.text() == "Hodor_04 is clean"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_04.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "http://hodor_04.com"
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictionsCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_04")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(ExclusionCriterion.TERRORIST_OFFENCES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_04"
    }

}