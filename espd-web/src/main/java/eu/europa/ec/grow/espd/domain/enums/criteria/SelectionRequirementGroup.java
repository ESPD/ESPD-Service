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

package eu.europa.ec.grow.espd.domain.enums.criteria;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.domain.enums.criteria.ListUtil.list;

/**
 * Created by ratoico on 12/7/15 at 3:44 PM.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SelectionRequirementGroup implements CcvRequirementGroup {

    /**
     *
     */
    INFO_ELECTRONICALLY_GROUP("9026e403-3eb6-4705-a9e9-e21a1efc867d", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY, SelectionCriterionRequirement.URL, SelectionCriterionRequirement.URL_CODE)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_1("1689194b-6ecf-4ab4-ab38-7656610c25bb", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YEAR_1, SelectionCriterionRequirement.AMOUNT_1)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_2("c628dd27-8016-4d80-8660-7461f2e3ee0f", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YEAR_2, SelectionCriterionRequirement.AMOUNT_2)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_3("9dd09f9f-3326-4865-9d5a-f0836076fb19", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YEAR_3, SelectionCriterionRequirement.AMOUNT_3)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_4("962011c9-9e2e-4e7b-818e-30e8506e874f", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YEAR_4, SelectionCriterionRequirement.AMOUNT_4)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_5("343795e2-98e9-4cc9-8ef2-8817cec8f49a", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YEAR_5, SelectionCriterionRequirement.AMOUNT_5)),
    /**
     *
     */
    YEAR_NUMBER_GROUP_1("96defecc-7d32-4957-82e9-aad5f3c5b736", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YEAR_1, SelectionCriterionRequirement.NUMBER_1)),
    /**
     *
     */
    YEAR_NUMBER_GROUP_2("dac727d8-2cd2-43e0-8561-6f17e25870a4", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YEAR_2, SelectionCriterionRequirement.NUMBER_2)),
    /**
     *
     */
    YEAR_NUMBER_GROUP_3("b799d324-358c-48b0-bd5e-6d205969b4a5", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YEAR_3, SelectionCriterionRequirement.NUMBER_3)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_1("1689194b-6ecf-4ab4-ab38-7656610c25bb", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_1, SelectionCriterionRequirement.RATIO_1)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_2("c628dd27-8016-4d80-8660-7461f2e3ee0f", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_2, SelectionCriterionRequirement.RATIO_2)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_3("9dd09f9f-3326-4865-9d5a-f0836076fb19", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_3, SelectionCriterionRequirement.RATIO_3)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_4("4564d79e-5db6-4a31-93ee-ac1f0019bdcb", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_4, SelectionCriterionRequirement.RATIO_4)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_5("bc43685e-8473-40e3-b174-3233aead6207", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_5, SelectionCriterionRequirement.RATIO_5)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_GROUP_1("96f00020-0a25-402e-b850-2378e83b5695",
            Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_1, SelectionCriterionRequirement.AMOUNT_1, SelectionCriterionRequirement.DATE_1, SelectionCriterionRequirement.RECIPIENTS_1)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_GROUP_2("c48572f9-47bf-423a-9885-2c78ae9ca718",
            Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_2, SelectionCriterionRequirement.AMOUNT_2, SelectionCriterionRequirement.DATE_2, SelectionCriterionRequirement.RECIPIENTS_2)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_GROUP_3("2c7a3581-2954-4142-8c1b-5c52d7c7e9b7",
            Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_3, SelectionCriterionRequirement.AMOUNT_3, SelectionCriterionRequirement.DATE_3, SelectionCriterionRequirement.RECIPIENTS_3)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_GROUP_4("d67a6126-dd6d-4ed2-bda7-214a19e13a63",
            Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_4, SelectionCriterionRequirement.AMOUNT_4, SelectionCriterionRequirement.DATE_4, SelectionCriterionRequirement.RECIPIENTS_4)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_GROUP_5("159fc086-cf34-48a4-a41b-afed62661383",
            Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.DESCRIPTION_5, SelectionCriterionRequirement.AMOUNT_5, SelectionCriterionRequirement.DATE_5, SelectionCriterionRequirement.RECIPIENTS_5)),
    /**
     *
     */
    ALL_CRITERIA_SATISFIED_GROUP("f3a6836d-2de2-4cd1-81ca-fb06178d05c5", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    ENROLMENT_PROFESSIONAL_REGISTER_GROUP("1768de86-a6c8-48e4-bd8e-de2f2f7424d0",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    ENROLMENT_TRADE_REGISTER_GROUP("8fe21e2c-5490-474b-90e6-fe25a7d8c538", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    SERVICE_CONTRACTS_AUTHORISATION_GROUP("a109e144-f65e-469d-bcda-220f1af34b6c",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.YOUR_ANSWER, SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    SERVICE_CONTRACTS_MEMBERSHIP_GROUP("84c72d9c-6372-4781-b957-afe97c503c6c",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.YOUR_ANSWER, SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    GENERAL_YEARLY_TURNOVER_GROUP("e1886054-ada4-473c-9afc-2fde82c24cf4",
            list(YEAR_AMOUNT_GROUP_1, YEAR_AMOUNT_GROUP_2, YEAR_AMOUNT_GROUP_3, YEAR_AMOUNT_GROUP_4,
                    YEAR_AMOUNT_GROUP_5), list(SelectionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    AVERAGE_YEARLY_TURNOVER_GROUP("08da0667-c7e3-445f-a548-1107794ef7d5", list(YEAR_AMOUNT_GROUP_1,
            YEAR_AMOUNT_GROUP_2, YEAR_AMOUNT_GROUP_3, YEAR_AMOUNT_GROUP_4, YEAR_AMOUNT_GROUP_5), list(
            SelectionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    SPECIFIC_YEARLY_TURNOVER_GROUP("ee1fdbab-f54e-4579-bcb8-060fe45178e9", list(YEAR_AMOUNT_GROUP_1,
            YEAR_AMOUNT_GROUP_2, YEAR_AMOUNT_GROUP_3, YEAR_AMOUNT_GROUP_4, YEAR_AMOUNT_GROUP_5), list(
            SelectionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    SPECIFIC_AVERAGE_TURNOVER_GROUP("fa29f9e1-dd24-4fe9-873d-1a6dbc720cb0", list(YEAR_AMOUNT_GROUP_1,
            YEAR_AMOUNT_GROUP_2, YEAR_AMOUNT_GROUP_3, YEAR_AMOUNT_GROUP_4, YEAR_AMOUNT_GROUP_5), list(
            SelectionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    SETUP_ECONOMIC_OPERATOR_GROUP("e9aa7763-c167-4352-8060-1a3d7d3e2662", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.SPECIFY_YEAR)),
    /**
     *
     */
    FINANCIAL_RATIO_GROUP("cf00f7bb-c2cf-4565-91bb-221d78d8dd2f", list(DESCRIPTION_RATIO_GROUP_1,
            DESCRIPTION_RATIO_GROUP_2, DESCRIPTION_RATIO_GROUP_3, DESCRIPTION_RATIO_GROUP_4, DESCRIPTION_RATIO_GROUP_5),
            list(SelectionCriterionRequirement.PLEASE_PROVIDE_DATA_BELOW)),
    /**
     *
     */
    PROFESSIONAL_RISK_INSURANCE_GROUP("42dc8062-974d-4201-91ba-7f2ea90338fd",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.AMOUNT_1)),
    /**
     *
     */
    OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS_GROUP("488ca189-bcdb-4bf4-80c7-3ad507fd89fb",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    TECHNICIANS_OR_TECHNICAL_BODIES_GROUP("9e2fd892-80e2-4c13-98bb-1515e56f45af",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES_GROUP("162843ae-aa63-47ab-9b05-e5e3e0f284ff",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    TECHNICAL_FACILITIES_AND_MEASURES_GROUP("33648fa9-9dc0-4b8e-8058-bfaa0eb08ab2",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    STUDY_AND_RESEARCH_FACILITIES_GROUP("23775f16-a319-4d75-9f3c-eb3ab18d1e64",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    SUPPLY_CHAIN_MANAGEMENT_GROUP("2fd60f39-d484-4862-ba5f-0a8c46da11d8", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    ALLOWANCE_OF_CHECKS_GROUP("d7721546-9106-43a7-8d31-2fe08a862b00", Collections.<CcvRequirementGroup>emptyList(),
            list(SelectionCriterionRequirement.ALLOW_CHECKS)),
    /**
     *
     */
    EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS_GROUP("094618e3-b243-49ce-9b12-6aab357f2f88",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    ENVIRONMENTAL_MANAGEMENT_FEATURES_GROUP("96defecc-7d32-4957-82e9-aad5f3c5b736",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    TOOLS_PLANT_TECHNICAL_EQUIPMENT_GROUP("eb18b241-7a11-415d-a04f-94fe0dae8e77",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    SUBCONTRACTING_PROPORTION_GROUP("575f7550-8a2d-4bad-b9d8-be07ab570076",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.PLEASE_SPECIFY)),
    /**
     *
     */
    SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA_GROUP("cb73544d-e8bb-4cc6-819b-b8e04f1e240e",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA_GROUP("511ddbf6-2c53-4fea-a469-3edc9941e603",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    SUPPLY_CONTRACTS_CERTIFICATES_QC_GROUP("4887c3d7-05fc-4e3e-b066-f338910f0c4c",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.YOUR_ANSWER, SelectionCriterionRequirement.EXPLAIN_SUPPLY_CONTRACTS_QC)),
    /**
     *
     */
    CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA_GROUP("0e88f63c-5642-4a17-833b-ae5800e1750a",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.YOUR_ANSWER, SelectionCriterionRequirement.EXPLAIN_CERTIFICATES_QA)),
    /**
     *
     */
    CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL_GROUP("82a59ce2-9c59-4075-af08-843ad89a45ec",
            Collections.<CcvRequirementGroup>emptyList(), list(SelectionCriterionRequirement.YOUR_ANSWER, SelectionCriterionRequirement.EXPLAIN_CERTIFICATES_ENVIRONMENTAL))
    ,;

    private final String id;

    private final List<? extends CcvRequirementGroup> subgroups;

    private final List<? extends CcvCriterionRequirement> requirements;

    SelectionRequirementGroup(String id, List<? extends CcvRequirementGroup> subgroups,
            List<? extends CcvCriterionRequirement> requirements) {
        this.id = id;
        this.subgroups = subgroups;
        this.requirements = requirements;
    }

    @Override
    public Boolean fulfillmentIndicator() {
        return null;
    }

}
