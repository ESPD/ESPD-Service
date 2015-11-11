//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.11 at 11:41:57 AM CET 
//


package oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.AttributeIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MaximumMeasureType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MeasureType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.MinimumMeasureType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for DimensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DimensionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}AttributeID"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}Measure" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}Description" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}MinimumMeasure" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}MaximumMeasure" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionType", propOrder = {
    "attributeID",
    "measure",
    "description",
    "minimumMeasure",
    "maximumMeasure"
})
public class DimensionType
    implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "AttributeID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2", required = true)
    protected AttributeIDType attributeID;
    @XmlElement(name = "Measure", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected MeasureType measure;
    @XmlElement(name = "Description", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<DescriptionType> description;
    @XmlElement(name = "MinimumMeasure", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected MinimumMeasureType minimumMeasure;
    @XmlElement(name = "MaximumMeasure", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected MaximumMeasureType maximumMeasure;

    /**
     * Gets the value of the attributeID property.
     * 
     * @return
     *     possible object is
     *     {@link AttributeIDType }
     *     
     */
    public AttributeIDType getAttributeID() {
        return attributeID;
    }

    /**
     * Sets the value of the attributeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributeIDType }
     *     
     */
    public void setAttributeID(AttributeIDType value) {
        this.attributeID = value;
    }

    /**
     * Gets the value of the measure property.
     * 
     * @return
     *     possible object is
     *     {@link MeasureType }
     *     
     */
    public MeasureType getMeasure() {
        return measure;
    }

    /**
     * Sets the value of the measure property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureType }
     *     
     */
    public void setMeasure(MeasureType value) {
        this.measure = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionType }
     * 
     * 
     */
    public List<DescriptionType> getDescription() {
        if (description == null) {
            description = new ArrayList<DescriptionType>();
        }
        return this.description;
    }

    /**
     * Gets the value of the minimumMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link MinimumMeasureType }
     *     
     */
    public MinimumMeasureType getMinimumMeasure() {
        return minimumMeasure;
    }

    /**
     * Sets the value of the minimumMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link MinimumMeasureType }
     *     
     */
    public void setMinimumMeasure(MinimumMeasureType value) {
        this.minimumMeasure = value;
    }

    /**
     * Gets the value of the maximumMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link MaximumMeasureType }
     *     
     */
    public MaximumMeasureType getMaximumMeasure() {
        return maximumMeasure;
    }

    /**
     * Sets the value of the maximumMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaximumMeasureType }
     *     
     */
    public void setMaximumMeasure(MaximumMeasureType value) {
        this.maximumMeasure = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            AttributeIDType theAttributeID;
            theAttributeID = this.getAttributeID();
            strategy.appendField(locator, this, "attributeID", buffer, theAttributeID);
        }
        {
            MeasureType theMeasure;
            theMeasure = this.getMeasure();
            strategy.appendField(locator, this, "measure", buffer, theMeasure);
        }
        {
            List<DescriptionType> theDescription;
            theDescription = (((this.description!= null)&&(!this.description.isEmpty()))?this.getDescription():null);
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            MinimumMeasureType theMinimumMeasure;
            theMinimumMeasure = this.getMinimumMeasure();
            strategy.appendField(locator, this, "minimumMeasure", buffer, theMinimumMeasure);
        }
        {
            MaximumMeasureType theMaximumMeasure;
            theMaximumMeasure = this.getMaximumMeasure();
            strategy.appendField(locator, this, "maximumMeasure", buffer, theMaximumMeasure);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DimensionType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DimensionType that = ((DimensionType) object);
        {
            AttributeIDType lhsAttributeID;
            lhsAttributeID = this.getAttributeID();
            AttributeIDType rhsAttributeID;
            rhsAttributeID = that.getAttributeID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "attributeID", lhsAttributeID), LocatorUtils.property(thatLocator, "attributeID", rhsAttributeID), lhsAttributeID, rhsAttributeID)) {
                return false;
            }
        }
        {
            MeasureType lhsMeasure;
            lhsMeasure = this.getMeasure();
            MeasureType rhsMeasure;
            rhsMeasure = that.getMeasure();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "measure", lhsMeasure), LocatorUtils.property(thatLocator, "measure", rhsMeasure), lhsMeasure, rhsMeasure)) {
                return false;
            }
        }
        {
            List<DescriptionType> lhsDescription;
            lhsDescription = (((this.description!= null)&&(!this.description.isEmpty()))?this.getDescription():null);
            List<DescriptionType> rhsDescription;
            rhsDescription = (((that.description!= null)&&(!that.description.isEmpty()))?that.getDescription():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            MinimumMeasureType lhsMinimumMeasure;
            lhsMinimumMeasure = this.getMinimumMeasure();
            MinimumMeasureType rhsMinimumMeasure;
            rhsMinimumMeasure = that.getMinimumMeasure();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumMeasure", lhsMinimumMeasure), LocatorUtils.property(thatLocator, "minimumMeasure", rhsMinimumMeasure), lhsMinimumMeasure, rhsMinimumMeasure)) {
                return false;
            }
        }
        {
            MaximumMeasureType lhsMaximumMeasure;
            lhsMaximumMeasure = this.getMaximumMeasure();
            MaximumMeasureType rhsMaximumMeasure;
            rhsMaximumMeasure = that.getMaximumMeasure();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maximumMeasure", lhsMaximumMeasure), LocatorUtils.property(thatLocator, "maximumMeasure", rhsMaximumMeasure), lhsMaximumMeasure, rhsMaximumMeasure)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            AttributeIDType theAttributeID;
            theAttributeID = this.getAttributeID();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "attributeID", theAttributeID), currentHashCode, theAttributeID);
        }
        {
            MeasureType theMeasure;
            theMeasure = this.getMeasure();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "measure", theMeasure), currentHashCode, theMeasure);
        }
        {
            List<DescriptionType> theDescription;
            theDescription = (((this.description!= null)&&(!this.description.isEmpty()))?this.getDescription():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            MinimumMeasureType theMinimumMeasure;
            theMinimumMeasure = this.getMinimumMeasure();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "minimumMeasure", theMinimumMeasure), currentHashCode, theMinimumMeasure);
        }
        {
            MaximumMeasureType theMaximumMeasure;
            theMaximumMeasure = this.getMaximumMeasure();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maximumMeasure", theMaximumMeasure), currentHashCode, theMaximumMeasure);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}