package eu.europa.ec.grow.espd.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlType(name = "Criterion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Criterion {

	@XmlElement private Boolean exists;
	
}
