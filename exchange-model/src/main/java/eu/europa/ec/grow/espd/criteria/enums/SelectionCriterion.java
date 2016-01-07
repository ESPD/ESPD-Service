package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterion;
import eu.europa.ec.grow.espd.entities.CcvCriterionGroup;
import eu.europa.ec.grow.espd.entities.CcvCriterionType;
import eu.europa.ec.grow.espd.entities.CcvLegislation;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.SelectionCriterionGroup.*;

/**
 * Created by vigi on 11/17/15:3:11 PM.
 */
@Getter
public enum SelectionCriterion implements CcvCriterion {

    /**
     *
     */
    ALL_SELECTION_CRITERIA_SATISFIED("7e7db838-eeac-46d9-ab39-42927486f22d", "All selection criteria will be satisfied",
            "The economic operator satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice.",
            SelectionCriterionTypeCode.ALL_CRITERIA_SATISFIED, null, list(ALL_CRITERIA_SATISFIED_GROUP)),
    /**
     *
     */
    ENROLMENT_PROFESSIONAL_REGISTER("6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f",
            "Enrolment in a relevant professional register",
            "The economic operator is enrolled in relevant professional registers kept in the Member State of its establishment as described in Annex XI of Directive 2014/24/EU; economic operators from certain Member States may have to comply with other requirements set out in that Annex.",
            SelectionCriterionTypeCode.SUITABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_2,
            list(ENROLMENT_PROFESSIONAL_REGISTER_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    ENROLMENT_TRADE_REGISTER("87b3fa26-3549-4f92-b8e0-3fd8f04bf5c7", "Enrolment in a trade register",
            "The economic operator is enrolled in trade registers kept in the Member State of its establishment as described in Annex XI of Directive 2014/24/EU; economic operators from certain Member States may have to comply with other requirements set out in that Annex.",
            SelectionCriterionTypeCode.SUITABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_2,
            list(ENROLMENT_TRADE_REGISTER_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SERVICE_CONTRACTS_AUTHORISATION("9eeb6d5c-0eb8-48e8-a4c5-5087a7c095a4",
            "For service contracts: authorisation of particular organisation needed",
            "Is a particular membership of a particular organisation needed in order to be able to perform the service in question in the country of establishment of the economic operator?",
            SelectionCriterionTypeCode.SUITABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_2,
            list(SERVICE_CONTRACTS_AUTHORISATION_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SERVICE_CONTRACTS_MEMBERSHIP("73f10e36-ed7a-412e-995c-aa76463e3776",
            "For service contracts: membership of particular organisation needed",
            "Is a particular membership of a particular organisation needed in order to be able to perform the service in question in the country of establishment of the economic operator?",
            SelectionCriterionTypeCode.SUITABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_2, list(SERVICE_CONTRACTS_MEMBERSHIP_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    GENERAL_YEARLY_TURNOVER("499efc97-2ac1-4af2-9e84-323c2ca67747", "General yearly turnover",
            "The economic operator's general yearly turnover for the number of financial years required in the relevant notice, the procurement documents or the ESPD is as follows.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, LegislationReference.DIRECTIVE_2014_24_EU_58_3,
            list(GENERAL_YEARLY_TURNOVER_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    AVERAGE_YEARLY_TURNOVER("b16cb9fc-6cb7-4585-9302-9533b415cf48", "Average yearly turnover",
            "The economic operator's average yearly turnover for the number of years required in the relevant notice, the procurement documents or the ESPD is as follows.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, LegislationReference.DIRECTIVE_2014_24_EU_58_3,
            list(AVERAGE_YEARLY_TURNOVER_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SPECIFIC_YEARLY_TURNOVER("074f6031-55f9-4e99-b9a4-c4363e8bc315", "Specific yearly turnover",
            "The economic operator's specific yearly turnover in the business area covered by the contract for the number of financial years required in the relevant notice, the procurement documents or the ESPD is as follows.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, LegislationReference.DIRECTIVE_2014_24_EU_58_3,
            list(SPECIFIC_YEARLY_TURNOVER_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SPECIFIC_AVERAGE_TURNOVER("d3dfb714-f558-4512-bbc5-e456fa2339de", "Specific average turnover",
            "The economic operator's specific average yearly turnover in the business area covered by the contract for the number of years required in the relevant notice, the procurement documents or the ESPD is as follows.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, LegislationReference.DIRECTIVE_2014_24_EU_58_3,
            list(SPECIFIC_AVERAGE_TURNOVER_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    FINANCIAL_RATIO("e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46", "Financial ratio",
            "Concerning the financial ratios  specified in the relevant notice, the procurement documents or the ESPD, the economic operator declares that the actual values for the required ratios are as follows.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, LegislationReference.DIRECTIVE_2014_24_EU_58_3,
            list(FINANCIAL_RATIO_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    PROFESSIONAL_RISK_INSURANCE("7604bd40-4462-4086-8763-a50da51a869c", "Professional risk indemnity insurance",
            "The insured amount in its professional risk indemnity insurance is the following.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, LegislationReference.DIRECTIVE_2014_24_EU_58_3,
            list(PROFESSIONAL_RISK_INSURANCE_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS("ab0e7f2e-6418-40e2-8870-6713123e41ad",
            "Other economic or financial requirements",
            "Concerning the other economic or financial requirements, if any, that may have been specified in the relevant notice or the procurement documents, please specify which apply to this economic operator.",
            SelectionCriterionTypeCode.ECONOMIC_FINANCIAL_STANDING, LegislationReference.DIRECTIVE_2014_24_EU_58_3,
            list(OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    WORK_CONTRACTS_PERFORMANCE_OF_WORKS("cdd3bb3e-34a5-43d5-b668-2aab86a73822",
            "For works contracts: performance of works of the specified type",
            "During the reference period, the economic operator has performed the following works of the specified type. Contracting authorities may require up to five years and allow experience dating from more than five years.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(DESCRIPTION_AMOUNT_DATE_RECIPIENTS_1_GROUP, DESCRIPTION_AMOUNT_DATE_RECIPIENTS_2_GROUP,
                    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_3_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SUPPLY_CONTRACTS_PERFORMANCE_OF_DELIVERIES("3a18a175-1863-4b1d-baef-588ce61960ca",
            "For supply contracts: performance of deliveries of the specified type",
            "During the reference period, the economic operator has delivered the following principal deliveries of the type specified. Contracting authorities may require up to three years and allow experience dating from more than three years.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(DESCRIPTION_AMOUNT_DATE_RECIPIENTS_1_GROUP, DESCRIPTION_AMOUNT_DATE_RECIPIENTS_2_GROUP,
                    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_3_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES("5e506c16-26ab-4e32-bb78-b27f87dc0565",
            "For service contracts: performance of services of the specified type",
            "During the reference period, the economic operator has provided the following main services of the type specified. Contracting authorities may require up to three years and allow experience dating from more than three years.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(DESCRIPTION_AMOUNT_DATE_RECIPIENTS_1_GROUP, DESCRIPTION_AMOUNT_DATE_RECIPIENTS_2_GROUP,
                    DESCRIPTION_AMOUNT_DATE_RECIPIENTS_3_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    TECHNICIANS_OR_TECHNICAL_BODIES("3aaca389-4a7b-406b-a4b9-080845d127e7",
            "Technicians or technical bodies for quality control",
            "The economic operator can call upon the following technicians or technical bodies , especially those responsible for quality control. For technicians or technical bodies not belonging directly to the economic operator's undertaking but on whose capacities the economic operator relies as set out under Part II, Section C, separate ESPD forms must be filled in.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(TECHNICIANS_OR_TECHNICAL_BODIES_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES("c599c130-b29f-461e-a187-4e16c7d40db7",
            "For works contracts: technicians or technical bodies to carry out the work",
            "In the case of public works contracts, the economic operator will be able to call on the following technicians or technical bodies to carry out the work.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    TECHNICAL_FACILITIES_AND_MEASURES("4bf996d9-439c-40c6-9ab9-980a48cb55a1",
            "Technical facilities and measures for ensuring quality",
            "The economic operator uses the following technical facilities and measures for ensuring quality are as follows.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(TECHNICAL_FACILITIES_AND_MEASURES_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    STUDY_AND_RESEARCH_FACILITIES("90a2e100-44cc-45d3-9970-69d6714f1596", "Study and research facilities",
            "The economic operator uses the following study and research facilities are as follows.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(STUDY_AND_RESEARCH_FACILITIES_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SUPPLY_CHAIN_MANAGEMENT("dc12a151-7fdf-4733-a8f0-30f667292e66", "Supply chain management",
            "The economic operator will be able to apply the following supply chain management and tracking systems when performing the contract.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(SUPPLY_CHAIN_MANAGEMENT_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    ALLOWANCE_OF_CHECKS("c8809aa1-29b6-4f27-ae2f-27e612e394db", "Allowance of checks",
            "For complex products or services to be supplied or, exceptionally, for products or services which are required for a special purpose: The economic operator will allow checks  to be conducted on the production capacities or the technical capacity of the economic operator and, where necessary, on the means of study and research which are available to it and on the quality control measures? The check is to be performed by the contracting authority or, in case the latter consents to this, on its behalf by a competent official body of the country in which the supplier or service provider is established.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(ALLOWANCE_OF_CHECKS_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS("07301031-2270-41af-8e7e-66fe0c777107",
            "Educational and professional qualifications",
            "The following educational and professional qualifications are held by the service provider or the contractor itself, and/or (depending on the requirements set out in the relevant notice or the procurement documents by its managerial staff.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    ENVIRONMENTAL_MANAGEMENT_FEATURES("9460457e-b43d-48a9-acd1-615de6ddd33e", "Environmental management measures",
            "The economic operator will be able to apply the following environmental management measures when performing the contract.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(ENVIRONMENTAL_MANAGEMENT_FEATURES_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    NUMBER_OF_MANAGERIAL_STAFF("6346959b-e097-4ea1-89cd-d1b4c131ea4d", "Number of managerial staff",
            "The economic operator’s number of managerial staff for the last three years were as follows.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(NUMBER_OF_MANAGERIAL_STAFF_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    AVERAGE_ANNUAL_MANPOWER("1f49b3f0-d50f-43f6-8b30-4bafab108b9b", "Average annual manpower",
            "The economic operator’s average annual manpower for the last three years were as follows.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(AVERAGE_ANNUAL_MANPOWER_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    TOOLS_PLANT_TECHNICAL_EQUIPMENT("cc18c023-211d-484d-a32e-52f3f970285f", "Tools, plant or technical equipment",
            "The following tools, plant or technical equipment will be available to it for performing the contract.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(TOOLS_PLANT_TECHNICAL_EQUIPMENT_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SUBCONTRACTING_PROPORTION("612a1625-118d-4ea4-a6db-413184e7c0a8", "Subcontracting proportion",
            "The economic operator intends possibly to subcontract  the following proportion (i.e. percentage) of the contract. Please note that if the economic operator has decided to subcontract a part of the contract and relies on the subcontractor’s capacities to perform that part, then please fill in a separate ESPD for such subcontractors, see Part II, Section C above.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(SUBCONTRACTING_PROPORTION_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA("bdf0601d-2480-4250-b870-658d0ee95be6",
            "For supply contracts: samples, descriptions or photographs without certification of authenticity",
            "The economic operator will supply the required samples, descriptions or photographs of the products to be supplied, which do not need to be accompanied by certifications of authenticity.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA("7662b7a9-bcb8-4763-a0a7-7505d8e8470d",
            "For supply contracts: samples, descriptions or photographs with certification of authenticity",
            "The economic operator will supply the required samples, descriptions or photographs of the products to be supplied and will provide certifications of authenticity where applicable.",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    SUPPLY_CONTRACTS_CERTIFICATES_QC("a7669d7d-9297-43e1-9d10-691a1660187c",
            "For supply contracts: certificates by quality control institutes",
            "Can the economic operator provide the required certificates drawn up by official quality control institutes or agencies of recognised competence attesting the conformity of products clearly identified by references to the technical specifications or standards, which are set out in the relevant notice or the procurement documents?",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_58_4,
            list(SUPPLY_CONTRACTS_CERTIFICATES_QC_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA("d726bac9-e153-4e75-bfca-c5385587766d",
            "Certificates by independent bodies about quality assurance standards",
            "Will the economic operator be able to produce certificates drawn up by independent bodies attesting that the economic operator complies with the required quality assurance standards, including accessibility for disabled persons?",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_62_2,
            list(CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL("8ed65e48-fd0d-444f-97bd-4f58da632999",
            "Certificates by independent bodies about environmental management systems or standards",
            "Will the economic operator be able to produce certificates drawn up by independent bodies attesting that the economic operator complies with the required environmental management systems or standards?",
            SelectionCriterionTypeCode.TECHNICAL_PROFESSIONAL_ABILITY, LegislationReference.DIRECTIVE_2014_24_EU_62_2,
            list(CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL_GROUP, INFO_ELECTRONICALLY_GROUP));

    private final String uuid;

    private final String shortName;

    private final String description;

    private final SelectionCriterionTypeCode criterionTypeCode;

    private final LegislationReference legislationReference;

    private final List<? extends CcvCriterionGroup> groups;

    SelectionCriterion(String uuid, String shortName, String description, SelectionCriterionTypeCode criterionTypeCode,
            LegislationReference legislationReference, List<? extends CcvCriterionGroup> groups) {
        this.uuid = uuid;
        this.shortName = shortName;
        this.description = description;
        this.criterionTypeCode = criterionTypeCode;
        this.legislationReference = legislationReference;
        this.groups = groups;
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
        return legislationReference;
    }

    @Override
    public CcvCriterionType getCriterionType() {
        return this.criterionTypeCode;
    }

    private static List<SelectionCriterionGroup> list(SelectionCriterionGroup... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }

}
