package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterion;
import eu.europa.ec.grow.espd.entities.CcvLegislation;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * Created by vigi on 11/17/15:2:34 PM.
 */
@Getter
public enum ExclusionCriterion implements CcvCriterion {

    /**
     *
     */
    GROUNDS_CRIMINAL_CONVICTIONS("005eb9ed-1347-4ca3-bb29-9bc0db64e1ab", "Grounds relating to criminal convictions",
            "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for criminal conviction, such as participation in a criminal organisation, as defined in Article 2 of Council Framework Decision 2008/841/JHA?",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference
            .DIRECTIVE_2014_24_EU_57_1, CriterionRequirement.CRIMINAL_CONVICTIONS_REQUIREMENTS),
    /**
     *
     */
    CORRUPTION("c27b7c4e-c837-4529-b867-ed55ce639db5", "Corruption",
            "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for corruption, as defined in Article 3 of the Convention on the fight against corruption involving officials of the European Communities or officials of Member States of the European Union and Article 2(1) of Council Framework Decision 2003/568/JHA, as well as corruption as defined in the national law of the contracting authority or the economic operator?",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            CriterionRequirement.CORRUPTION_REQUIREMENTS),
    /**
     *
     */
    FRAUD("297d2323-3ede-424e-94bc-a91561e6f320", "Fraud",
            "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for fraud within the meaning of Article 1 of the Convention on the protection of the European Communities'' financial interests?",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            CriterionRequirement.FRAUD_REQUIREMENTS),
    /**
     *
     */
    TERRORIST_OFFENCES("d486fb70-86b3-4e75-97f2-0d71b5697c7d",
            "Terrorist offences or offences linked to terrorist activities",
            "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for terrorist offences or offences linked to terrorist activities, as defined in Articles 1 and 3 of Council Framework Decision 2002/475/JHA respectively, or inciting or aiding or abetting or attempting to commit an offence, as referred to in Article 4 of that Framework Decision?",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            CriterionRequirement.TERRORIST_OFFENCES_REQUIREMENTS),
    /**
     *
     */
    MONEY_LAUNDERING("47112079-6fec-47a3-988f-e561668c3aef", "Money laundering or terrorist financing",
            "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for Money laundering or terrorist financing, as defined in Article 1 of Directive 2005/60/EC of the European Parliament and of the Council?",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            CriterionRequirement.MONEY_LAUNDERING_REQUIREMENTS),
    /**
     *
     */
    CHILD_LABOUR("d789d01a-fe03-4ccd-9898-73f9cfa080d1", "Child labour and other forms of trafficking in human beings",
            "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for child labour and other forms of trafficking in human beings as defined in Article 2 of Directive 2011/36/EU of the European Parliament and of the Council?",
            ExclusionCriterionTypeCode.CRIMINAL_CONVICTIONS, LegislationReference.DIRECTIVE_2014_24_EU_57_1,
            CriterionRequirement.CHILD_LABOUR_REQUIREMENTS),
    /**
     *
     */
    PAYMENT_OF_TAXES("b61bbeb7-690e-4a40-bc68-d6d4ecfaa3d4", "Payment of taxes",
            "Has it been established by a judicial or administrative decision having final and binding effect in accordance with the legal provisions in the country in which you are established or in the Member State of the contracting authority, that your organisation is in breach of obligations related to the payment of tax contributions?",
            ExclusionCriterionTypeCode.PAYMENT_OF_TAXES, LegislationReference.DIRECTIVE_2014_24_EU_57_2,
            CriterionRequirement.PAYMENT_TAXES_REQUIREMENTS),

