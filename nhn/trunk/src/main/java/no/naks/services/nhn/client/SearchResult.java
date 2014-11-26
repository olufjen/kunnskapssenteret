
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SearchResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Facets" type="{http://register.nhn.no/CommunicationParty}ArrayOfSearchFacet" minOccurs="0"/>
 *         &lt;element name="Results" type="{http://register.nhn.no/CommunicationParty}ArrayOfCommunicationParty" minOccurs="0"/>
 *         &lt;element name="ServerTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TotalResults" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchResult", propOrder = {
    "facets",
    "results",
    "serverTime",
    "totalResults"
})
public class SearchResult {

    @XmlElementRef(name = "Facets", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfSearchFacet> facets;
    @XmlElementRef(name = "Results", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfCommunicationParty> results;
    @XmlElement(name = "ServerTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar serverTime;
    @XmlElement(name = "TotalResults")
    protected Integer totalResults;

    /**
     * Gets the value of the facets property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSearchFacet }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSearchFacet> getFacets() {
        return facets;
    }

    /**
     * Sets the value of the facets property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSearchFacet }{@code >}
     *     
     */
    public void setFacets(JAXBElement<ArrayOfSearchFacet> value) {
        this.facets = ((JAXBElement<ArrayOfSearchFacet> ) value);
    }

    /**
     * Gets the value of the results property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCommunicationParty }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCommunicationParty> getResults() {
        return results;
    }

    /**
     * Sets the value of the results property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCommunicationParty }{@code >}
     *     
     */
    public void setResults(JAXBElement<ArrayOfCommunicationParty> value) {
        this.results = ((JAXBElement<ArrayOfCommunicationParty> ) value);
    }

    /**
     * Gets the value of the serverTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getServerTime() {
        return serverTime;
    }

    /**
     * Sets the value of the serverTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setServerTime(XMLGregorianCalendar value) {
        this.serverTime = value;
    }

    /**
     * Gets the value of the totalResults property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * Sets the value of the totalResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalResults(Integer value) {
        this.totalResults = value;
    }

}
