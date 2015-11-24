package eu.europa.ec.grow.espd.domain;

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

}
