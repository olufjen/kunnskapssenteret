
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
 *         &lt;element name="p" type="{http://register.nhn.no/CommunicationParty}OrganizationPersonCreate" minOccurs="0"/>
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
@XmlRootElement(name = "CreateOrganizationPerson")
public class CreateOrganizationPerson {

    @XmlElementRef(name = "p", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<OrganizationPersonCreate> p;

    /**
     * Gets the value of the p property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OrganizationPersonCreate }{@code >}
     *     
     */
    public JAXBElement<OrganizationPersonCreate> getP() {
        return p;
    }

    /**
     * Sets the value of the p property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OrganizationPersonCreate }{@code >}
     *     
     */
    public void setP(JAXBElement<OrganizationPersonCreate> value) {
        this.p = ((JAXBElement<OrganizationPersonCreate> ) value);
    }

}
