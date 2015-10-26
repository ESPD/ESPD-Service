package eu.europa.ec.grow.espd.domain;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;

import org.springframework.web.multipart.MultipartFile;

@Data
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EspdDocument {

	@XmlTransient private String action;
	@XmlTransient private String agent;// "ca" or "eo"

	 @XmlElement private String name;
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
	
	 @XmlElement private Boolean criminalConvictions;
	 @XmlElement private Boolean corruption;
	 @XmlElement private Boolean fraud;
	 @XmlElement private Boolean terroristOffences;
	 @XmlElement private Boolean moneyLaundering;
	 @XmlElement private Boolean childLabour;
	
	 @XmlElement private Boolean paymentTaxes;
	 @XmlElement private Boolean paymentSocsec;
	
	 @XmlElement private Boolean breachingObligations;
	 @XmlElement private Boolean bankruptSubject;
	 @XmlElement private Boolean guiltyGrave;
	 @XmlElement private Boolean agreementsEo;
	 @XmlElement private Boolean conflictInterest;
	 @XmlElement private Boolean involvementPreparation;
	 @XmlElement private Boolean earlyTermination;
	 @XmlElement private Boolean guiltyMisinterpretation;

	public void setAttachment(MultipartFile attachment) throws IOException, JAXBException {}
	public MultipartFile getAttachment() {return null;}

	public Boolean getIsCA() {
		return "ca".equals(agent);
	}
	
	public Boolean getIsEO() {
		return "eo".equals(agent);
	}

}
