package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

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
