package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionGroup;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.SelectionCriterionRequirement.*;

/**
 * Created by ratoico on 12/7/15 at 3:44 PM.
 */
@Getter
public enum SelectionCriterionGroup implements CcvCriterionGroup {

    /**
     *
     */
    INFO_ELECTRONICALLY_GROUP("9026e403-3eb6-4705-a9e9-e21a1efc867d", Collections.<CcvCriterionGroup>emptyList(),
            list(INFO_AVAILABLE_ELECTRONICALLY, URL, URL_CODE)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_1("1689194b-6ecf-4ab4-ab38-7656610c25bb", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR_1, AMOUNT_1)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_2("c628dd27-8016-4d80-8660-7461f2e3ee0f", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR_2, AMOUNT_2)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_3("9dd09f9f-3326-4865-9d5a-f0836076fb19", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR_3, AMOUNT_3)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_4("962011c9-9e2e-4e7b-818e-30e8506e874f", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR_4, AMOUNT_4)),
    /**
     *
     */
    YEAR_AMOUNT_GROUP_5("343795e2-98e9-4cc9-8ef2-8817cec8f49a", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR_5, AMOUNT_5)),
    /**
     *
     */
    YEAR_NUMBER_GROUP_1("96defecc-7d32-4957-82e9-aad5f3c5b736", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR_1, NUMBER_1)),
    /**
     *
     */
    YEAR_NUMBER_GROUP_2("dac727d8-2cd2-43e0-8561-6f17e25870a4", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR_2, NUMBER_2)),
    /**
     *
     */
    YEAR_NUMBER_GROUP_3("b799d324-358c-48b0-bd5e-6d205969b4a5", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR_3, NUMBER_3)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_1("1689194b-6ecf-4ab4-ab38-7656610c25bb", Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_1, RATIO_1)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_2("c628dd27-8016-4d80-8660-7461f2e3ee0f", Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_2, RATIO_2)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_3("9dd09f9f-3326-4865-9d5a-f0836076fb19", Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_3, RATIO_3)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_4("4564d79e-5db6-4a31-93ee-ac1f0019bdcb", Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_4, RATIO_4)),
    /**
     *
     */
    DESCRIPTION_RATIO_GROUP_5("bc43685e-8473-40e3-b174-3233aead6207", Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_5, RATIO_5)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_GROUP_1("96f00020-0a25-402e-b850-2378e83b5695",
            Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_1, AMOUNT_1, DATE_1, RECIPIENTS_1)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS__GROUP_2("c48572f9-47bf-423a-9885-2c78ae9ca718",
            Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_2, AMOUNT_2, DATE_2, RECIPIENTS_2)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_GROUP_3("2c7a3581-2954-4142-8c1b-5c52d7c7e9b7",
            Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_3, AMOUNT_3, DATE_3, RECIPIENTS_3)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_GROUP_4("d67a6126-dd6d-4ed2-bda7-214a19e13a63",
            Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_4, AMOUNT_4, DATE_4, RECIPIENTS_4)),
    /**
     *
     */
    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_GROUP_5("159fc086-cf34-48a4-a41b-afed62661383",
            Collections.<CcvCriterionGroup>emptyList(),
            list(DESCRIPTION_5, AMOUNT_5, DATE_5, RECIPIENTS_5)),
    /**
     *
     */
    ALL_CRITERIA_SATISFIED_GROUP("f3a6836d-2de2-4cd1-81ca-fb06178d05c5", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER)),
    /**
     *
     */
    ENROLMENT_PROFESSIONAL_REGISTER_GROUP("1768de86-a6c8-48e4-bd8e-de2f2f7424d0",
            Collections.<CcvCriterionGroup>emptyList(), list(YOUR_ANSWER)),
    /**
     *
     */
    ENROLMENT_TRADE_REGISTER_GROUP("8fe21e2c-5490-474b-90e6-fe25a7d8c538", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER)),
    /**
     *
     */
    SERVICE_CONTRACTS_AUTHORISATION_GROUP("a109e144-f65e-469d-bcda-220f1af34b6c",
            Collections.<CcvCriterionGroup>emptyList(), list(YOUR_ANSWER)),
    /**
     *
     */
    SERVICE_CONTRACTS_MEMBERSHIP_GROUP("84c72d9c-6372-4781-b957-afe97c503c6c",
            Collections.<CcvCriterionGroup>emptyList(), list(YOUR_ANSWER)),
    /**
     *
     */
    GENERAL_YEARLY_TURNOVER_GROUP("e1886054-ada4-473c-9afc-2fde82c24cf4",
            list(YEAR_AMOUNT_GROUP_1, YEAR_AMOUNT_GROUP_2, YEAR_AMOUNT_GROUP_3, YEAR_AMOUNT_GROUP_4,
                    YEAR_AMOUNT_GROUP_5), list(YOUR_ANSWER)),
    /**
     *
     */
    AVERAGE_YEARLY_TURNOVER_GROUP("08da0667-c7e3-445f-a548-1107794ef7d5", list(YEAR_AMOUNT_GROUP_1,
            YEAR_AMOUNT_GROUP_2, YEAR_AMOUNT_GROUP_3, YEAR_AMOUNT_GROUP_4, YEAR_AMOUNT_GROUP_5), list(YOUR_ANSWER)),
    /**
     *
     */
    SPECIFIC_YEARLY_TURNOVER_GROUP("ee1fdbab-f54e-4579-bcb8-060fe45178e9", list(YEAR_AMOUNT_GROUP_1,
            YEAR_AMOUNT_GROUP_2, YEAR_AMOUNT_GROUP_3, YEAR_AMOUNT_GROUP_4, YEAR_AMOUNT_GROUP_5), list(YOUR_ANSWER)),
    /**
     *
     */
    SPECIFIC_AVERAGE_TURNOVER_GROUP("fa29f9e1-dd24-4fe9-873d-1a6dbc720cb0", list(YEAR_AMOUNT_GROUP_1,
            YEAR_AMOUNT_GROUP_2, YEAR_AMOUNT_GROUP_3, YEAR_AMOUNT_GROUP_4, YEAR_AMOUNT_GROUP_5), list(YOUR_ANSWER)),
    /**
     *
     */
    FINANCIAL_RATIO_GROUP("cf00f7bb-c2cf-4565-91bb-221d78d8dd2f", list(DESCRIPTION_RATIO_GROUP_1,
            DESCRIPTION_RATIO_GROUP_2, DESCRIPTION_RATIO_GROUP_3, DESCRIPTION_RATIO_GROUP_4, DESCRIPTION_RATIO_GROUP_5),
            Collections.<CcvCriterionRequirement>emptyList()),
    /**
     *
     */
    PROFESSIONAL_RISK_INSURANCE_GROUP("42dc8062-974d-4201-91ba-7f2ea90338fd",
            Collections.<CcvCriterionGroup>emptyList(), list(AMOUNT_1)),
    /**
     *
     */
    OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS_GROUP("488ca189-bcdb-4bf4-80c7-3ad507fd89fb",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    TECHNICIANS_OR_TECHNICAL_BODIES_GROUP("9e2fd892-80e2-4c13-98bb-1515e56f45af",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_SPECIFY)),
    /**
     *
     */
    WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES_GROUP("162843ae-aa63-47ab-9b05-e5e3e0f284ff",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_SPECIFY)),
    /**
     *
     */
    TECHNICAL_FACILITIES_AND_MEASURES_GROUP("33648fa9-9dc0-4b8e-8058-bfaa0eb08ab2",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    STUDY_AND_RESEARCH_FACILITIES_GROUP("23775f16-a319-4d75-9f3c-eb3ab18d1e64",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    SUPPLY_CHAIN_MANAGEMENT_GROUP("2fd60f39-d484-4862-ba5f-0a8c46da11d8", Collections.<CcvCriterionGroup>emptyList(),
            list(PLEASE_DESCRIBE)),
    /**
     *
     */
    ALLOWANCE_OF_CHECKS_GROUP("d7721546-9106-43a7-8d31-2fe08a862b00", Collections.<CcvCriterionGroup>emptyList(),
            list(ALLOW_CHECKS)),
    /**
     *
     */
    EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS_GROUP("094618e3-b243-49ce-9b12-6aab357f2f88",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    ENVIRONMENTAL_MANAGEMENT_FEATURES_GROUP("96defecc-7d32-4957-82e9-aad5f3c5b736",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    TOOLS_PLANT_TECHNICAL_EQUIPMENT_GROUP("eb18b241-7a11-415d-a04f-94fe0dae8e77",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    SUBCONTRACTING_PROPORTION_GROUP("575f7550-8a2d-4bad-b9d8-be07ab570076",
            Collections.<CcvCriterionGroup>emptyList(), list(PERCENTAGE)),
    /**
     *
     */
    SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA_GROUP("cb73544d-e8bb-4cc6-819b-b8e04f1e240e",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA_GROUP("511ddbf6-2c53-4fea-a469-3edc9941e603",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    SUPPLY_CONTRACTS_CERTIFICATES_QC_GROUP("4887c3d7-05fc-4e3e-b066-f338910f0c4c",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA_GROUP("0e88f63c-5642-4a17-833b-ae5800e1750a",
            Collections.<CcvCriterionGroup>emptyList(), list(YOUR_ANSWER)),
    /**
     *
     */
    CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL_GROUP("82a59ce2-9c59-4075-af08-843ad89a45ec",
            Collections.<CcvCriterionGroup>emptyList(), list(YOUR_ANSWER)),;

    private final String id;

    private final List<? extends CcvCriterionGroup> subgroups;

    private final List<? extends CcvCriterionRequirement> requirements;

    SelectionCriterionGroup(String id, List<? extends CcvCriterionGroup> subgroups,
            List<? extends CcvCriterionRequirement> requirements) {
        this.id = id;
        this.subgroups = subgroups;
        this.requirements = requirements;
    }

    @SafeVarargs
    private static <T> List<T> list(T... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }

}
