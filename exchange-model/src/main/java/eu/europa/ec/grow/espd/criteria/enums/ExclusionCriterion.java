package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterion;
import eu.europa.ec.grow.espd.entities.CcvCriterionGroup;
import eu.europa.ec.grow.espd.entities.CcvLegislation;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionGroup.*;

/**
 * Created by vigi on 11/17/15:2:34 PM.
 */
@Getter
public enum ExclusionCriterion implements CcvCriterion {

    /**
     *
     */
    PARTICIPATION_CRIMINAL_ORGANISATION("005eb9ed-1347-4ca3-bb29-9bc0db64e1ab",
            "Participation in a criminal organisation",
            "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for participation in a criminal orgnisation, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 2 of Council Framework Decision 2008/841/JHA of 24 October 2008 on the fight against organised crime (OJ L 300, 11.11.2008, p. 42).",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference
            .DIRECTIVE_2014_24_EU_57_1, list(CRIMINAL_CONVICTION_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    CORRUPTION("c27b7c4e-c837-4529-b867-ed55ce639db5", "Corruption",
            "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for corruption, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 3 of the Convention on the fight against corruption involving officials of the European Communities or officials of Member States of the European Union, OJ C 195, 25.6.1997, p. 1, and in Article 2(1) of Council Framework Decision 2003/568/JHA of 22 July 2003 on combating corruption in the private sector (OJ L 192, 31.7.2003, p. 54). This exclusion ground also includes corruption as defined in the national law of the contracting authority (contracting entity) or the economic operator.",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            list(CRIMINAL_CONVICTION_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    FRAUD("297d2323-3ede-424e-94bc-a91561e6f320", "Fraud",
            "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for fraud, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? Within the meaning of Article 1 of the Convention on the protection of the European Communities' financial interests (OJ C 316, 27.11.1995, p. 48).",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            list(CRIMINAL_CONVICTION_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    TERRORIST_OFFENCES("d486fb70-86b3-4e75-97f2-0d71b5697c7d",
            "Terrorist offences or offences linked to terrorist activities",
            "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for terrorist offences or offences linked to terrorist activities, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Articles 1 and 3 of Council Framework Decision of 13 June 2002 on combating terrorism (OJ L 164, 22.6.2002, p. 3). This exclusion ground also includes inciting or aiding or abetting or attempting to commit an offence, as referred to in Article 4 of that Framework Decision.",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            list(CRIMINAL_CONVICTION_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    MONEY_LAUNDERING("47112079-6fec-47a3-988f-e561668c3aef", "Money laundering or terrorist financing",
            "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for money laundering or terrorist financing, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 1 of Directive 2005/60/EC of the European Parliament and of the Council of 26 October 2005 on the prevention of the use of the financial system for the purpose of money laundering and terrorist financing (OJ L 309, 25.11.2005, p. 15).",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            list(CRIMINAL_CONVICTION_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    CHILD_LABOUR("d789d01a-fe03-4ccd-9898-73f9cfa080d1", "Child labour and other forms of trafficking in human beings",
            "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for child labour and other forms of trafficking in human beings, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 2 of Directive 2011/36/EU of the European Parliament and of the Council of 5 April 2011 on preventing and combating trafficking in human beings and protecting its victims, and replacing Council Framework Decision 2002/629/JHA (OJ L 101, 15.4.2011, p. 1).",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            list(CRIMINAL_CONVICTION_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    PAYMENT_OF_TAXES("b61bbeb7-690e-4a40-bc68-d6d4ecfaa3d4", "Payment of taxes",
            "Has the economic operator breached its obligations relating to the payment of taxes, both in the country in which it is established and in Member State of the contracting authority or contracting entity if other than the country of establishment?",
            ExclusionCriterionTypeCode.PAYMENT_OF_TAXES, LegislationReference.DIRECTIVE_2014_24_EU_57_2,
            list(PAYMENT_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),

    /**
     *
     */
    PAYMENT_OF_SOCIAL_SECURITY("7d85e333-bbab-49c0-be8d-c36d71a72f5e", "Payment of social security contributions",
            "Has the economic operator breached its obligations relating to the payment social security contributions, both in the country in which it is established and in Member State of the contracting authority or contracting entity if other than the country of establishment?",
            ExclusionCriterionTypeCode.PAYMENT_OF_SOCIAL_SECURITY, LegislationReference.DIRECTIVE_2014_24_EU_57_2,
            list(PAYMENT_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL("a80ddb62-d25b-4e4e-ae22-3968460dc0a9",
            "Breaching of obligations in the fields of environmental, social and labour law",
            "Has the economic operator, to its knowledge, breached its obligations in the fields of environmental, social and labour law? As referred to for the purposes of this procurement in national law, in the relevant notice or the procurement documents or in Article 18(2) of Directive 2004/18/EU.",
            ExclusionCriterionTypeCode.ENVIRONMENTAL, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(ENVIRONMENTAL_MAIN_GROUP)),
    /**
     *
     */
    BANKRUPTCY("d3732c09-7d62-4edc-a172-241da6636e7c", "Bankruptcy",
            "Is the economic operator bankrupt? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract.",
            ExclusionCriterionTypeCode.BANKRUPTCY_INSOLVENCY, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(INSOLVENCY_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    INSOLVENCY("396f288a-e267-4c20-851a-ed4f7498f137", "Insolvency",
            "Is the economic operator the subject of insolvency or winding-up? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract.",
            ExclusionCriterionTypeCode.BANKRUPTCY_INSOLVENCY, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(INSOLVENCY_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    ARRANGEMENT_WITH_CREDITORS("68918c7a-f5bc-4a1a-a62f-ad8983600d48", "Arrangement with creditors",
            "Is the economic operator in arrangement with creditors? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract.",
            ExclusionCriterionTypeCode.BANKRUPTCY_INSOLVENCY, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(INSOLVENCY_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    ANALOGOUS_SITUATION("daffa2a9-9f8f-4568-8be8-7b8bf306d096",
            "Analogous situation like bankruptcy under national law",
            "Is the economic operator in in any analogous situation like bankruptcy arising from a similar procedure under national laws and regulations? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract.",
            ExclusionCriterionTypeCode.BANKRUPTCY_INSOLVENCY, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(INSOLVENCY_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    ASSETS_ADMINISTERED_BY_LIQUIDATOR("8fda202a-0c37-41bb-9d7d-de3f49edbfcb", "Assets being administered by liquidator",
            "Are the assets of the economic operator being administered by a liquidator or by the court?  This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract.",
            ExclusionCriterionTypeCode.BANKRUPTCY_INSOLVENCY, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(INSOLVENCY_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    BUSINESS_ACTIVITIES_SUSPENDED("166536e2-77f7-455c-b018-70582474e4f6", "Business activities are suspended",
            "Are the business activities of the economic operator suspended?  This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract.",
            ExclusionCriterionTypeCode.BANKRUPTCY_INSOLVENCY, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(INSOLVENCY_MAIN_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    GUILTY_OF_PROFESSIONAL_MISCONDUCT("514d3fde-1e3e-4dcd-b02a-9f984d5bbda3", "Guilty of grave professional misconduct",
            "Is the economic operator  guilty of grave professional misconduct? Where applicable, see definitions in national law, the relevant notice or the procurement documents.",
            ExclusionCriterionTypeCode.MISCONDUCT, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(MISCONDUCT_MAIN_GROUP)),
    /**
     *
     */
    CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE("b1b5ac18-f393-4280-9659-1367943c1a2e",
            "Conflict of interest due to its participation in the procurement procedure",
            "Is the economic operator aware of any conflict of interest, as indicated in national law, the relevant notice or the procurement documents due to its participation in the procurement procedure?",
            ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE("61874050-5130-4f1c-a174-720939c7b483",
            "Direct or indirect involvement in the preparation of this procurement procedure",
            "Has the economic operator or an undertaking related to it advised the contracting authority or contracting entity or otherwise been involved in the preparation of the procurement procedure?",
            ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE_GROUP)),

    /**
     *
     */
    EARLY_TERMINATION("3293e92b-7f3e-42f1-bee6-a7641bb04251",
            "Early termination, damages or other comparable sanctions",
            "Has the economic operator experienced that a prior public contract, a prior contract with a contracting entity or a prior concession contract was terminated early, or that damages or other comparable sanctions were imposed in connection with that prior contract?",
            ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(EARLY_TERMINATION_MAIN_GROUP)),
    /**
     *
     */
    GUILTY_OF_MISINTERPRETATION("696a75b2-6107-428f-8b74-82affb67e184",
            "Guilty of misinterpretation, withheld information, able to provide required documents and obtained confidential information of this procedure",
            "Can the economic operator confirm the four exclusion grounds, that it has not been guilty of serious misrepresentation in supplying the information required for the verification of the absence of grounds for exclusion or the fulfilment of the selection criteria, that it has not withheld such information, it has been able, without delay, to submit the supporting documents required by a contracting authority or contracting entity, and it has not undertaken to unduly influence the decision making process of the contracting authority or contracting entity, to obtain confidential information that may confer upon it undue advantages in the procurement procedure or to negligently provide misleading information that may have a material influence on decisions concerning exclusion, selection or award?",
            ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list(GUILTY_OF_MISINTERPRETATION_GROUP, INFO_ELECTRONICALLY_GROUP)),
    /**
     *
     */
    NATIONAL_EXCLUSION_GROUNDS("63adb07d-db1b-4ef0-a14e-a99785cf8cf6", "Purely national exclusion grounds",
            "Other exclusion grounds that may be foreseen in the national legislation of the contracting authority’s or contracting entity’s Member State. Do the purely national grounds of exclusion, which are specified in the relevant notice or in the procurement documents, apply?",
            ExclusionCriterionTypeCode.OTHER, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            list());

    private final String uuid;

    private final String shortName;

    private final String description;

    private final ExclusionCriterionTypeCode criterionTypeCode;

    private final LegislationReference legislationReference;

    private final List<? extends CcvCriterionGroup> groups;

    ExclusionCriterion(String uuid, String shortName, String description,
            ExclusionCriterionTypeCode criterionTypeCode, LegislationReference legislationReference,
            List<? extends CcvCriterionGroup> groups) {
        this.uuid = uuid;
        this.shortName = shortName;
        this.description = description;
        this.criterionTypeCode = criterionTypeCode;
        this.legislationReference = legislationReference;
        this.groups = groups;
    }

    @Override
    public String getTypeCode() {
        return CriterionType.EXCLUSION.name() + "." + getCriterionTypeCode().name();
    }

    @Override
    public String getName() {
        return getShortName();
    }

    @Override
    public CcvLegislation getLegislation() {
        return getLegislationReference();
    }

    private static List<ExclusionCriterionGroup> list(ExclusionCriterionGroup... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }
}
