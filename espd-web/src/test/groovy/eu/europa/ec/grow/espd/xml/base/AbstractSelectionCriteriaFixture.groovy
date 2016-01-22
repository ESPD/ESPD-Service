package eu.europa.ec.grow.espd.xml.base
/**
 * Created by ratoico on 12/15/15 at 5:29 PM.
 */
class AbstractSelectionCriteriaFixture extends AbstractCriteriaFixture {

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
        checkRequirement(r2, "e2d863a0-60cb-4e58-8c14-4c1595af48b7", "Code", "CODE")
        assert r2.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r2.ID.@schemeVersionID.text() == "1.0"
    }

    protected static void checkYearAmountCurrencyGroup1(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "1689194b-6ecf-4ab4-ab38-7656610c25bb"
        assert yearAmountCurrencyGroup.Requirement.size() == 2

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "5aacceb3-280e-42f1-b2da-3d8ac7877fe9", "Year", "QUANTITY")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
    }

    protected static void checkYearAmountCurrencyGroup2(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "c628dd27-8016-4d80-8660-7461f2e3ee0f"
        assert yearAmountCurrencyGroup.Requirement.size() == 2

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "49a57870-7fb8-451f-a7af-fa0e7f8b97e7", "Year", "QUANTITY")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "4acd0a02-c267-4d05-b456-c0565c2ffd46", "Amount", "AMOUNT")
    }

    protected static void checkYearAmountCurrencyGroup3(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "9dd09f9f-3326-4865-9d5a-f0836076fb19"
        assert yearAmountCurrencyGroup.Requirement.size() == 2

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "9d0cf1cb-27bc-4747-8579-47dce4d8d490", "Year", "QUANTITY")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "28fb4b41-5178-4b79-ba24-d9a62fa4a658", "Amount", "AMOUNT")
    }

    protected static void checkYearAmountCurrencyGroup4(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "962011c9-9e2e-4e7b-818e-30e8506e874f"
        assert yearAmountCurrencyGroup.Requirement.size() == 2

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "17a7353d-a7a4-43ee-9cc8-b9db83eeafb3", "Year", "QUANTITY")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "9f278e42-aa1d-4b2e-97cd-832248aa5393", "Amount", "AMOUNT")
    }

    protected static void checkYearAmountCurrencyGroup5(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "343795e2-98e9-4cc9-8ef2-8817cec8f49a"
        assert yearAmountCurrencyGroup.Requirement.size() == 2

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "34825634-5151-4e31-af1b-7eafadcf15be", "Year", "QUANTITY")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "cc1a0b1e-dbfd-4313-a4fb-2e543b05549b", "Amount", "AMOUNT")
    }

    protected static void checkDescriptionRatioGroup1(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "1689194b-6ecf-4ab4-ab38-7656610c25bb"
        assert descriptionRatioGroup.Requirement.size() == 2

        checkRequirement(descriptionRatioGroup.Requirement[0], "ab05ff3b-f3e1-4441-9b43-ee9912e29e92", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "5461b973-7067-457e-93cc-8338da2c3eef", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionRatioGroup2(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "c628dd27-8016-4d80-8660-7461f2e3ee0f"
        assert descriptionRatioGroup.Requirement.size() == 2

        checkRequirement(descriptionRatioGroup.Requirement[0], "927def36-1fa3-4018-8b45-7ee2c5b1e0af", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "295d82b7-5ee6-4977-8aea-bac4acf6ecdf", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionRatioGroup3(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "9dd09f9f-3326-4865-9d5a-f0836076fb19"
        assert descriptionRatioGroup.Requirement.size() == 2

        checkRequirement(descriptionRatioGroup.Requirement[0], "e6ca4034-cfee-499a-9a47-c4f2862ef4d0", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "2b792afb-87ba-47b5-a80c-aee76a6f2cc8", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionRatioGroup4(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "4564d79e-5db6-4a31-93ee-ac1f0019bdcb"
        assert descriptionRatioGroup.Requirement.size() == 2

        checkRequirement(descriptionRatioGroup.Requirement[0], "b1640c24-b405-443e-bf5e-d7771f66aab6", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "22dc4bef-182d-4b81-bddc-cc30b218f9bb", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionRatioGroup5(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "bc43685e-8473-40e3-b174-3233aead6207"
        assert descriptionRatioGroup.Requirement.size() == 2

        checkRequirement(descriptionRatioGroup.Requirement[0], "587129bc-a5e1-43be-94ac-6e5366d30c67", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "990eef0a-14c6-41af-8bf2-b8311332d152", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup1(def group) {
        assert group.ID.text() == "96f00020-0a25-402e-b850-2378e83b5695"
        assert group.Requirement.size() == 4

        checkRequirement(group.Requirement[0], "ab05ff3b-f3e1-4441-9b43-ee9912e29e92", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "42ec8116-31a7-4118-8612-5b04f5c8bde7", "Date", "DATE")
        checkRequirement(group.Requirement[3], "a92536ab-6783-40bb-a037-5d31f421fd85", "Recipients", "DESCRIPTION")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup2(def group) {
        assert group.ID.text() == "c48572f9-47bf-423a-9885-2c78ae9ca718"
        assert group.Requirement.size() == 4

        checkRequirement(group.Requirement[0], "927def36-1fa3-4018-8b45-7ee2c5b1e0af", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "4acd0a02-c267-4d05-b456-c0565c2ffd46", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "8d0e5e16-85ed-4730-a784-d4db8f439c0c", "Date", "DATE")
        checkRequirement(group.Requirement[3], "c8babafa-b6fa-4e14-8749-d913d8f1d33b", "Recipients", "DESCRIPTION")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup3(def group) {
        assert group.ID.text() == "2c7a3581-2954-4142-8c1b-5c52d7c7e9b7"
        assert group.Requirement.size() == 4

        checkRequirement(group.Requirement[0], "e6ca4034-cfee-499a-9a47-c4f2862ef4d0", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "28fb4b41-5178-4b79-ba24-d9a62fa4a658", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "c953e635-580b-4d7c-a30c-2edbde3b8fdf", "Date", "DATE")
        checkRequirement(group.Requirement[3], "5157e1ff-d272-4382-98a9-6953f5a15300", "Recipients", "DESCRIPTION")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup4(def group) {
        assert group.ID.text() == "d67a6126-dd6d-4ed2-bda7-214a19e13a63"
        assert group.Requirement.size() == 4

        checkRequirement(group.Requirement[0], "b1640c24-b405-443e-bf5e-d7771f66aab6", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "9f278e42-aa1d-4b2e-97cd-832248aa5393", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "9b263b45-fc63-4b01-a3dc-cb9c95dda449", "Date", "DATE")
        checkRequirement(group.Requirement[3], "a84ea948-cf03-47b5-b4cf-a35f49910d10", "Recipients", "DESCRIPTION")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup5(def group) {
        assert group.ID.text() == "159fc086-cf34-48a4-a41b-afed62661383"
        assert group.Requirement.size() == 4

        checkRequirement(group.Requirement[0], "587129bc-a5e1-43be-94ac-6e5366d30c67", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "cc1a0b1e-dbfd-4313-a4fb-2e543b05549b", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "056cba1d-986b-4164-92b6-26a1cbdf0690", "Date", "DATE")
        checkRequirement(group.Requirement[3], "38a4802f-0b93-4e78-ad4e-2a057e1aa578", "Recipients", "DESCRIPTION")
    }

    protected static void checkYearNumberGroup1(def yearNumberGroup) {
        assert yearNumberGroup.ID.text() == "96defecc-7d32-4957-82e9-aad5f3c5b736"
        assert yearNumberGroup.Requirement.size() == 2

        checkRequirement(yearNumberGroup.Requirement[0], "5aacceb3-280e-42f1-b2da-3d8ac7877fe9", "Year", "QUANTITY")
        checkRequirement(yearNumberGroup.Requirement[1], "42037f41-53af-44df-b6b8-2395cee98087", "Number", "QUANTITY")
    }

    protected static void checkYearNumberGroup2(def yearNumberGroup) {
        assert yearNumberGroup.ID.text() == "dac727d8-2cd2-43e0-8561-6f17e25870a4"
        assert yearNumberGroup.Requirement.size() == 2

        checkRequirement(yearNumberGroup.Requirement[0], "49a57870-7fb8-451f-a7af-fa0e7f8b97e7", "Year", "QUANTITY")
        checkRequirement(yearNumberGroup.Requirement[1], "0bb2d3bf-160f-4904-a4e8-ee672bd5cb30", "Number", "QUANTITY")
    }

    protected static void checkYearNumberGroup3(def yearNumberGroup) {
        assert yearNumberGroup.ID.text() == "b799d324-358c-48b0-bd5e-6d205969b4a5"
        assert yearNumberGroup.Requirement.size() == 2

        checkRequirement(yearNumberGroup.Requirement[0], "9d0cf1cb-27bc-4747-8579-47dce4d8d490", "Year", "QUANTITY")
        checkRequirement(yearNumberGroup.Requirement[1], "668dbc0d-2a3a-49b9-b8e1-8ebbeccd712a", "Number", "QUANTITY")
    }

}
