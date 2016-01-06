package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CriminalConvictionsCriterion extends ExclusionCriterion implements ConvictionHolder {

    private Date dateOfConviction;
    private String reason;
    private String convicted;
    private String periodLength;

    public static final CriminalConvictionsCriterion buildWithExists(Boolean exists) {
        CriminalConvictionsCriterion criminalConvictions = new CriminalConvictionsCriterion();
        criminalConvictions.setExists(exists);
        return criminalConvictions;
    }
}
