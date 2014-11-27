
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfKeyValuePairOfstringstring complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfKeyValuePairOfstringstring">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyValuePairOfstringstring" type="{http://schemas.datacontract.org/2004/07/System.Collections.Generic}KeyValuePairOfstringstring" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfKeyValuePairOfstringstring", namespace = "http://schemas.datacontract.org/2004/07/System.Collections.Generic", propOrder = {
    "keyValuePairOfstringstring"
})
public class ArrayOfKeyValuePairOfstringstring {

    @XmlElement(name = "KeyValuePairOfstringstring")
    protected List<KeyValuePairOfstringstring> keyValuePairOfstringstring;

    /**
     * Gets the value of the keyValuePairOfstringstring property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyValuePairOfstringstring property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyValuePairOfstringstring().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyValuePairOfstringstring }
     * 
     * 
     */
    public List<KeyValuePairOfstringstring> getKeyValuePairOfstringstring() {
        if (keyValuePairOfstringstring == null) {
            keyValuePairOfstringstring = new ArrayList<KeyValuePairOfstringstring>();
        }
        return this.keyValuePairOfstringstring;
    }

}
