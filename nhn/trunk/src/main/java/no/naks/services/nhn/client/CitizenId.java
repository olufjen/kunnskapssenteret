
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CitizenId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CitizenId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CitizenIdType" type="{http://register.nhn.no/Common}CitizenIdType" minOccurs="0"/>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sex" type="{http://register.nhn.no/Common}Code" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CitizenId", namespace = "http://register.nhn.no/Common", propOrder = {
    "citizenIdType",
    "id",
    "sex"
})
public class CitizenId {

    @XmlElement(name = "CitizenIdType")
    protected CitizenIdType citizenIdType;
    @XmlElementRef(name = "Id", namespace = "http://register.nhn.no/Common", type = JAXBElement.class)
    protected JAXBElement<String> id;
    @XmlElementRef(name = "Sex", namespace = "http://register.nhn.no/Common", type = JAXBElement.class)
    protected JAXBElement<Code> sex;

    /**
     * Gets the value of the citizenIdType property.
     * 
     * @return
     *     possible object is
     *     {@link CitizenIdType }
     *     
     */
    public CitizenIdType getCitizenIdType() {
        return citizenIdType;
    }

    /**
     * Sets the value of the citizenIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CitizenIdType }
     *     
     */
    public void setCitizenIdType(CitizenIdType value) {
        this.citizenIdType = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setId(JAXBElement<String> value) {
        this.id = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public JAXBElement<Code> getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public void setSex(JAXBElement<Code> value) {
        this.sex = ((JAXBElement<Code> ) value);
    }

}
