
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSearchResultFacetEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSearchResultFacetEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchResultFacetEntry" type="{http://register.nhn.no/CommunicationParty}SearchResultFacetEntry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSearchResultFacetEntry", propOrder = {
    "searchResultFacetEntry"
})
public class ArrayOfSearchResultFacetEntry {

    @XmlElement(name = "SearchResultFacetEntry", nillable = true)
    protected List<SearchResultFacetEntry> searchResultFacetEntry;

    /**
     * Gets the value of the searchResultFacetEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchResultFacetEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchResultFacetEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchResultFacetEntry }
     * 
     * 
     */
    public List<SearchResultFacetEntry> getSearchResultFacetEntry() {
        if (searchResultFacetEntry == null) {
            searchResultFacetEntry = new ArrayList<SearchResultFacetEntry>();
        }
        return this.searchResultFacetEntry;
    }

}
