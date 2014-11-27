
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfEntityLogEntryData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEntityLogEntryData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EntityLogEntryData" type="{http://register.nhn.no/Common}EntityLogEntryData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEntityLogEntryData", namespace = "http://register.nhn.no/Common", propOrder = {
    "entityLogEntryData"
})
public class ArrayOfEntityLogEntryData {

    @XmlElement(name = "EntityLogEntryData", nillable = true)
    protected List<EntityLogEntryData> entityLogEntryData;

    /**
     * Gets the value of the entityLogEntryData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entityLogEntryData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntityLogEntryData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityLogEntryData }
     * 
     * 
     */
    public List<EntityLogEntryData> getEntityLogEntryData() {
        if (entityLogEntryData == null) {
            entityLogEntryData = new ArrayList<EntityLogEntryData>();
        }
        return this.entityLogEntryData;
    }

}
