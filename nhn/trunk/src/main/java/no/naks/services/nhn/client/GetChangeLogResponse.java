
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
 *         &lt;element name="GetChangeLogResult" type="{http://register.nhn.no/Common}ArrayOfEntityLogEntry" minOccurs="0"/>
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
    "getChangeLogResult"
})
@XmlRootElement(name = "GetChangeLogResponse")
public class GetChangeLogResponse {

    @XmlElementRef(name = "GetChangeLogResult", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfEntityLogEntry> getChangeLogResult;

    /**
     * Gets the value of the getChangeLogResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntityLogEntry }{@code >}
     *     
     */
    public JAXBElement<ArrayOfEntityLogEntry> getGetChangeLogResult() {
        return getChangeLogResult;
    }

    /**
     * Sets the value of the getChangeLogResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntityLogEntry }{@code >}
     *     
     */
    public void setGetChangeLogResult(JAXBElement<ArrayOfEntityLogEntry> value) {
        this.getChangeLogResult = ((JAXBElement<ArrayOfEntityLogEntry> ) value);
    }

}