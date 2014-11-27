
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Code complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Code">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodeText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodeValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SimpleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Code", namespace = "http://register.nhn.no/Common", propOrder = {
    "codeText",
    "codeValue",
    "oid",
    "simpleType"
})
public class Code {

    @XmlElementRef(name = "CodeText", namespace = "http://register.nhn.no/Common", type = JAXBElement.class)
    protected JAXBElement<String> codeText;
    @XmlElementRef(name = "CodeValue", namespace = "http://register.nhn.no/Common", type = JAXBElement.class)
    protected JAXBElement<String> codeValue;
    @XmlElement(name = "OID")
    protected Integer oid;
    @XmlElementRef(name = "SimpleType", namespace = "http://register.nhn.no/Common", type = JAXBElement.class)
    protected JAXBElement<String> simpleType;

    /**
     * Gets the value of the codeText property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodeText() {
        return codeText;
    }

    /**
     * Sets the value of the codeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodeText(JAXBElement<String> value) {
        this.codeText = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the codeValue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodeValue() {
        return codeValue;
    }

    /**
     * Sets the value of the codeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodeValue(JAXBElement<String> value) {
        this.codeValue = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the oid property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOID() {
        return oid;
    }

    /**
     * Sets the value of the oid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOID(Integer value) {
        this.oid = value;
    }

    /**
     * Gets the value of the simpleType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSimpleType() {
        return simpleType;
    }

    /**
     * Sets the value of the simpleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSimpleType(JAXBElement<String> value) {
        this.simpleType = ((JAXBElement<String> ) value);
    }

}
