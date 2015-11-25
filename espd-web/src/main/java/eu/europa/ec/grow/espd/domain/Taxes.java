package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Taxes extends ExclusionCriterion {

    private Country country;
    private Integer amount;
    private String currency;
    private String periodLength;

    public static final Taxes buildWithExists(Boolean exists) {
        Taxes taxes = new Taxes();
        taxes.setExists(exists);
        return taxes;
    }

}