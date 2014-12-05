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
import javax.xml.bind.annotation.XmlType;

import no.kith.xmlstds.emok.codes.CS;
import no.kith.xmlstds.emok.codes.URL;


/**
 * 
 *          Inneholder opplysninger om telekommunikasjonsadresse, inklusive kommunikasjonstype. Denne klassen benyttes for � registrere telefonnummer, telefaks, persons�ker etc., knyttes opp mot de registrerte adressene.
 *       
 * 
 * <p>Java class for TeleCom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TeleCom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TypeTelecom" type="{http://www.kith.no/xmlstds/msghead/2006-05-24}CS" minOccurs="0"/>
 *         &lt;element name="TeleAddress" type="{http://www.kith.no/xmlstds/msghead/2006-05-24}URL"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TeleCom", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24", propOrder = {
    "typeTelecom",
    "teleAddress"
})
public class TeleCom {

    @XmlElement(name = "TypeTelecom")
    protected CS typeTelecom;
    @XmlElement(name = "TeleAddress", required = true)
    protected URL teleAddress;

    /**
     * Gets the value of the typeTelecom property.
     * 
     * @return
     *     possible object is
     *     {@link CS }
     *     
     */
    public CS getTypeTelecom() {
        return typeTelecom;
    }

    /**
     * Sets the value of the typeTelecom property.
     * 
     * @param value
     *     allowed object is
     *     {@link CS }
     *     
     */
    public void setTypeTelecom(CS value) {
        this.typeTelecom = value;
    }

    /**
     * Gets the value of the teleAddress property.
     * 
     * @return
     *     possible object is
     *     {@link URL }
     *     
     */
    public URL getTeleAddress() {
        return teleAddress;
    }

    /**
     * Sets the value of the teleAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link URL }
     *     
     */
    public void setTeleAddress(URL value) {
        this.teleAddress = value;
    }

}