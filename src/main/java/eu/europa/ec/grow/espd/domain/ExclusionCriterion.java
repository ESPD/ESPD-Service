package eu.europa.ec.grow.espd.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@XmlType(name = "ExclusionCriterion")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper=false)
public class ExclusionCriterion extends Criterion {
	
	@XmlElement private AvaliableElectronically avaliableElectronically;
	@XmlElement private SelfCleaning selfCleaning;
	@XmlElement private BreachOfObligations breachOfObligations;
}
