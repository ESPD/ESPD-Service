package eu.europa.ec.grow.espd.domain.exclusion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import eu.europa.ec.grow.espd.domain.ExclusionCriterion;

@Data
@XmlType(name = "Taxes")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper=false)
public class Taxes extends ExclusionCriterion {

	@XmlElement private String country;
	@XmlElement private Integer amount;
	@XmlElement private String currency;
	@XmlElement private String periodLength;
	
}