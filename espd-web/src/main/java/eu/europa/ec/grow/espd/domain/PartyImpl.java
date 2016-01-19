package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.entities.CacParty;
import lombok.Data;

/**
 * Created by vigi on 11/19/15:2:27 PM.
 */
@Data
public class PartyImpl implements CacParty {

    private String name;
    
    private String vatNumber;

    private String anotherNationalId;//If no VAT-number is applicable, please indicate another national identification number, if required and applicable

    private String website;

    private String street;

    private String postalCode;

    private String city;

    private Country country;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

}
