package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CriminalConvictions extends ExclusionCriterion {

    private Date dateOfConviction;
    private String reason;
    private String convicted;
    private String periodLength;

    public static final CriminalConvictions buildWithExists(Boolean exists) {
        CriminalConvictions criminalConvictions = new CriminalConvictions();
        criminalConvictions.setExists(exists);
        return criminalConvictions;
    }

    @Override
    public Country getCountry() {
        throw new UnsupportedOperationException("Criminal convictions criterion does not have a country.");
    }

    @Override
    public Integer getAmount() {
        throw new UnsupportedOperationException("Criminal convictions criterion does not have an amount.");
    }

    @Override
    public String getCurrency() {
        throw new UnsupportedOperationException("Criminal convictions criterion does not have a currency.");
    }

}