    /**
     *
     */
    PAYMENT_OF_SOCIAL_SECURITY("7d85e333-bbab-49c0-be8d-c36d71a72f5e", "Payment of social security contributions",
            "Has it been established by a judicial or administrative decision having final and binding effect in accordance with the legal provisions in the country in which you are established or in the Member State of the contracting authority, that your organisation is in breach of obligations related to the payment of social security contributions?",
            ExclusionCriterionTypeCode.PAYMENT_OF_SOCIAL_SECURITY, LegislationReference.DIRECTIVE_2014_24_EU_57_2,
            CriterionRequirement.PAYMENT_SOCIAL_SECURITY_REQUIREMENTS),
    /**
     *
     */
    BREACHING_OF_OBLIGATIONS("a80ddb62-d25b-4e4e-ae22-3968460dc0a9",
            "Breaching of obligations in the fields of environmental, social and labour law",
            "Has the economic operator, to its knowledge, breached its obligations in the fields of environmental, social and labour law? As referred to for the purposes of this procurement in national law, in the relevant notice or the procurement documents or in Article 18(2) of Directive 2004/18/EU?",
            ExclusionCriterionTypeCode.ENVIRONMENTAL, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            CriterionRequirement.BREACH_OBLIGATIONS_ENV_REQUIREMENTS),
    /**
     *
     */
    BANKRUPTCY_INSOLVENCY("d3732c09-7d62-4edc-a172-241da6636e7c", "Bankrupt, the subject of insolvency or winding-up",
            "Is the economic operator in any of the following situations: Bankrupt, or the subject of insolvency or winding-up proceedings, or in an arrangement with creditors, or in any analogous situation arising from a similar procedure under national laws and regulations. See national law, the relevant notice or the procurement documents, or that its assets are being administered by a liquidator or by the court, or that its business activities are suspended?",
            ExclusionCriterionTypeCode.INSOLVENCY, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            CriterionRequirement.BANKRUPTCY_REQUIREMENTS),
    /**
     *
     */
    GUILTY_OF_PROFESSIONAL_MISCONDUCT("396f288a-e267-4c20-851a-ed4f7498f137", "Guilty of grave professional misconduct",
            "Is the economic operator  guilty of grave professional misconduct? Where applicable, see definitions in national law, the relevant notice or the procurement documents.",
            ExclusionCriterionTypeCode.MISCONDUCT, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            CriterionRequirement.PROFESSIONAL_MISCONDUCT_REQUIREMENTS),
    /**
     *
     */
    AGREEMENTS_WITH_OTHER_EO("68918c7a-f5bc-4a1a-a62f-ad8983600d48",
            "Agreements with other economic operators aimed at distorting competition",
            "Has the economic operator entered into agreements with other economic operators aimed at distorting competition?",
            ExclusionCriterionTypeCode.MISCONDUCT, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            CriterionRequirement.AGREEMENT_WITH_EO_REQUIREMENTS),
    /**
     *
     */
    CONFLICT_OF_INTEREST("b1b5ac18-f393-4280-9659-1367943c1a2e",
            "Conflict of interest due to its participation in the procurement procedure",
            "Is the economic operator aware of any conflict of interest, as indicated in national law, the relevant notice or the procurement documents due to its participation in the procurement procedure?",
            ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            CriterionRequirement.CONFLICT_OF_INTEREST_REQUIREMENTS),
    /**
     *
     */
    INVOLVEMENT_PROCUREMENT_PROCEDURE("61874050-5130-4f1c-a174-720939c7b483",
            "Direct or indirect involvement in the preparation of this procurement procedure",
            "Has the economic operator or an undertaking related to it advised the contracting authority or contracting entity or otherwise been involved in the preparation of the procurement procedure?",
            ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            CriterionRequirement.INVOLVEMENT_PROCUREMENT_PROCEDURE_REQUIREMENTS),
    /**
     *
     */
    EARLY_TERMINATION("3293e92b-7f3e-42f1-bee6-a7641bb04251",
            "Early termination, damages or other comparable sanctions",
            "Has the economic operator experienced that a prior public contract, a prior contract with a contracting entity or a prior concession contract was terminated early, or that damages or other comparable sanctions were imposed in connection with that prior contract?",
            ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            CriterionRequirement.EARLY_TERMINATION_REQUIREMENTS),
    /**
     *
     */
    GUILTY_OF_MISINTERPRETATION("696a75b2-6107-428f-8b74-82affb67e184",
            "Guilty of misinterpretation, withheld information, [...]",
            "Can the economic operator confirm that it has not been guilty of serious misrepresentation in supplying the information required for the verification of the absence of grounds for exclusion or the fulfilment of the selection criteria, it has not withheld such information, it has been able, without delay, to submit the supporting documents required by a contracting authority or contracting entity, and it has not undertaken to unduly influence the decision making process of the contracting authority or contracting entity, to obtain confidential information that may confer upon it undue advantages in the procurement procedure or to negligently provide misleading information that may have a material influence on decisions concerning exclusion, selection or award?",
            ExclusionCriterionTypeCode.CONFLICT_OF_INTEREST, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            CriterionRequirement.GUILTY_OF_MISINTERPRETATION_REQUIREMENTS),
    /**
     *
     */
    NATIONAL_EXCLUSION_GROUNDS("df81025e-6aa0-4665-8807-ee317e5f928e", "Purely national exclusion grounds",
            "Do the purely national grounds of exclusion, which are specified in the relevant notice or in the procurement documents, apply?",
            ExclusionCriterionTypeCode.OTHER, LegislationReference.DIRECTIVE_2014_24_EU_57_4,
            Collections.<CriterionRequirement>emptyList());

    private final String uuid;

    private final String shortName;

    private final String description;

    private final ExclusionCriterionTypeCode criterionTypeCode;

    private final LegislationReference legislationReference;

    private final List<CriterionRequirement> requirements;

    ExclusionCriterion(String uuid, String shortName, String description,
            ExclusionCriterionTypeCode criterionTypeCode, LegislationReference legislationReference,
            List<CriterionRequirement> requirements) {
        this.uuid = uuid;
        this.shortName = shortName;
        this.description = description;
        this.criterionTypeCode = criterionTypeCode;
        this.legislationReference = legislationReference;
        this.requirements = requirements;
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
}
