package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;

import java.util.Date;

/**
 * Created by ratoico on 1/19/16 at 11:56 AM.
 */
@Data
public class EconomicOperatorRepresentative {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String placeOfBirth;

    private String street;

    private String postalCode;

    private String city;

    private Country country;

    private String email;

    private String phone;

    /**
     * Position/Acting in the capacity of
     */
    private String position;

    /**
     * If needed, please provide detailed information on the representation (its forms, extent, purpose ...)
     */
    private String additionalInfo;
}
