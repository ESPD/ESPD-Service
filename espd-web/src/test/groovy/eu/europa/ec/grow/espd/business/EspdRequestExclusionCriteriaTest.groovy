package eu.europa.ec.grow.espd.business
/**
 * Created by vigi on 11/17/15:3:54 PM.
 */
class EspdRequestExclusionCriteriaTest extends AbstractEspdXmlMarshalling {

    private void checkCriterionId(def result, int idx, String expectedId) {
        assert result.Criterion[idx].CriterionID.text() == expectedId
        assert result.Criterion[idx].CriterionID.@schemeAgencyID.text() == "EU-COM-GROW"
        assert result.Criterion[idx].CriterionID.@schemeVersionID.text() == "1.0"
        assert result.Criterion[idx].CriterionID.@schemeID.text() == "CriteriaID"
    }

    private void checkCriterionTypeCode(def result, int idx, String expectedTypeCode) {
        assert result.Criterion[idx].CriterionTypeCode.text() == expectedTypeCode
        assert result.Criterion[idx].CriterionTypeCode.@listAgencyID.text() == "EU-COM-GROW"
        assert result.Criterion[idx].CriterionTypeCode.@listID.text() == "CriteriaTypeCode"
        assert result.Criterion[idx].CriterionTypeCode.@listVersionID.text() == "1.0"
    }

    private void checkLegislationReference(def result, int idx, String expectedArticle) {
        def ref = result.Criterion[idx].CriterionLegislationReference

        assert ref.LegislationTitle.text() == "Directive 2014/24/EU"
        assert ref.LegislationDescription.text() == "On public procurement and repealing Directive 2004/18/EC"
        assert ref.JurisdictionLevelCode.text() == "EU_DIRECTIVE"
        assert ref.JurisdictionLevelCode.@listAgencyID.text() == "EU-COM-GROW"
        assert ref.JurisdictionLevelCode.@listID.text() == "CriterionJurisdictionLevelCode"
        assert ref.JurisdictionLevelCode.@listVersionID.text() == "1.0"
        assert ref.LegislationArticle.text() == expectedArticle
        assert ref.LegislationURIID.text() == "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024"
    }

