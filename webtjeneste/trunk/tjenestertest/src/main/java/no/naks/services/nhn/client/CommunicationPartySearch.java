
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommunicationPartySearch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommunicationPartySearch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Page" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PageSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SearchConstraints" type="{http://schemas.datacontract.org/2004/07/System.Collections.Generic}ArrayOfKeyValuePairOfstringstring" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommunicationPartySearch", propOrder = {
    "page",
    "pageSize",
    "searchConstraints"
})
public class CommunicationPartySearch {

    @XmlElement(name = "Page")
    protected Integer page;
    @XmlElement(name = "PageSize")
    protected Integer pageSize;
    @XmlElementRef(name = "SearchConstraints", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfKeyValuePairOfstringstring> searchConstraints;

    /**
     * Gets the value of the page property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPage(Integer value) {
        this.page = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPageSize(Integer value) {
        this.pageSize = value;
    }

    /**
     * Gets the value of the searchConstraints property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValuePairOfstringstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfKeyValuePairOfstringstring> getSearchConstraints() {
        return searchConstraints;
    }

    /**
     * Sets the value of the searchConstraints property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfKeyValuePairOfstringstring }{@code >}
     *     
     */
    public void setSearchConstraints(JAXBElement<ArrayOfKeyValuePairOfstringstring> value) {
        this.searchConstraints = ((JAXBElement<ArrayOfKeyValuePairOfstringstring> ) value);
    }

}
