
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSearchFacet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSearchFacet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchFacet" type="{http://register.nhn.no/CommunicationParty}SearchFacet" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSearchFacet", propOrder = {
    "searchFacet"
})
public class ArrayOfSearchFacet {

    @XmlElement(name = "SearchFacet", nillable = true)
    protected List<SearchFacet> searchFacet;

    /**
     * Gets the value of the searchFacet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchFacet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchFacet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchFacet }
     * 
     * 
     */
    public List<SearchFacet> getSearchFacet() {
        if (searchFacet == null) {
            searchFacet = new ArrayList<SearchFacet>();
        }
        return this.searchFacet;
    }

}
