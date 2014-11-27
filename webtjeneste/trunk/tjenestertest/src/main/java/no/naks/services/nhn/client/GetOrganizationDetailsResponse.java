
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
 *         &lt;element name="GetOrganizationDetailsResult" type="{http://register.nhn.no/CommunicationParty}Organization" minOccurs="0"/>
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
    "getOrganizationDetailsResult"
})
@XmlRootElement(name = "GetOrganizationDetailsResponse")
public class GetOrganizationDetailsResponse {

    @XmlElementRef(name = "GetOrganizationDetailsResult", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Organization> getOrganizationDetailsResult;

    /**
     * Gets the value of the getOrganizationDetailsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Organization }{@code >}
     *     
     */
    public JAXBElement<Organization> getGetOrganizationDetailsResult() {
        return getOrganizationDetailsResult;
    }

    /**
     * Sets the value of the getOrganizationDetailsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Organization }{@code >}
     *     
     */
    public void setGetOrganizationDetailsResult(JAXBElement<Organization> value) {
        this.getOrganizationDetailsResult = ((JAXBElement<Organization> ) value);
    }

}
