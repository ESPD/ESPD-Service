package eu.europa.ec.grow.espd.xml.response.exclusion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:15 PM.
 */
class BankruptcyResponseTest extends AbstractExclusionCriteriaFixture {

    def "12. should contain the 'Bankruptcy' criterion"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.BANKRUPTCY)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "d3732c09-7d62-4edc-a172-241da6636e7c")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.BANKRUPTCY_INSOLVENCY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Bankruptcy"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Is the economic operator bankrupt? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "d91c11a1-f19e-4b83-8ade-c4be2bf00555"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 3

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")

        def r1_2 = request.Criterion[idx].RequirementGroup[0].Requirement[2]
        checkRequirement(r1_2, "4e3f468a-86c4-4c99-bd15-c8b221229348", "Indicate reasons for being nevertheless to perform the contract", "DESCRIPTION")

        then: "check second sub group"
        def sub2 = request.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(sub2)
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.BANKRUPTCY)

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true,
                description: "bogus description."))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.BANKRUPTCY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")
        req.Response[0].Description.text() == "bogus description."
    }

    def "check the 'Indicate reasons' requirement response"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true,
                reason: "Reason here."))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.BANKRUPTCY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[2]
        checkRequirement(req, "4e3f468a-86c4-4c99-bd15-c8b221229348", "Indicate reasons for being nevertheless to perform the contract", "DESCRIPTION")
        req.Response[0].Description.text() == "Reason here."
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.BANKRUPTCY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_12.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.BANKRUPTCY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "http://hodor_12.com"
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BankruptcyCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_12")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion.BANKRUPTCY)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_12"
    }

}