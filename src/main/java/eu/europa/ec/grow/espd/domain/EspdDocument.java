package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.domain.exclusion.BreachOfObligations;
import eu.europa.ec.grow.espd.domain.exclusion.CriminalConvictions;
import eu.europa.ec.grow.espd.domain.exclusion.Taxes;
import eu.europa.ec.grow.espd.domain.selection.SelectionCriterion;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.io.IOException;

@Data
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EspdDocument {

	@XmlTransient private String action;// "ca_create_espd" or "eo_import_espd"

	@XmlElement
    @NotNull(message = "Name is mandatory.")
	private String name;

	@XmlElement private String natRegNumber;
	@XmlElement private String streetAndNumber;
	@XmlElement private String postcode;
	@XmlElement private String city;
	@XmlElement private String country;
	@XmlElement private String contactPerson;
	@XmlElement private String email;
	@XmlElement private String telephone;
	@XmlElement private String website;

	@XmlElement private String procedureDesc;
	@XmlElement private String lotConcerned;
	@XmlElement private String fileRefByCA;
	@XmlElement private String websiteProcDocs;
	
	//Exclusions
	
	@XmlElement private CriminalConvictions criminalConvictions;
	@XmlElement private CriminalConvictions corruption;
	@XmlElement private CriminalConvictions fraud;
	@XmlElement private CriminalConvictions terroristOffences;
	@XmlElement private CriminalConvictions moneyLaundering;
	@XmlElement private CriminalConvictions childLabour;
	
	@XmlElement private Taxes paymentTaxes;
	@XmlElement private Taxes paymentSocsec;
	
	@XmlElement private BreachOfObligations breachingObligations;
	@XmlElement private BreachOfObligations bankruptSubject;
	@XmlElement private BreachOfObligations guiltyGrave;
	@XmlElement private BreachOfObligations agreementsEo;
	@XmlElement private BreachOfObligations conflictInterest;
	@XmlElement private BreachOfObligations involvementPreparation;
	@XmlElement private BreachOfObligations earlyTermination;
	@XmlElement private BreachOfObligations guiltyMisinterpretation;

    @XmlElement
    private SelectionCriterion suitabilityEnrolment;

    @XmlElement
    private SelectionCriterion suitabilityServiceContracts;

    @XmlElement
    private SelectionCriterion economicGeneralTurnover;

    @XmlElement
    private SelectionCriterion economicAverageTurnover;

    @XmlElement
    private SelectionCriterion economicEnrolment;

    @XmlElement
    private SelectionCriterion economicServiceContracts;

    @XmlElement
    private Criterion selectionSatisfiesAll;

	//trick to use MultipartFile as @RequestParam
	public void setAttachment(MultipartFile attachment) throws IOException, JAXBException {}
	public MultipartFile getAttachment() {return null;}

}
