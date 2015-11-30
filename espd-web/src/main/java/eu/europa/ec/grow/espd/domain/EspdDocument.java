package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Data
public class EspdDocument {

    private String action;// "ca_create_espd" or "eo_import_espd"

    private PartyImpl authority;

    private String procedureDesc;
    private String lotConcerned;
    private String fileRefByCA;
    private String websiteProcDocs;

    //Exclusions

    private CriminalConvictions criminalConvictions;
    private CriminalConvictions corruption;
    private CriminalConvictions fraud;
    private CriminalConvictions terroristOffences;
    private CriminalConvictions moneyLaundering;
    private CriminalConvictions childLabour;

    private Taxes paymentTaxes;
    private Taxes paymentSocsec;

    private BreachOfObligations breachingObligations;
    private BreachOfObligations bankruptcy;
    private BreachOfObligations insolvency;
    private BreachOfObligations arrangementWithCreditors;
    private BreachOfObligations analogousSituation;
    private BreachOfObligations assetsAdministeredByLiquidator;
    private BreachOfObligations guiltyGrave;
    private BreachOfObligations agreementsEo;
    private BreachOfObligations conflictInterest;
    private BreachOfObligations involvementPreparation;
    private BreachOfObligations earlyTermination;
    private BreachOfObligations guiltyMisinterpretation;

    private Criterion purelyNationalGrounds;

    private SelectionCriterion suitabilityEnrolment;

    private SelectionCriterion suitabilityServiceContracts;

    private SelectionCriterion economicGeneralTurnover;

    private SelectionCriterion economicAverageTurnover;

    private SelectionCriterion economicEnrolment;

    private SelectionCriterion economicServiceContracts;

    private Criterion selectionSatisfiesAll;

    //trick to use MultipartFile as @RequestParam
    public void setAttachment(MultipartFile attachment) throws IOException, JAXBException {
    }

    public MultipartFile getAttachment() {
        return null;
    }

    public final boolean satisfiesAllCriteria() {
        return getSelectionSatisfiesAll() != null && getSelectionSatisfiesAll().getExists();
    }
}
