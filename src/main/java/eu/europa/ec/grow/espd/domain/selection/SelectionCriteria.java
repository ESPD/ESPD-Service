package eu.europa.ec.grow.espd.domain.selection;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by vigi on 11/3/15:2:57 PM.
 */
@Data
@XmlType(name = "SelectionCriteria")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode(callSuper=false)
public class SelectionCriteria {

    @XmlElement
    private SelectionCriterion satisfiesAll;

    @XmlElement
    private SelectionCriterion suitabilityEnrolment;

    @XmlElement
    private SelectionCriterion suitabilityServiceContracts;

    @XmlElement
    private SelectionCriterion economicGeneralTurnover;

    @XmlElement
    private SelectionCriterion economicAverageTurnover;

    @XmlElement
    private SelectionCriterion economicEnrolment;

    @XmlElement
    private SelectionCriterion economicServiceContracts;
}
