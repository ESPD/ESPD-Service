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
	
}
