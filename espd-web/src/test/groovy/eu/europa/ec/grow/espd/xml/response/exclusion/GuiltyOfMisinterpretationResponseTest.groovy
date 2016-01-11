package eu.europa.ec.grow.espd.xml.response.exclusion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.ConflictInterestCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:26 PM.
 */
class GuiltyOfMisinterpretationResponseTest extends AbstractExclusionCriteriaFixture {

    def "22. should contain the 'Guilty of misinterpretation, withheld information, able to provide required documents and obtained confidential information of this procedure' criterion"() {
        given:
        def espd = new EspdDocument(guiltyMisinterpretation: new ConflictInterestCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "696a75b2-6107-428f-8b74-82affb67e184")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Guilty of misinterpretation, withheld information, able to provide required documents and obtained confidential information of this procedure"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Can the economic operator confirm the four exclusion grounds, that it has not been guilty of serious misrepresentation in supplying the information required for the verification of the absence of grounds for exclusion or the fulfilment of the selection criteria, that it has not withheld such information, it has been able, without delay, to submit the supporting documents required by a contracting authority or contracting entity, and it has not undertaken to unduly influence the decision making process of the contracting authority or contracting entity, to obtain confidential information that may confer upon it undue advantages in the procurement procedure or to negligently provide misleading information that may have a material influence on decisions concerning exclusion, selection or award?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "ddea7091-f238-4fcb-9d93-eacb13190243"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")

        then: "check second sub group"
        def sub2 = request.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(sub2)
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(guiltyMisinterpretation: new ConflictInterestCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(guiltyMisinterpretation: new ConflictInterestCriterion(exists: true,
                description: "bogus description."))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")
        req.Response[0].Description.text() == "bogus description."
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(guiltyMisinterpretation: new ConflictInterestCriterion(exists: true,
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
        def espd = new EspdDocument(guiltyMisinterpretation: new ConflictInterestCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_20.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "http://hodor_20.com"
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(guiltyMisinterpretation: new ConflictInterestCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_20")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_20"
    }

}