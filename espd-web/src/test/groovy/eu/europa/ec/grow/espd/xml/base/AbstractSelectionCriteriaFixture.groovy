/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml.base
/**
 * Created by ratoico on 12/15/15 at 5:29 PM.
 */
abstract class AbstractSelectionCriteriaFixture extends AbstractCriteriaFixture {

    protected static void checkInfoAvailableElectronicallyRequirementGroup(def infoGroup) {
        assert infoGroup.ID.text() == "9026e403-3eb6-4705-a9e9-e21a1efc867d"
        assert infoGroup.RequirementGroup.size() == 1
        assert infoGroup.Requirement.size() == 1
        assert infoGroup.@pi.text() == ""

        def r0 = infoGroup.Requirement[0]
        checkRequirement(r0, "9dae5670-cb75-4c97-901b-96ddac5a633a", "Is this information available electronically?", "INDICATOR")
        assert r0.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r0.ID.@schemeVersionID.text() == "1.0"

        def g1_1 = infoGroup.RequirementGroup[0]
        assert g1_1.ID.text() == "0a166f0a-0c5f-42b0-81e9-0fc9fa598a48"
        assert g1_1.Requirement.size() == 3
        assert g1_1.RequirementGroup.size() == 0
        assert g1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"

        def r1 = g1_1.Requirement[0]
        checkRequirement(r1, "03bb1954-13ae-47d8-8ef8-b7fe0f22d700", "URL", "EVIDENCE_URL")
        assert r1.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r1.ID.@schemeVersionID.text() == "1.0"


        def r2 = g1_1.Requirement[1]
        checkRequirement(r2, "e2d863a0-60cb-4e58-8c14-4c1595af48b7", "Code", "CODE")
        assert r2.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r2.ID.@schemeVersionID.text() == "1.0"

        def r3 = g1_1.Requirement[2]
        checkRequirement(r3, "5cbf74d9-a1e2-4233-921d-8b298842ee7d", "Issuer", "DESCRIPTION")
        assert r3.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r3.ID.@schemeVersionID.text() == "1.0"
    }

