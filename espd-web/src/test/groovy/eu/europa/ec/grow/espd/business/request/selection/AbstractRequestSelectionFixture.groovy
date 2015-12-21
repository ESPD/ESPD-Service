package eu.europa.ec.grow.espd.business.request.selection

import eu.europa.ec.grow.espd.business.request.AbstractRequestFixture
/**
 * Created by ratoico on 12/15/15 at 5:29 PM.
 */
class AbstractRequestSelectionFixture extends AbstractRequestFixture {

    protected static void checkInfoAvailableElectronicallyRequirementGroup(def infoElectronicallyRequirementGroup) {
        assert infoElectronicallyRequirementGroup.ID.text() == "9026e403-3eb6-4705-a9e9-e21a1efc867d"
        assert infoElectronicallyRequirementGroup.RequirementGroup.size() == 0
        assert infoElectronicallyRequirementGroup.Requirement.size() == 3

        def r0 = infoElectronicallyRequirementGroup.Requirement[0]
        checkRequirement(r0, "9dae5670-cb75-4c97-901b-96ddac5a633a", "Is this information available electronically?", "INDICATOR")
        assert r0.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r0.ID.@schemeVersionID.text() == "1.0"

        def r1 = infoElectronicallyRequirementGroup.Requirement[1]
        checkRequirement(r1, "03bb1954-13ae-47d8-8ef8-b7fe0f22d700", "URL", "URL")
        assert r1.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r1.ID.@schemeVersionID.text() == "1.0"

        def r2 = infoElectronicallyRequirementGroup.Requirement[2]
        checkRequirement(r2, "e2d863a0-60cb-4e58-8c14-4c1595af48b7", "Code", "TEXT")
        assert r2.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r2.ID.@schemeVersionID.text() == "1.0"
    }

    protected static void checkYearAmountCurrency1Group(def yearAmountCurrencyGroup) {
        yearAmountCurrencyGroup.ID.text() == "1689194b-6ecf-4ab4-ab38-7656610c25bb"
        yearAmountCurrencyGroup.Requirement.size() == 3

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "5aacceb3-280e-42f1-b2da-3d8ac7877fe9", "Year", "QUANTITY")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
        checkRequirement(yearAmountCurrencyGroup.Requirement[2], "095c4a57-7f84-4863-a55e-363068d1aaf4", "Currency", "CURRENCY")
    }

    protected static void checkYearAmountCurrency2Group(def yearAmountCurrencyGroup) {
        yearAmountCurrencyGroup.ID.text() == "c628dd27-8016-4d80-8660-7461f2e3ee0f"
        yearAmountCurrencyGroup.Requirement.size() == 3

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "5aacceb3-280e-42f1-b2da-3d8ac7877fe9", "Year", "QUANTITY")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
        checkRequirement(yearAmountCurrencyGroup.Requirement[2], "095c4a57-7f84-4863-a55e-363068d1aaf4", "Currency", "CURRENCY")
    }

    protected static void checkYearAmountCurrency3Group(def yearAmountCurrencyGroup) {
        yearAmountCurrencyGroup.ID.text() == "9dd09f9f-3326-4865-9d5a-f0836076fb19"
        yearAmountCurrencyGroup.Requirement.size() == 3

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "5aacceb3-280e-42f1-b2da-3d8ac7877fe9", "Year", "QUANTITY")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
        checkRequirement(yearAmountCurrencyGroup.Requirement[2], "095c4a57-7f84-4863-a55e-363068d1aaf4", "Currency", "CURRENCY")
    }

    protected static void checkDescriptionAmountDateRecipients1Group(def group) {
        group.ID.text() == "96f00020-0a25-402e-b850-2378e83b5695"
        group.Requirement.size() == 4

        checkRequirement(group.Requirement[0], "ab05ff3b-f3e1-4441-9b43-ee9912e29e92", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "42ec8116-31a7-4118-8612-5b04f5c8bde7", "Date", "DATE")
        checkRequirement(group.Requirement[3], "a92536ab-6783-40bb-a037-5d31f421fd85", "Recipients", "TEXT")
    }

    protected static void checkDescriptionAmountDateRecipients2Group(def group) {
        group.ID.text() == "c48572f9-47bf-423a-9885-2c78ae9ca718"
        group.Requirement.size() == 4

        checkRequirement(group.Requirement[0], "ab05ff3b-f3e1-4441-9b43-ee9912e29e92", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "42ec8116-31a7-4118-8612-5b04f5c8bde7", "Date", "DATE")
        checkRequirement(group.Requirement[3], "a92536ab-6783-40bb-a037-5d31f421fd85", "Recipients", "TEXT")
    }

    protected static void checkDescriptionAmountDateRecipients3Group(def group) {
        group.ID.text() == "2c7a3581-2954-4142-8c1b-5c52d7c7e9b7"
        group.Requirement.size() == 4

        checkRequirement(group.Requirement[0], "ab05ff3b-f3e1-4441-9b43-ee9912e29e92", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "42ec8116-31a7-4118-8612-5b04f5c8bde7", "Date", "DATE")
        checkRequirement(group.Requirement[3], "a92536ab-6783-40bb-a037-5d31f421fd85", "Recipients", "TEXT")
    }

}
