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
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.LocationIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.LocationType;
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
 * <p>Java class for StowageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StowageType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}LocationID" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2}Location" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2}MeasurementDimension" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StowageType", propOrder = {
    "locationID",
    "location",
    "measurementDimension"
})
public class StowageType
    implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "LocationID", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected LocationIDType locationID;
    @XmlElement(name = "Location", namespace = "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2")
    protected List<LocationType> location;
    @XmlElement(name = "MeasurementDimension")
    protected List<DimensionType> measurementDimension;

    /**
     * Gets the value of the locationID property.
     * 
     * @return
     *     possible object is
     *     {@link LocationIDType }
     *     
     */
    public LocationIDType getLocationID() {
        return locationID;
    }

    /**
     * Sets the value of the locationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationIDType }
     *     
     */
    public void setLocationID(LocationIDType value) {
        this.locationID = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the location property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocationType }
     * 
     * 
     */
    public List<LocationType> getLocation() {
        if (location == null) {
            location = new ArrayList<LocationType>();
        }
        return this.location;
    }

    /**
     * Gets the value of the measurementDimension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measurementDimension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasurementDimension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DimensionType }
     * 
     * 
     */
    public List<DimensionType> getMeasurementDimension() {
        if (measurementDimension == null) {
            measurementDimension = new ArrayList<DimensionType>();
        }
        return this.measurementDimension;
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
            LocationIDType theLocationID;
            theLocationID = this.getLocationID();
            strategy.appendField(locator, this, "locationID", buffer, theLocationID);
        }
        {
            List<LocationType> theLocation;
            theLocation = (((this.location!= null)&&(!this.location.isEmpty()))?this.getLocation():null);
            strategy.appendField(locator, this, "location", buffer, theLocation);
        }
        {
            List<DimensionType> theMeasurementDimension;
            theMeasurementDimension = (((this.measurementDimension!= null)&&(!this.measurementDimension.isEmpty()))?this.getMeasurementDimension():null);
            strategy.appendField(locator, this, "measurementDimension", buffer, theMeasurementDimension);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof StowageType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final StowageType that = ((StowageType) object);
        {
            LocationIDType lhsLocationID;
            lhsLocationID = this.getLocationID();
            LocationIDType rhsLocationID;
            rhsLocationID = that.getLocationID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locationID", lhsLocationID), LocatorUtils.property(thatLocator, "locationID", rhsLocationID), lhsLocationID, rhsLocationID)) {
                return false;
            }
        }
        {
            List<LocationType> lhsLocation;
            lhsLocation = (((this.location!= null)&&(!this.location.isEmpty()))?this.getLocation():null);
            List<LocationType> rhsLocation;
            rhsLocation = (((that.location!= null)&&(!that.location.isEmpty()))?that.getLocation():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "location", lhsLocation), LocatorUtils.property(thatLocator, "location", rhsLocation), lhsLocation, rhsLocation)) {
                return false;
            }
        }
        {
            List<DimensionType> lhsMeasurementDimension;
            lhsMeasurementDimension = (((this.measurementDimension!= null)&&(!this.measurementDimension.isEmpty()))?this.getMeasurementDimension():null);
            List<DimensionType> rhsMeasurementDimension;
            rhsMeasurementDimension = (((that.measurementDimension!= null)&&(!that.measurementDimension.isEmpty()))?that.getMeasurementDimension():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "measurementDimension", lhsMeasurementDimension), LocatorUtils.property(thatLocator, "measurementDimension", rhsMeasurementDimension), lhsMeasurementDimension, rhsMeasurementDimension)) {
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
            LocationIDType theLocationID;
            theLocationID = this.getLocationID();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "locationID", theLocationID), currentHashCode, theLocationID);
        }
        {
            List<LocationType> theLocation;
            theLocation = (((this.location!= null)&&(!this.location.isEmpty()))?this.getLocation():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "location", theLocation), currentHashCode, theLocation);
        }
        {
            List<DimensionType> theMeasurementDimension;
            theMeasurementDimension = (((this.measurementDimension!= null)&&(!this.measurementDimension.isEmpty()))?this.getMeasurementDimension():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "measurementDimension", theMeasurementDimension), currentHashCode, theMeasurementDimension);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}