    protected static void checkYearAmountCurrencyGroup1(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "c0cd9c1c-e90a-4ff9-bce3-ac0fe31abf16"
        assert yearAmountCurrencyGroup.Requirement.size() == 2
        assert yearAmountCurrencyGroup.@pi.text() == ""

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "5aacceb3-280e-42f1-b2da-3d8ac7877fe9", "Year", "QUANTITY_YEAR")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
    }

    protected static void checkYearAmountCurrencyGroup2(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "99c9d014-d0e1-473d-b6d4-a8549f2b19fa"
        assert yearAmountCurrencyGroup.Requirement.size() == 2
        assert yearAmountCurrencyGroup.@pi.text() == ""

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "49a57870-7fb8-451f-a7af-fa0e7f8b97e7", "Year", "QUANTITY_YEAR")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "4acd0a02-c267-4d05-b456-c0565c2ffd46", "Amount", "AMOUNT")
    }

    protected static void checkYearAmountCurrencyGroup3(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "9f0e291f-05c9-4cb6-bc50-4c2d3b2049b2"
        assert yearAmountCurrencyGroup.Requirement.size() == 2
        assert yearAmountCurrencyGroup.@pi.text() == ""

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "9d0cf1cb-27bc-4747-8579-47dce4d8d490", "Year", "QUANTITY_YEAR")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "28fb4b41-5178-4b79-ba24-d9a62fa4a658", "Amount", "AMOUNT")
    }

    protected static void checkYearAmountCurrencyGroup4(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "67b8d7fa-a0aa-43d6-a30b-e15b95326df2"
        assert yearAmountCurrencyGroup.Requirement.size() == 2
        assert yearAmountCurrencyGroup.@pi.text() == ""

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "17a7353d-a7a4-43ee-9cc8-b9db83eeafb3", "Year", "QUANTITY_YEAR")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "9f278e42-aa1d-4b2e-97cd-832248aa5393", "Amount", "AMOUNT")
    }

    protected static void checkYearAmountCurrencyGroup5(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "c8c09a0c-b7a7-4271-bb6a-80f1c0e988f7"
        assert yearAmountCurrencyGroup.Requirement.size() == 2
        assert yearAmountCurrencyGroup.@pi.text() == ""

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "34825634-5151-4e31-af1b-7eafadcf15be", "Year", "QUANTITY_YEAR")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "cc1a0b1e-dbfd-4313-a4fb-2e543b05549b", "Amount", "AMOUNT")
    }

    protected static void checkDescriptionRatioGroup1(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "096686e1-82ca-4de0-8710-d74d90da0f0c"
        assert descriptionRatioGroup.Requirement.size() == 2
        assert descriptionRatioGroup.@pi.text() == ""

        checkRequirement(descriptionRatioGroup.Requirement[0], "ab05ff3b-f3e1-4441-9b43-ee9912e29e92", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "5461b973-7067-457e-93cc-8338da2c3eef", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionRatioGroup2(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "ce5a61e7-0b0d-4c8b-ac55-2a457743022a"
        assert descriptionRatioGroup.Requirement.size() == 2
        assert descriptionRatioGroup.@pi.text() == ""

        checkRequirement(descriptionRatioGroup.Requirement[0], "927def36-1fa3-4018-8b45-7ee2c5b1e0af", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "295d82b7-5ee6-4977-8aea-bac4acf6ecdf", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionRatioGroup3(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "3d0734f0-8f9b-4218-a245-c535e3773bc5"
        assert descriptionRatioGroup.Requirement.size() == 2
        assert descriptionRatioGroup.@pi.text() == ""

        checkRequirement(descriptionRatioGroup.Requirement[0], "e6ca4034-cfee-499a-9a47-c4f2862ef4d0", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "2b792afb-87ba-47b5-a80c-aee76a6f2cc8", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionRatioGroup4(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "87fda668-c758-4c54-af0a-0210e6ded32d"
        assert descriptionRatioGroup.Requirement.size() == 2
        assert descriptionRatioGroup.@pi.text() == ""

        checkRequirement(descriptionRatioGroup.Requirement[0], "b1640c24-b405-443e-bf5e-d7771f66aab6", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "22dc4bef-182d-4b81-bddc-cc30b218f9bb", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionRatioGroup5(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "637f9db3-ee53-48f7-8ee7-aefa58260730"
        assert descriptionRatioGroup.Requirement.size() == 2
        assert descriptionRatioGroup.@pi.text() == ""

        checkRequirement(descriptionRatioGroup.Requirement[0], "587129bc-a5e1-43be-94ac-6e5366d30c67", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "990eef0a-14c6-41af-8bf2-b8311332d152", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup1(def group) {
        assert group.ID.text() == "96f00020-0a25-402e-b850-2378e83b5695"
        assert group.Requirement.size() == 5
        assert group.@pi.text() == ""

        checkRequirement(group.Requirement[0], "ab05ff3b-f3e1-4441-9b43-ee9912e29e92", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "42ec8116-31a7-4118-8612-5b04f5c8bde7", "Start Date", "DATE")
        checkRequirement(group.Requirement[3], "3641b897-f9f0-4d90-909a-b6d4c4b1d645", "End Date", "DATE")
        checkRequirement(group.Requirement[4], "a92536ab-6783-40bb-a037-5d31f421fd85", "Recipients", "DESCRIPTION")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup2(def group) {
        assert group.ID.text() == "c48572f9-47bf-423a-9885-2c78ae9ca718"
        assert group.Requirement.size() == 5
        assert group.@pi.text() == ""

        checkRequirement(group.Requirement[0], "927def36-1fa3-4018-8b45-7ee2c5b1e0af", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "4acd0a02-c267-4d05-b456-c0565c2ffd46", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "8d0e5e16-85ed-4730-a784-d4db8f439c0c", "Start Date", "DATE")
        checkRequirement(group.Requirement[3], "4c842551-fb07-4a13-91e6-5653820f7e80", "End Date", "DATE")
        checkRequirement(group.Requirement[4], "c8babafa-b6fa-4e14-8749-d913d8f1d33b", "Recipients", "DESCRIPTION")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup3(def group) {
        assert group.ID.text() == "2c7a3581-2954-4142-8c1b-5c52d7c7e9b7"
        assert group.Requirement.size() == 5
        assert group.@pi.text() == ""

        checkRequirement(group.Requirement[0], "e6ca4034-cfee-499a-9a47-c4f2862ef4d0", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "28fb4b41-5178-4b79-ba24-d9a62fa4a658", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "c953e635-580b-4d7c-a30c-2edbde3b8fdf", "Start Date", "DATE")
        checkRequirement(group.Requirement[3], "822934ff-da94-40d2-a799-f29ba7bba2b0", "End Date", "DATE")
        checkRequirement(group.Requirement[4], "5157e1ff-d272-4382-98a9-6953f5a15300", "Recipients", "DESCRIPTION")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup4(def group) {
        assert group.ID.text() == "d67a6126-dd6d-4ed2-bda7-214a19e13a63"
        assert group.Requirement.size() == 5
        assert group.@pi.text() == ""

        checkRequirement(group.Requirement[0], "b1640c24-b405-443e-bf5e-d7771f66aab6", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "9f278e42-aa1d-4b2e-97cd-832248aa5393", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "9b263b45-fc63-4b01-a3dc-cb9c95dda449", "Start Date", "DATE")
        checkRequirement(group.Requirement[3], "7a95ddbd-05e8-4af4-973f-1b8d05f71e0f", "End Date", "DATE")
        checkRequirement(group.Requirement[4], "a84ea948-cf03-47b5-b4cf-a35f49910d10", "Recipients", "DESCRIPTION")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup5(def group) {
        assert group.ID.text() == "159fc086-cf34-48a4-a41b-afed62661383"
        assert group.Requirement.size() == 5
        assert group.@pi.text() == ""

        checkRequirement(group.Requirement[0], "587129bc-a5e1-43be-94ac-6e5366d30c67", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "cc1a0b1e-dbfd-4313-a4fb-2e543b05549b", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "056cba1d-986b-4164-92b6-26a1cbdf0690", "Start Date", "DATE")
        checkRequirement(group.Requirement[3], "dd71df86-3ad5-42dd-add5-9bd51dc88f05", "End Date", "DATE")
        checkRequirement(group.Requirement[4], "38a4802f-0b93-4e78-ad4e-2a057e1aa578", "Recipients", "DESCRIPTION")
    }

    protected static void checkYearNumberGroup1(def yearNumberGroup) {
        assert yearNumberGroup.ID.text() == "96defecc-7d32-4957-82e9-aad5f3c5b736"
        assert yearNumberGroup.Requirement.size() == 2
        assert yearNumberGroup.@pi.text() == ""

        checkRequirement(yearNumberGroup.Requirement[0], "5aacceb3-280e-42f1-b2da-3d8ac7877fe9", "Year", "QUANTITY_YEAR")
        checkRequirement(yearNumberGroup.Requirement[1], "42037f41-53af-44df-b6b8-2395cee98087", "Number", "QUANTITY_INTEGER")
    }

    protected static void checkYearNumberGroup2(def yearNumberGroup) {
        assert yearNumberGroup.ID.text() == "dac727d8-2cd2-43e0-8561-6f17e25870a4"
        assert yearNumberGroup.Requirement.size() == 2
        assert yearNumberGroup.@pi.text() == ""

        checkRequirement(yearNumberGroup.Requirement[0], "49a57870-7fb8-451f-a7af-fa0e7f8b97e7", "Year", "QUANTITY_YEAR")
        checkRequirement(yearNumberGroup.Requirement[1], "0bb2d3bf-160f-4904-a4e8-ee672bd5cb30", "Number", "QUANTITY_INTEGER")
    }

    protected static void checkYearNumberGroup3(def yearNumberGroup) {
        assert yearNumberGroup.ID.text() == "b799d324-358c-48b0-bd5e-6d205969b4a5"
        assert yearNumberGroup.Requirement.size() == 2
        assert yearNumberGroup.@pi.text() == ""

        checkRequirement(yearNumberGroup.Requirement[0], "9d0cf1cb-27bc-4747-8579-47dce4d8d490", "Year", "QUANTITY_YEAR")
        checkRequirement(yearNumberGroup.Requirement[1], "668dbc0d-2a3a-49b9-b8e1-8ebbeccd712a", "Number", "QUANTITY_INTEGER")
    }

}
