
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchFacet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchFacet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Entries" type="{http://register.nhn.no/CommunicationParty}ArrayOfSearchResultFacetEntry" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchFacet", propOrder = {
    "codeValue",
    "entries",
    "name"
})
public class SearchFacet {

    @XmlElementRef(name = "CodeValue", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<String> codeValue;
    @XmlElementRef(name = "Entries", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfSearchResultFacetEntry> entries;
    @XmlElementRef(name = "Name", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<String> name;

    /**
     * Gets the value of the codeValue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodeValue() {
        return codeValue;
    }

    /**
     * Sets the value of the codeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodeValue(JAXBElement<String> value) {
        this.codeValue = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the entries property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSearchResultFacetEntry }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSearchResultFacetEntry> getEntries() {
        return entries;
    }

    /**
     * Sets the value of the entries property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSearchResultFacetEntry }{@code >}
     *     
     */
    public void setEntries(JAXBElement<ArrayOfSearchResultFacetEntry> value) {
        this.entries = ((JAXBElement<ArrayOfSearchResultFacetEntry> ) value);
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setName(JAXBElement<String> value) {
        this.name = ((JAXBElement<String> ) value);
    }

}
