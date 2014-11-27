
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSpeciality complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSpeciality">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Speciality" type="{http://register.nhn.no/HPR}Speciality" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSpeciality", namespace = "http://register.nhn.no/HPR", propOrder = {
    "speciality"
})
public class ArrayOfSpeciality {

    @XmlElement(name = "Speciality", nillable = true)
    protected List<Speciality> speciality;

    /**
     * Gets the value of the speciality property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the speciality property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpeciality().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Speciality }
     * 
     * 
     */
    public List<Speciality> getSpeciality() {
        if (speciality == null) {
            speciality = new ArrayList<Speciality>();
        }
        return this.speciality;
    }

}
