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
class AbstractSelectionCriteriaFixture extends AbstractCriteriaFixture {

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
        assert g1_1.Requirement.size() == 2
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
    }

    protected static void checkYearAmountCurrencyGroup(def yearAmountCurrencyGroup) {
        assert yearAmountCurrencyGroup.ID.text() == "1689194b-6ecf-4ab4-ab38-7656610c25bb"
        assert yearAmountCurrencyGroup.Requirement.size() == 2

        checkRequirement(yearAmountCurrencyGroup.Requirement[0], "5aacceb3-280e-42f1-b2da-3d8ac7877fe9", "Year", "QUANTITY_YEAR")
        checkRequirement(yearAmountCurrencyGroup.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
    }

    protected static void checkDescriptionRatioGroup(def descriptionRatioGroup) {
        assert descriptionRatioGroup.ID.text() == "1689194b-6ecf-4ab4-ab38-7656610c25bb"
        assert descriptionRatioGroup.Requirement.size() == 2
        assert descriptionRatioGroup.@pi.text() == ""

        checkRequirement(descriptionRatioGroup.Requirement[0], "ab05ff3b-f3e1-4441-9b43-ee9912e29e92", "Description", "DESCRIPTION")
        checkRequirement(descriptionRatioGroup.Requirement[1], "5461b973-7067-457e-93cc-8338da2c3eef", "Ratio", "QUANTITY")
    }

    protected static void checkDescriptionAmountDateRecipientsGroup(def group) {
        assert group.ID.text() == "96f00020-0a25-402e-b850-2378e83b5695"
        assert group.Requirement.size() == 4
        assert group.@pi.text() == ""

        checkRequirement(group.Requirement[0], "ab05ff3b-f3e1-4441-9b43-ee9912e29e92", "Description", "DESCRIPTION")
        checkRequirement(group.Requirement[1], "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")
        checkRequirement(group.Requirement[2], "42ec8116-31a7-4118-8612-5b04f5c8bde7", "Date", "DATE")
        checkRequirement(group.Requirement[3], "a92536ab-6783-40bb-a037-5d31f421fd85", "Recipients", "DESCRIPTION")
    }

    protected static void checkYearNumberGroup(def yearNumberGroup) {
        assert yearNumberGroup.ID.text() == "96defecc-7d32-4957-82e9-aad5f3c5b736"
        assert yearNumberGroup.Requirement.size() == 2
        assert yearNumberGroup.@pi.text() == ""

        checkRequirement(yearNumberGroup.Requirement[0], "5aacceb3-280e-42f1-b2da-3d8ac7877fe9", "Year", "QUANTITY_YEAR")
        checkRequirement(yearNumberGroup.Requirement[1], "42037f41-53af-44df-b6b8-2395cee98087", "Number", "QUANTITY_INTEGER")
    }

}
