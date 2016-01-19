package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.entities.CacParty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class EconomicOperatorImpl extends PartyImpl {
	
	//private String contactPerson;//Contact person or persons: (for the moment one field)

	private Boolean isSmallSizedEnterprise;//Is the economic operator a Micro, a Small or a Medium-Sized Enterprise ?
	private Boolean isShelteredWorkshop;//Only in case the procurement is reserved: is the economic operator a sheltered workshop, a 'social business' or will it provide for the performance of the contract in the context of sheltered employment programmes?
	private Integer percentageDisabledWorkers;//What is the corresponding percentage of disabled or disadvantaged workers?
	private String detailsDisabledWorkers; //If required, please provide details on whether the employees concerned belong to a specific category of disabled or disadvantaged workers?

	
	private Boolean isEORegisteredOfficially; //If applicable, is the economic operator registered on an official list of approved economic operators or does it have an equivalent certificate (e.g. under a national (pre)qualification system)?
	//if yes
	private String certNumber;		// Please provide the relevant registration or certification number, if applicable:
	private String certificateUrl;	// If the certificate of registration or certification is available electronically, please state: 
	private String certRefBasis;	// Please state the references on which the registration or certification is based, and, where applicable, the classification obtained in the official list:
	private Boolean certCoversAllCrit;	// Does the registration or certification cover all of the required selection criteria? 
	//if no
	private String socialSecPaymentDoc;//Will the economic operator be able to provide a certificate with regard to the payment of social security contributions and taxes or provide information enabling the contracting authority or contracting entity to obtaining it directly by accessing a national database in any Member State that is available free of charge?
	private String socialSecPaymentDocUrl;//If the relevant documentation is available electronically, please indicate:
			

	private Boolean isEOInGroup;//Is the economic operator participating in the procurement procedure together with others?	
	//if yes
	private String eoRoleInGroup;//Please indicate the role of the economic operator in the group (leader, responsible for specific tasks...)
	private String otherEOInGroup;//Please identify the other economic operators participating in the procurement procedure together:
	private String eoGroupName;// Where applicable, name of the participating group:

    private EconomicOperatorRepresentative representative;

	private Boolean isEORelyOnEntities;//Does the economic operator rely on the capacities of other entities in order to meet the selection criteria set out under Part IV and the criteria and rules (if any) set out under Part V below?

    public void copyProperties(CacParty fromParty) {
        setName(fromParty.getName());
        setWebsite(fromParty.getWebsite());
        setVatNumber(fromParty.getVatNumber());
        setAnotherNationalId(fromParty.getAnotherNationalId());
        setStreet(fromParty.getStreet());
        setPostalCode(fromParty.getPostalCode());
        setCity(fromParty.getCity());
        setCountry((Country) fromParty.getCountry());
        setContactName(fromParty.getContactName());
        setContactPhone(fromParty.getContactPhone());
        setContactEmail(fromParty.getContactEmail());
    }

}
