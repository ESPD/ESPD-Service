package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.criteria.CcvCriterion;
import eu.europa.ec.grow.espd.criteria.CcvLegislation;
import lombok.Getter;

/**
 * Created by vigi on 11/17/15:3:11 PM.
 */
@Getter
public enum SelectionCriterion implements CcvCriterion {

    /**
     *
     */
    ALL_SELECTION_CRITERIA_SATISFIED("7e7db838-eeac-46d9-ab39-42927486f22d", "All Selection Criteria Will Be Satisfied",
            "The economic operator satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice.",
            SelectionCriterionTypeCode.ALL_CRITERIA_SATISFIED, null),
    /**
     *
     */
    REGISTER_ENROLLMENT("6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f", "Register Enrollment",
            "Is the economic operator enrolled in the relevant professional or trade registers kept in the Member State of its establishment?",
            SelectionCriterionTypeCode.SUITABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_2),
    /**
     *
     */
    SERVICE_CONTRACTS_AUTHORISATION("6d22237f-e37e-4e8c-b6e7-0b86c2a63fbb", "Service Contracts - Authorisation",
            "For this service contract, is a particular authorisation or membership of a particular organisation needed in order to be able to perform the service in question in the country of establishment of the economic operator?",
            SelectionCriterionTypeCode.SUITABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_2),
    /**
     *
     */
    SERVICE_CONTRACT_EO_PARTICULAR_AUTHORISATION("bd1d08fa-ea30-49ab-96b2-a1c8e49e94b8",
            "Service Contracts - Economic Operator Particular Authorisation",
            "For this service contract if there is a particular authorisation or membership of a particular organisation needed in order to be able to perform the service in question in the country of establishment of the economic operator, does the economic operator have it?",
            SelectionCriterionTypeCode.SUITABILITY, null),
    /**
     *
     */
    GENERAL_YEARLY_TURNOVER("499efc97-2ac1-4af2-9e84-323c2ca67747", "General Yearly Turnover",
            "Please specify the economic operator (“general”) yearly turnover for the number of financial years required in the relevant notice or the procurement documents.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, null),
    /**
     *
     */
    AVERAGE_YEARLY_TURNOVER("b16cb9fc-6cb7-4585-9302-9533b415cf48", "Average Yearly Turnover",
            "Please specify the economic operator average yearly turnover for the number of years required in the relevant notice or the procurement documents.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, null),
    /**
     *
     */
    YEARLY_TURNOVER_BUSINESS_AREA("074f6031-55f9-4e99-b9a4-c4363e8bc315", "Yearly Specific Turnover  - Business Area",
            "Please specify the yearly specific turnover in the business area covered by the contract and specified in the relevant notice or the procurement documents for the number of financial years required.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, null),
    /**
     *
     */
    AVERAGE_TURNOVER_BUSINESS_AREA("d3dfb714-f558-4512-bbc5-e456fa2339de", "Average Yearly Turnover - Business Area",
            "Please specify the average yearly turnover in the area and for the number of years required in the relevant notice or the procurement documents.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, null),
    /**
     *
     */
    EO_SET_UP_DATE("a4e8a1e8-2017-444c-86c0-3e3bba81b171", "EO set up date",
            "In case the information concerning turnover (general or specific) is not available for the entire period required, please state the date on which the economic operator was set up or started trading",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, null),
    /**
     *
     */
    FINANCIAL_RATIOS("e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46", "Financial Ratios",
            "Concerning the financial ratios specified in the relevant notice or the procurement documents, please specify the actual value(s) for the required ratio(s).",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, null),
    /**
     *
     */
    PROFESSIONAL_RISK_INSURANCE("7604bd40-4462-4086-8763-a50da51a869c", "Professional Risk Indemnity Insurance",
            "Please specify the insured amount in the economic operator's professional risk indemnity insurance.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, null),
    /**
     *
     */
    OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS("ab0e7f2e-6418-40e2-8870-6713123e41ad",
            "Other Economic or Financial Requirements",
            "Concerning the other economic or financial requirements, if any, that may have been specified in the relevant notice or the procurement documents, please specify which apply to this economic operator.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, null),
    /**
     *
     */
    PUBLIC_WORK_EXPERIENCE("cdd3bb3e-34a5-43d5-b668-2aab86a73822", "Public Work Experience",
            "For public works contracts only, during the reference period, please specify which works and types of works has the economic operator performed.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    PUBLIC_SUPPLY_SERVICE_EXPERIENCE("3a18a175-1863-4b1d-baef-588ce61960ca", "Public Supply Public Service Experience",
            "For public supply and public service contracts only, during the reference period, please specify the principal deliveries of the type specified or the main services of the  type specified provided by the economic operator.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    TECHNICIANS_OR_TECHNICAL_BODIES("f33eab5b-f95f-4066-9520-c84add5405d2", "Technicians or Technical Bodies",
            "Please specify which technicians or technical bodies can the economic operator call upon, especially those responsible for quality control.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    TECHNICAL_FACILITIES_AND_MEASURES("1b0c80fd-e532-412d-8907-c864626df59a", "Technical Facilities and Measures",
            "Please specify which technical facilities and measures for ensuring quality and its study and research facilities the economic operator uses.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    SUPPLY_CHAIN_MANAGEMENT("b1f3bcff-ad84-4fcf-b0e5-c407c2a3ad3b", "Supply Chain Management",
            "Please specify which supply chain management and tracking systems when performing the contract will the economic operator be able to apply.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    CHECKS("63904f0f-a91a-4b3a-a252-002e5a9abd4f", "Checks",
            "For complex products or services to be supplied or, exceptionally, for products or services which are required for a special purpose. The economic operator will allow checks  to be conducted on the production capacities or the technical capacity of the economic operator and, where necessary, on the means of study and research which are available to it and on the quality control measures?",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS("7b127588-e232-44e5-82a8-f877a7ed426f",
            "Educational And Professional Qualifications",
            "Please specify which educational and professional qualifications are held by: a) The service provider or the contractor itself, and/or (depending on the requirements set out in the relevant notice or the procurement documents), b) Its managerial staff.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    ENVIRONMENTAL_MANAGEMENT_FEATURES("af4624dc-9012-4dc6-a019-9d0d2be00087", "Environmental Management Measures",
            "Please specify which environmental management measures when performing the contract will the economic be able to apply.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    AVERAGE_ANNUAL_MANPOWER("3df61701-757c-4e9d-9170-e1eb56803b14", "Average Annual Manpower",
            "Please specify the economic opeator's average annual manpower and the number of managerial staff for the last three years.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    TOOLS_PLANT_TECHNICAL_EQUIPMENT("9e982224-af2c-45ae-bbb2-dfdb6d62c1bc", "Tools, Plant or Technical Equipment",
            "Please specify the tools, plant or technical equipment that will be available to the economic operator for performing the contract.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    SUBCONTRACTED_PROPORTION("9bf349c7-deb7-4ddf-b1a2-b6be031ff918", "Subcontracted Proportion",
            "Please specify proportion (i.e. percentage) of the contract that the economic operator intends possibly to subcontract.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    PUBLIC_SUPPLY_CONTRACTS("2fa995c4-fc67-4bdc-aa88-de8b105f1e26", "Public Supply Contracts",
            "For public supply contracts, will the economic operator supply the required samples, descriptions or photographs of the products to be supplied, which do not need to be accompanied by certifications of authenticity?",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    PUBLIC_SUPPLY_CERTIFICATES("19f08370-3f3b-4f67-855d-e35c5d02277c", "Public Supply Contracts - Certificates",
            "For public supply contracts, will the economic operator furthermore declare that it will provide the required certificates of authenticity?",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    PUBLIC_SUPPLY_QA_CERTIFICATES("0c89b282-084d-47e0-9d13-6a6080ec6e50",
            "Public Supply Contracts - Quality Control Certificates",
            "For public supply contracts, can the economic operator provide the required certificates drawn up by official quality control institutes or agencies of recognised competence attesting the conformity of products clearly identified by references to the technical specifications or standards, which are set out in the relevant notice or the procurement documents?",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    QA_STANDARDS("8fed8afb-14c9-4931-a95c-9c09883c26cd", "Quality Assurance Standards",
            "Will the economic operator be able to produce certificates drawn up by independent bodies attesting that the economic operator complies with the required quality assurance standards, including accessibility for disabled persons?",
            SelectionCriterionTypeCode.QUALITY_ASSURANCE, null),
    /**
     *
     */
    PUBLIC_SUPPLY_ENVIRONMENT_CERTIFICATES("e99c958b-cb4f-428d-a09f-5e8d13e3347a",
            "Public Supply Contracts - Environment Certificates",
            "Will the economic operator be able to produce certificates drawn up by independent bodies attesting that the economic operator complies with the required environmental management systems or standards?",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null),
    /**
     *
     */
    REDUCTION_NO_OF_CANDIDATES("3f82bce6-00db-4dc2-a869-fb9d36f9f006", "Reduction of Number of Candidates",
            "Please specify in which way the economic operator meets the objective and non discriminatory criteria or rules to be applied in order to limit the number of candidates.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, null);

    private final String uuid;

    private final String shortName;

    private final String description;

    private final SelectionCriterionTypeCode criterionTypeCode;

    private final LegislationReference legislationReference;

    SelectionCriterion(final String uuid, final String shortName, final String description,
            final SelectionCriterionTypeCode criterionTypeCode, final LegislationReference legislationReference) {
        this.uuid = uuid;
        this.shortName = shortName;
        this.description = description;
        this.criterionTypeCode = criterionTypeCode;
        this.legislationReference = legislationReference;
    }

    @Override
    public String getTypeCode() {
        return CriterionType.SELECTION.name() + "." + getCriterionTypeCode().name();
    }

    @Override
    public String getName() {
        return getShortName();
    }

    @Override
    public CcvLegislation getLegislation() {
        return null;
    }

}
