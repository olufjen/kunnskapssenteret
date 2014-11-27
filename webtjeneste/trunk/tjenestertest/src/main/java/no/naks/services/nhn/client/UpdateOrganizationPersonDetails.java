
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="p" type="{http://register.nhn.no/CommunicationParty}OrganizationPersonUpdate" minOccurs="0"/>
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
    "p"
})
@XmlRootElement(name = "UpdateOrganizationPersonDetails")
public class UpdateOrganizationPersonDetails {

    @XmlElementRef(name = "p", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<OrganizationPersonUpdate> p;

    /**
     * Gets the value of the p property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OrganizationPersonUpdate }{@code >}
     *     
     */
    public JAXBElement<OrganizationPersonUpdate> getP() {
        return p;
    }

    /**
     * Sets the value of the p property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OrganizationPersonUpdate }{@code >}
     *     
     */
    public void setP(JAXBElement<OrganizationPersonUpdate> value) {
        this.p = ((JAXBElement<OrganizationPersonUpdate> ) value);
    }

}
