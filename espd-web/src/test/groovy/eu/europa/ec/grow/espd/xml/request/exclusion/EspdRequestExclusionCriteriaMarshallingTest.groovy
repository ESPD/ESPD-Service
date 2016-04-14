package eu.europa.ec.grow.espd.xml.request.exclusion

import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture

/**
 *  Created by vigi on 11/17/15:3:54 PM.
 */
class EspdRequestExclusionCriteriaMarshallingTest extends AbstractExclusionCriteriaFixture {

    def "exclusion criteria are no longer mandatory so if none are selected then none should appear"() {
        given: "if the exists flag is either false or the criterion is not present at all"
        def espd = new EspdDocument(criminalConvictions: null,
                corruption: new CriminalConvictionsCriterion(exists: false),
                fraud: null,
                terroristOffences: null,
                moneyLaundering: null,
                childLabour: null,
                paymentTaxes: new TaxesCriterion(exists: false),
                paymentSocialSecurity: null,
                breachingObligationsEnvironmental: null,
                breachingObligationsSocial: new LawCriterion(exists: false),
                breachingObligationsLabour: null,
                bankruptcy: null,
                insolvency: null,
                arrangementWithCreditors: null,
                analogousSituation: null,
                assetsAdministeredByLiquidator: null,
                businessActivitiesSuspended: null,
                guiltyGrave: null,
                agreementsWithOtherEO: null,
                conflictInterest: null,
                involvementPreparationProcurement: null,
                earlyTermination: null,
                guiltyMisinterpretation: null,
                purelyNationalGrounds: null)

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "we should see the mandatory exclusion criteria (all of them minus 'purely national') plus all the selection criteria (since none of them are selected) plus the eo criteria"
        request.Criterion.size() == getMandatoryExclusionCriteriaSize() + SelectionCriterion.values().length + eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.values().length
        for (SelectionCriterion criterion : SelectionCriterion.values()) {
            checkCriterionId(request, idx++, criterion.getUuid())
        }
    }

    def "all exclusion criteria should be in the correct order"() {
        given:
        def espd = new EspdDocument(
                criminalConvictions: new CriminalConvictionsCriterion(exists: true),
                corruption: new CriminalConvictionsCriterion(exists: true),
                fraud: new CriminalConvictionsCriterion(exists: true),
                terroristOffences: new CriminalConvictionsCriterion(exists: true),
                moneyLaundering: new CriminalConvictionsCriterion(exists: true),
                childLabour: new CriminalConvictionsCriterion(exists: true),
                paymentTaxes: new TaxesCriterion(exists: true),
                paymentSocialSecurity: new TaxesCriterion(exists: true),
                breachingObligationsEnvironmental: new LawCriterion(exists: true),
                breachingObligationsSocial: new LawCriterion(exists: true),
                breachingObligationsLabour: new LawCriterion(exists: true),
                bankruptcy: new BankruptcyCriterion(exists: true),
                insolvency: new BankruptcyCriterion(exists: true),
                arrangementWithCreditors: new BankruptcyCriterion(exists: true),
                analogousSituation: new BankruptcyCriterion(exists: true),
                assetsAdministeredByLiquidator: new BankruptcyCriterion(exists: true),
                businessActivitiesSuspended: new BankruptcyCriterion(exists: true),
                guiltyGrave: new MisconductDistortionCriterion(exists: true),
                agreementsWithOtherEO: new MisconductDistortionCriterion(exists: true),
                conflictInterest: new ConflictInterestCriterion(exists: true),
                involvementPreparationProcurement: new ConflictInterestCriterion(exists: true),
                earlyTermination: new ConflictInterestCriterion(exists: true),
                guiltyMisinterpretation: new ConflictInterestCriterion(exists: true),
                purelyNationalGrounds: new PurelyNationalGrounds(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "we should see all exclusion criteria ('purely national' is selected) plus all the selection criteria (since none of them are selected) plus the only award criterion"
        request.Criterion.size() == ExclusionCriterion.values().length + SelectionCriterion.values().length + eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion.values().length
        for (ExclusionCriterion criterion : ExclusionCriterion.values()) {
            checkCriterionId(request, idx++, criterion.getUuid())
        }
    }

}