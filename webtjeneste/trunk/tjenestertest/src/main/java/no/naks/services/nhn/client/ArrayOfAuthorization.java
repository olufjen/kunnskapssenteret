
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfAuthorization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 *  
 * <pre>
 * &lt;complexType name="ArrayOfAuthorization">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Authorization" type="{http://register.nhn.no/HPR}Authorization" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAuthorization", namespace = "http://register.nhn.no/HPR", propOrder = {
    "authorization"
})
public class ArrayOfAuthorization {

    @XmlElement(name = "Authorization", nillable = true)
    protected List<Authorization> authorization;

    /**
     * Gets the value of the authorization property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authorization property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthorization().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Authorization }
     * 
     * 
     */
    public List<Authorization> getAuthorization() {
        if (authorization == null) {
            authorization = new ArrayList<Authorization>();
        }
        return this.authorization;
    }

}
