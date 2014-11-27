
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommunicationPartyStatistics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommunicationPartyStatistics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessTypeStatistics" type="{http://register.nhn.no/CommunicationParty}ArrayOfBusinessTypeStatistics" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommunicationPartyStatistics", propOrder = {
    "businessTypeStatistics"
})
public class CommunicationPartyStatistics {

    @XmlElementRef(name = "BusinessTypeStatistics", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfBusinessTypeStatistics> businessTypeStatistics;

    /**
     * Gets the value of the businessTypeStatistics property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBusinessTypeStatistics }{@code >}
     *     
     */
    public JAXBElement<ArrayOfBusinessTypeStatistics> getBusinessTypeStatistics() {
        return businessTypeStatistics;
    }

    /**
     * Sets the value of the businessTypeStatistics property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfBusinessTypeStatistics }{@code >}
     *     
     */
    public void setBusinessTypeStatistics(JAXBElement<ArrayOfBusinessTypeStatistics> value) {
        this.businessTypeStatistics = ((JAXBElement<ArrayOfBusinessTypeStatistics> ) value);
    }

}