    def "00. should contain the 'Grounds relating to criminal convictions' criterion"() {
        when:
        def result = parseXml()
        def idx = 0

        then: "CriterionID element"
        checkCriterionId(result, idx, "005eb9ed-1347-4ca3-bb29-9bc0db64e1ab")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Grounds relating to criminal convictions"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for criminal conviction, such as participation in a criminal organisation, as defined in Article 2 of Council Framework Decision 2008/841/JHA?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(1)")
    }

    def "01. should contain the 'Corruption' criterion"() {
        when:
        def result = parseXml()
        def idx = 1

        then: "CriterionID element"
        checkCriterionId(result, idx, "c27b7c4e-c837-4529-b867-ed55ce639db5")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Corruption"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for corruption, as defined in Article 3 of the Convention on the fight against corruption involving officials of the European Communities or officials of Member States of the European Union and Article 2(1) of Council Framework Decision 2003/568/JHA, as well as corruption as defined in the national law of the contracting authority or the economic operator?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(1)")
    }

    def "02. should contain the 'Fraud' criterion"() {
        when:
        def result = parseXml()
        def idx = 2

        then: "CriterionID element"
        checkCriterionId(result, idx, "297d2323-3ede-424e-94bc-a91561e6f320")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Fraud"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for fraud within the meaning of Article 1 of the Convention on the protection of the European Communities'' financial interests?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(1)")
    }

    def "03. should contain the 'Terrorist offences or offences linked to terrorist activities' criterion"() {
        when:
        def result = parseXml()
        def idx = 3

        then: "CriterionID element"
        checkCriterionId(result, idx, "d486fb70-86b3-4e75-97f2-0d71b5697c7d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Terrorist offences or offences linked to terrorist activities"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for terrorist offences or offences linked to terrorist activities, as defined in Articles 1 and 3 of Council Framework Decision 2002/475/JHA respectively, or inciting or aiding or abetting or attempting to commit an offence, as referred to in Article 4 of that Framework Decision?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(1)")
    }

    def "04. should contain the 'Money laundering or terrorist financing' criterion"() {
        when:
        def result = parseXml()
        def idx = 4

        then: "CriterionID element"
        checkCriterionId(result, idx, "47112079-6fec-47a3-988f-e561668c3aef")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Money laundering or terrorist financing"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for Money laundering or terrorist financing, as defined in Article 1 of Directive 2005/60/EC of the European Parliament and of the Council?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(1)")
    }

    def "05. should contain the 'Child labour and other forms of trafficking in human beings' criterion"() {
        when:
        def result = parseXml()
        def idx = 5

        then: "CriterionID element"
        checkCriterionId(result, idx, "d789d01a-fe03-4ccd-9898-73f9cfa080d1")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Child labour and other forms of trafficking in human beings"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for child labour and other forms of trafficking in human beings as defined in Article 2 of Directive 2011/36/EU of the European Parliament and of the Council?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(1)")
    }

    def "06. should contain the 'Payment of taxes' criterion"() {
        when:
        def result = parseXml()
        def idx = 6

        then: "CriterionID element"
        checkCriterionId(result, idx, "b61bbeb7-690e-4a40-bc68-d6d4ecfaa3d4")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.PAYMENT_OF_TAXES")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Payment of taxes"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Has it been established by a judicial or administrative decision having final and binding effect in accordance with the legal provisions in the country in which you are established or in the Member State of the contracting authority, that your organisation is in breach of obligations related to the payment of tax contributions?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(2)")
    }

    def "07. should contain the 'Payment of taxes - Breach of Judicial or Administrative Decision' criterion"() {
        when:
        def result = parseXml()
        def idx = 7

        then: "CriterionID element"
        checkCriterionId(result, idx, "0db387a4-d53a-4239-9ade-c1c57800826d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.PAYMENT_OF_TAXES")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Payment of taxes - Breach of Judicial or Administrative Decision"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "If it has been established that the economic operator is in breach of obligations related to the payment of tax or social security contributions, has this breach of obligations been established through a judicial or administrative decision?"
    }

    def "08. should contain the 'Payment of social security contributions' criterion"() {
        when:
        def result = parseXml()
        def idx = 8

        then: "CriterionID element"
        checkCriterionId(result, idx, "7d85e333-bbab-49c0-be8d-c36d71a72f5e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.PAYMENT_OF_SOCIAL_SECURITY")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Payment of social security contributions"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Has it been established by a judicial or administrative decision having final and binding effect in accordance with the legal provisions in the country in which you are established or in the Member State of the contracting authority, that your organisation is in breach of obligations related to the payment of social security contributions?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(2)")
    }

    def "09. should contain the 'Breaching of obligations in the fields of environmental, social and labour law' criterion"() {
        when:
        def result = parseXml()
        def idx = 9

        then: "CriterionID element"
        checkCriterionId(result, idx, "a80ddb62-d25b-4e4e-ae22-3968460dc0a9")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.ENVIRONMENTAL")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Breaching of obligations in the fields of environmental, social and labour law"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Has the economic operator, to its knowledge, breached its obligations in the fields of environmental, social and labour law? As referred to for the purposes of this procurement in national law, in the relevant notice or the procurement documents or in Article 18(2) of Directive 2004/18/EU?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(4)")
    }

    def "10. should contain the 'Bankrupt, the subject of insolvency or winding-up' criterion"() {
        when:
        def result = parseXml()
        def idx = 10

        then: "CriterionID element"
        checkCriterionId(result, idx, "d3732c09-7d62-4edc-a172-241da6636e7c")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.INSOLVENCY")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Bankrupt, the subject of insolvency or winding-up"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Is the economic operator in any of the following situations: Bankrupt, or the subject of insolvency or winding-up proceedings, or in an arrangement with creditors, or in any analogous situation arising from a similar procedure under national laws and regulations. See national law, the relevant notice or the procurement documents, or that its assets are being administered by a liquidator or by the court, or that its business activities are suspended?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(4)")
    }

    def "11. should contain the 'Guilty of grave professional misconduct' criterion"() {
        when:
        def result = parseXml()
        def idx = 11

        then: "CriterionID element"
        checkCriterionId(result, idx, "396f288a-e267-4c20-851a-ed4f7498f137")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.MISCONDUCT")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Guilty of grave professional misconduct"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Is the economic operator  guilty of grave professional misconduct? Where applicable, see definitions in national law, the relevant notice or the procurement documents."

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(4)")
    }

    def "12. should contain the 'Agreements with other economic operators aimed at distorting competition' criterion"() {
        when:
        def result = parseXml()
        def idx = 12

        then: "CriterionID element"
        checkCriterionId(result, idx, "68918c7a-f5bc-4a1a-a62f-ad8983600d48")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.MISCONDUCT")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Agreements with other economic operators aimed at distorting competition"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Has the economic operator entered into agreements with other economic operators aimed at distorting competition?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(4)")
    }

    def "13. should contain the 'Conflict of interest due to its participation in the procurement procedure' criterion"() {
        when:
        def result = parseXml()
        def idx = 13

        then: "CriterionID element"
        checkCriterionId(result, idx, "b1b5ac18-f393-4280-9659-1367943c1a2e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Conflict of interest due to its participation in the procurement procedure"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Is the economic operator aware of any conflict of interest, as indicated in national law, the relevant notice or the procurement documents due to its participation in the procurement procedure?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(4)")
    }

    def "14. should contain the 'Direct or indirect involvement in the preparation of this procurement procedure' criterion"() {
        when:
        def result = parseXml()
        def idx = 14

        then: "CriterionID element"
        checkCriterionId(result, idx, "61874050-5130-4f1c-a174-720939c7b483")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Direct or indirect involvement in the preparation of this procurement procedure"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Has the economic operator or an undertaking related to it advised the contracting authority or contracting entity or otherwise been involved in the preparation of the procurement procedure?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(4)")
    }

    def "15. should contain the 'Early termination, damages or other comparable sanctions' criterion"() {
        when:
        def result = parseXml()
        def idx = 15

        then: "CriterionID element"
        checkCriterionId(result, idx, "3293e92b-7f3e-42f1-bee6-a7641bb04251")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Early termination, damages or other comparable sanctions"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Has the economic operator experienced that a prior public contract, a prior contract with a contracting entity or a prior concession contract was terminated early, or that damages or other comparable sanctions were imposed in connection with that prior contract?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(4)")
    }

    def "16. should contain the 'Guilty of misinterpretation, withheld information, [...]' criterion"() {
        when:
        def result = parseXml()
        def idx = 16

        then: "CriterionID element"
        checkCriterionId(result, idx, "696a75b2-6107-428f-8b74-82affb67e184")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.CONFLICT_OF_INTEREST")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Guilty of misinterpretation, withheld information, [...]"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Can the economic operator confirm that it has not been guilty of serious misrepresentation in supplying the information required for the verification of the absence of grounds for exclusion or the fulfilment of the selection criteria, it has not withheld such information, it has been able, without delay, to submit the supporting documents required by a contracting authority or contracting entity, and it has not undertaken to unduly influence the decision making process of the contracting authority or contracting entity, to obtain confidential information that may confer upon it undue advantages in the procurement procedure or to negligently provide misleading information that may have a material influence on decisions concerning exclusion, selection or award?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(4)")
    }

    def "17. should contain the 'Purely national exclusion grounds' criterion"() {
        when:
        def result = parseXml()
        def idx = 17

        then: "CriterionID element"
        checkCriterionId(result, idx, "df81025e-6aa0-4665-8807-ee317e5f928e")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(result, idx, "EXCLUSION.OTHER")

        then: "CriterionName element"
        result.Criterion[idx].CriterionName.text() == "Purely national exclusion grounds"

        then: "CriterionDescription element"
        result.Criterion[idx].CriterionDescription.text() == "Do the purely national grounds of exclusion, which are specified in the relevant notice or in the procurement documents, apply?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(result, idx, "57(4)")
    }

}