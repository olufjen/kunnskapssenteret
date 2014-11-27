
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
 *         &lt;element name="search" type="{http://register.nhn.no/CommunicationParty}CommunicationPartySearch" minOccurs="0"/>
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
    "search"
})
@XmlRootElement(name = "Search")
public class Search {

    @XmlElementRef(name = "search", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<CommunicationPartySearch> search;

    /**
     * Gets the value of the search property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CommunicationPartySearch }{@code >}
     *     
     */
    public JAXBElement<CommunicationPartySearch> getSearch() {
        return search;
    }

    /**
     * Sets the value of the search property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CommunicationPartySearch }{@code >}
     *     
     */
    public void setSearch(JAXBElement<CommunicationPartySearch> value) {
        this.search = ((JAXBElement<CommunicationPartySearch> ) value);
    }

}
