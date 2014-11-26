
package no.naks.services.nhn.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KeyValuePairOfintArrayOfCodeUO9uWExd complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KeyValuePairOfintArrayOfCodeUO9uWExd">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="value" type="{http://register.nhn.no/Common}ArrayOfCode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeyValuePairOfintArrayOfCodeUO9uWExd", namespace = "http://schemas.datacontract.org/2004/07/System.Collections.Generic", propOrder = {
    "key",
    "value"
})
public class KeyValuePairOfintArrayOfCodeUO9UWExd {

    protected int key;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfCode value;

    /**
     * Gets the value of the key property.
     * 
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     */
    public void setKey(int value) {
        this.key = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCode }
     *     
     */
    public ArrayOfCode getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCode }
     *     
     */
    public void setValue(ArrayOfCode value) {
        this.value = value;
    }

}
