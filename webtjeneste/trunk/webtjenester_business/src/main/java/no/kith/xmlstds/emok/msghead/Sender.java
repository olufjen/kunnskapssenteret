//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.29 at 10:55:06 AM CEST 
//


package no.kith.xmlstds.emok.msghead;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import no.kith.xmlstds.emok.codes.CS;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ComMethod" type="{http://www.kith.no/xmlstds/msghead/2006-05-24}CS" minOccurs="0"/>
 *         &lt;element ref="{http://www.kith.no/xmlstds/msghead/2006-05-24}Organisation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "comMethod",
    "organisation"
})
@XmlRootElement(name = "Sender", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
public class Sender {

    @XmlElement(name = "ComMethod", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected CS comMethod;
    @XmlElement(name = "Organisation", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24", required = true)
    protected Organisation organisation;

    /**
     * Gets the value of the comMethod property.
     * 
     * @return
     *     possible object is
     *     {@link CS }
     *     
     */
    public CS getComMethod() {
        return comMethod;
    }

    /**
     * Sets the value of the comMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CS }
     *     
     */
    public void setComMethod(CS value) {
        this.comMethod = value;
    }

    /**
     * Gets the value of the organisation property.
     * 
     * @return
     *     possible object is
     *     {@link Organisation }
     *     
     */
    public Organisation getOrganisation() {
        return organisation;
    }

    /**
     * Sets the value of the organisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Organisation }
     *     
     */
    public void setOrganisation(Organisation value) {
        this.organisation = value;
    }

}