package eu.europa.ec.grow.espd.domain.exclusion;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import eu.europa.ec.grow.espd.domain.ExclusionCriterion;

@Data
@XmlType(name = "BreachOfObligations")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper=false)
public class BreachOfObligations extends ExclusionCriterion {

	@XmlElement private Boolean isFinal;
	@XmlElement private Date dateOfConviction;
	@XmlElement private String periodLength;
	@XmlElement private String otherMeans;
	
	
}
