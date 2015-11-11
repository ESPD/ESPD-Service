//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.11 at 11:41:57 AM CET 
//


package grow.names.specification.ubl.schema.xsd.espdrequest_1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the grow.names.specification.ubl.schema.xsd.espdrequest_1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ESPDRequest_QNAME = new QName("urn:grow:names:specification:ubl:schema:xsd:ESPDRequest-1", "ESPDRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: grow.names.specification.ubl.schema.xsd.espdrequest_1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ESPDRequestType }
     * 
     */
    public ESPDRequestType createESPDRequestType() {
        return new ESPDRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ESPDRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:grow:names:specification:ubl:schema:xsd:ESPDRequest-1", name = "ESPDRequest")
    public JAXBElement<ESPDRequestType> createESPDRequest(ESPDRequestType value) {
        return new JAXBElement<ESPDRequestType>(_ESPDRequest_QNAME, ESPDRequestType.class, null, value);
    }

}