package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurelyNationalGrounds extends ExclusionCriterion {

    public static final PurelyNationalGrounds buildWithExists(Boolean exists) {
        PurelyNationalGrounds criminalConvictions = new PurelyNationalGrounds();
        criminalConvictions.setExists(exists);
        return criminalConvictions;
    }
}
