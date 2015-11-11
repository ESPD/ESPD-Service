package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@Data
@XmlType(name = "CriminalConvictions")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper=false)
public class CriminalConvictions extends ExclusionCriterion {

	@XmlElement private Date dateOfConviction;
	@XmlElement private String reason;
	@XmlElement private String convicted;
	@XmlElement private String periodLength;
	
	
	
}
