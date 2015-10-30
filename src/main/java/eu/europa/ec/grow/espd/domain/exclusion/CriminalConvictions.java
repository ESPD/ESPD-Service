package eu.europa.ec.grow.espd.domain.exclusion;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import eu.europa.ec.grow.espd.domain.Criterion;

@Data
@XmlType(name = "CriminalConvictions")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper=false)
public class CriminalConvictions extends Criterion {

	@XmlElement private Date dateOfConviction;
	@XmlElement private String reason;
	@XmlElement private String convicted;
	@XmlElement private String periodLength;
	
}
