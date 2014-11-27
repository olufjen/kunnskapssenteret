
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBusinessTypeStatistics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBusinessTypeStatistics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessTypeStatistics" type="{http://register.nhn.no/CommunicationParty}BusinessTypeStatistics" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBusinessTypeStatistics", propOrder = {
    "businessTypeStatistics"
})
public class ArrayOfBusinessTypeStatistics {

    @XmlElement(name = "BusinessTypeStatistics", nillable = true)
    protected List<BusinessTypeStatistics> businessTypeStatistics;

    /**
     * Gets the value of the businessTypeStatistics property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessTypeStatistics property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessTypeStatistics().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessTypeStatistics }
     * 
     * 
     */
    public List<BusinessTypeStatistics> getBusinessTypeStatistics() {
        if (businessTypeStatistics == null) {
            businessTypeStatistics = new ArrayList<BusinessTypeStatistics>();
        }
        return this.businessTypeStatistics;
    }

}
