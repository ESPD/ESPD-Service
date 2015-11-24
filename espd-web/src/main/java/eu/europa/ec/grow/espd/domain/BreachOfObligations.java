package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class BreachOfObligations extends ExclusionCriterion {

    private Boolean isFinal;
    private Date dateOfConviction;
    private String periodLength;
    private String otherMeans;

}
