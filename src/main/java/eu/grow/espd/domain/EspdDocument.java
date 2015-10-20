package eu.grow.espd.domain;

import lombok.Data;

@Data
public class EspdDocument {

	private String languageCode;
	private String name;
	private String natRegNumber;
	private String streetAndNumber;
	private String postcode;
	private String city;
	private String country;
	private String contactPerson;
	private String email;
	private String telephone;
	private String website;

	private String procedureDesc;
	private String lotConcerned;
	private String fileRefByCA;
	private String websiteProcDocs;
	
	//Exclusions
	
	private Boolean criminal_convictions;
	private Boolean corruption;
	private Boolean fraud;
	private Boolean terrorist_offences;
	private Boolean money_laundering;
	private Boolean child_labour;
	
	private Boolean payment_taxes;
	private Boolean payment_socsec;
	
	private Boolean breaching_obligations;
	private Boolean bankrupt_subject;
	private Boolean guilty_grave;
	private Boolean agreements_eo;
	private Boolean conflict_interest;
	private Boolean involvement_preparation;
	private Boolean early_termination;
	private Boolean guilty_misinterpretation;
	
	
	
}
