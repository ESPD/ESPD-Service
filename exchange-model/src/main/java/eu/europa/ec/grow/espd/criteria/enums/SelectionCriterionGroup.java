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
    YEAR_AMOUNT_CURRENCY_1_GROUP("1689194b-6ecf-4ab4-ab38-7656610c25bb", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR, AMOUNT, CURRENCY)),
    /**
     *
     */
    YEAR_AMOUNT_CURRENCY_2_GROUP("c628dd27-8016-4d80-8660-7461f2e3ee0f", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR, AMOUNT, CURRENCY)),
    /**
     *
     */
    YEAR_AMOUNT_CURRENCY_3_GROUP("9dd09f9f-3326-4865-9d5a-f0836076fb19", Collections.<CcvCriterionGroup>emptyList(),
            list(YEAR, AMOUNT, CURRENCY)),
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
            list(YEAR_AMOUNT_CURRENCY_1_GROUP, YEAR_AMOUNT_CURRENCY_2_GROUP, YEAR_AMOUNT_CURRENCY_3_GROUP),
            list(YOUR_ANSWER)),
    /**
     *
     */
    AVERAGE_YEARLY_TURNOVER_GROUP("08da0667-c7e3-445f-a548-1107794ef7d5", list(YEAR_AMOUNT_CURRENCY_1_GROUP,
            YEAR_AMOUNT_CURRENCY_2_GROUP, YEAR_AMOUNT_CURRENCY_3_GROUP),
            list(YOUR_ANSWER)),
    /**
     *
     */
    SPECIFIC_YEARLY_TURNOVER_GROUP("ee1fdbab-f54e-4579-bcb8-060fe45178e9", list(YEAR_AMOUNT_CURRENCY_1_GROUP,
            YEAR_AMOUNT_CURRENCY_2_GROUP, YEAR_AMOUNT_CURRENCY_3_GROUP),
            list(YOUR_ANSWER)),
    /**
     *
     */
    SPECIFIC_AVERAGE_TURNOVER_GROUP("fa29f9e1-dd24-4fe9-873d-1a6dbc720cb0", list(YEAR_AMOUNT_CURRENCY_1_GROUP,
            YEAR_AMOUNT_CURRENCY_2_GROUP, YEAR_AMOUNT_CURRENCY_3_GROUP),
            list(YOUR_ANSWER)),
    /**
     *
     */
    FINANCIAL_RATIO_GROUP("cf00f7bb-c2cf-4565-91bb-221d78d8dd2f", Collections.<CcvCriterionGroup>emptyList(),
            list(REQUIRED_RATIO)),
    /**
     *
     */
    PROFESSIONAL_RISK_INSURANCE_GROUP("42dc8062-974d-4201-91ba-7f2ea90338fd",
            Collections.<CcvCriterionGroup>emptyList(), list(AMOUNT_CURRENCY)),
    /**
     *
     */
    OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS_GROUP("488ca189-bcdb-4bf4-80c7-3ad507fd89fb",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    WORK_CONTRACTS_PERFORMANCE_OF_WORKS_GROUP("996e24f2-fc7b-49a3-8b97-37268516a57c",
            Collections.<CcvCriterionGroup>emptyList(), list(DESCRIPTION, AMOUNT, DATE, RECIPIENTS)),
    /**
     *
     */
    SUPPLY_CONTRACTS_PERFORMANCE_OF_DELIVERIES_GROUP("082fac2f-2667-4cd9-8eda-c92b27a17803",
            Collections.<CcvCriterionGroup>emptyList(), list(DESCRIPTION, AMOUNT, DATE, RECIPIENTS)),
    /**
     *
     */
    SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES_GROUP("adcc74e6-3a89-476b-b66d-870b28f50960",
            Collections.<CcvCriterionGroup>emptyList(), list(DESCRIPTION, AMOUNT, DATE, RECIPIENTS)),
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
            list(PLEASE_DESCRIBE)),
    /**
     *
     */
    EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS_GROUP("094618e3-b243-49ce-9b12-6aab357f2f88",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_ENUMERATE)),
    /**
     *
     */
    ENVIRONMENTAL_MANAGEMENT_FEATURES_GROUP("96defecc-7d32-4957-82e9-aad5f3c5b736",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    NUMBER_OF_MANAGERIAL_STAFF_GROUP("990e8da4-33af-4d3e-ac39-83a003c18d97",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_DESCRIBE)),
    /**
     *
     */
    AVERAGE_ANNUAL_MANPOWER_GROUP("21e41b02-e82b-4e02-953c-7351f5cd221b",
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
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_ENUMERATE)),
    /**
     *
     */
    SUPPLY_CONTRACTS_CERTIFICATES_QC_GROUP("4887c3d7-05fc-4e3e-b066-f338910f0c4c",
            Collections.<CcvCriterionGroup>emptyList(), list(PLEASE_ENUMERATE)),
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
