package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType(name = "ExclusionCriterion")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper=false)
public class ExclusionCriterion extends Criterion {
	
	@XmlElement private AvailableElectronically availableElectronically;
	@XmlElement private SelfCleaning selfCleaning;
	@XmlElement private BreachOfObligations breachOfObligations;
}
