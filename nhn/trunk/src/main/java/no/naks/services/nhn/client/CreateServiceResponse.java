
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
 *         &lt;element name="CreateServiceResult" type="{http://register.nhn.no/CommunicationParty}Service" minOccurs="0"/>
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
    "createServiceResult"
})
@XmlRootElement(name = "CreateServiceResponse")
public class CreateServiceResponse {

    @XmlElementRef(name = "CreateServiceResult", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Service> createServiceResult;

    /**
     * Gets the value of the createServiceResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Service }{@code >}
     *     
     */
    public JAXBElement<Service> getCreateServiceResult() {
        return createServiceResult;
    }

    /**
     * Sets the value of the createServiceResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Service }{@code >}
     *     
     */
    public void setCreateServiceResult(JAXBElement<Service> value) {
        this.createServiceResult = ((JAXBElement<Service> ) value);
    }

}
