package eu.europa.ec.grow.espd.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType(name = "Criterion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Criterion {

	@XmlElement private Boolean exists;
	
}
