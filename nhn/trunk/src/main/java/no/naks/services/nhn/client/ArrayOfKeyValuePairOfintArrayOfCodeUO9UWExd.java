
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfKeyValuePairOfintArrayOfCodeUO9uWExd complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfKeyValuePairOfintArrayOfCodeUO9uWExd">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyValuePairOfintArrayOfCodeUO9uWExd" type="{http://schemas.datacontract.org/2004/07/System.Collections.Generic}KeyValuePairOfintArrayOfCodeUO9uWExd" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfKeyValuePairOfintArrayOfCodeUO9uWExd", namespace = "http://schemas.datacontract.org/2004/07/System.Collections.Generic", propOrder = {
    "keyValuePairOfintArrayOfCodeUO9UWExd"
})
public class ArrayOfKeyValuePairOfintArrayOfCodeUO9UWExd {

    @XmlElement(name = "KeyValuePairOfintArrayOfCodeUO9uWExd")
    protected List<KeyValuePairOfintArrayOfCodeUO9UWExd> keyValuePairOfintArrayOfCodeUO9UWExd;

    /**
     * Gets the value of the keyValuePairOfintArrayOfCodeUO9UWExd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyValuePairOfintArrayOfCodeUO9UWExd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyValuePairOfintArrayOfCodeUO9UWExd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyValuePairOfintArrayOfCodeUO9UWExd }
     * 
     * 
     */
    public List<KeyValuePairOfintArrayOfCodeUO9UWExd> getKeyValuePairOfintArrayOfCodeUO9UWExd() {
        if (keyValuePairOfintArrayOfCodeUO9UWExd == null) {
            keyValuePairOfintArrayOfCodeUO9UWExd = new ArrayList<KeyValuePairOfintArrayOfCodeUO9UWExd>();
        }
        return this.keyValuePairOfintArrayOfCodeUO9UWExd;
    }

}
