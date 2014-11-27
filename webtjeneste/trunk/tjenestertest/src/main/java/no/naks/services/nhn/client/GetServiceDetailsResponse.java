
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
 *         &lt;element name="GetServiceDetailsResult" type="{http://register.nhn.no/CommunicationParty}Service" minOccurs="0"/>
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
    "getServiceDetailsResult"
})
@XmlRootElement(name = "GetServiceDetailsResponse")
public class GetServiceDetailsResponse {

    @XmlElementRef(name = "GetServiceDetailsResult", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Service> getServiceDetailsResult;

    /**
     * Gets the value of the getServiceDetailsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Service }{@code >}
     *     
     */
    public JAXBElement<Service> getGetServiceDetailsResult() {
        return getServiceDetailsResult;
    }

    /**
     * Sets the value of the getServiceDetailsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Service }{@code >}
     *     
     */
    public void setGetServiceDetailsResult(JAXBElement<Service> value) {
        this.getServiceDetailsResult = ((JAXBElement<Service> ) value);
    }

}
