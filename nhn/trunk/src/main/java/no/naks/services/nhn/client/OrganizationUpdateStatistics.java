
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrganizationUpdateStatistics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrganizationUpdateStatistics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Statistics" type="{http://register.nhn.no/CommunicationParty}ArrayOfUpdateStatistic" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationUpdateStatistics", propOrder = {
    "statistics"
})
public class OrganizationUpdateStatistics {

    @XmlElementRef(name = "Statistics", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfUpdateStatistic> statistics;

    /**
     * Gets the value of the statistics property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfUpdateStatistic }{@code >}
     *     
     */
    public JAXBElement<ArrayOfUpdateStatistic> getStatistics() {
        return statistics;
    }

    /**
     * Sets the value of the statistics property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfUpdateStatistic }{@code >}
     *     
     */
    public void setStatistics(JAXBElement<ArrayOfUpdateStatistic> value) {
        this.statistics = ((JAXBElement<ArrayOfUpdateStatistic> ) value);
    }

}
