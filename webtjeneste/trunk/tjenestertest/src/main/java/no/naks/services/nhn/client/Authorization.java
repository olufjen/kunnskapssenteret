
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Authorization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Authorization">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LastChanged" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Profession" type="{http://register.nhn.no/Common}Code" minOccurs="0"/>
 *         &lt;element name="Requisition" type="{http://register.nhn.no/HPR}Requisition" minOccurs="0"/>
 *         &lt;element name="Specialities" type="{http://register.nhn.no/HPR}ArrayOfSpeciality" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://register.nhn.no/Common}Code" minOccurs="0"/>
 *         &lt;element name="Valid" type="{http://register.nhn.no/Common}Period" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Authorization", namespace = "http://register.nhn.no/HPR", propOrder = {
    "lastChanged",
    "profession",
    "requisition",
    "specialities",
    "type",
    "valid"
})
public class Authorization {

    @XmlElement(name = "LastChanged")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastChanged;
    @XmlElementRef(name = "Profession", namespace = "http://register.nhn.no/HPR", type = JAXBElement.class)
    protected JAXBElement<Code> profession;
    @XmlElementRef(name = "Requisition", namespace = "http://register.nhn.no/HPR", type = JAXBElement.class)
    protected JAXBElement<Requisition> requisition;
    @XmlElementRef(name = "Specialities", namespace = "http://register.nhn.no/HPR", type = JAXBElement.class)
    protected JAXBElement<ArrayOfSpeciality> specialities;
    @XmlElementRef(name = "Type", namespace = "http://register.nhn.no/HPR", type = JAXBElement.class)
    protected JAXBElement<Code> type;
    @XmlElementRef(name = "Valid", namespace = "http://register.nhn.no/HPR", type = JAXBElement.class)
    protected JAXBElement<Period> valid;

    /**
     * Gets the value of the lastChanged property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastChanged() {
        return lastChanged;
    }

    /**
     * Sets the value of the lastChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastChanged(XMLGregorianCalendar value) {
        this.lastChanged = value;
    }

    /**
     * Gets the value of the profession property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public JAXBElement<Code> getProfession() {
        return profession;
    }

    /**
     * Sets the value of the profession property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public void setProfession(JAXBElement<Code> value) {
        this.profession = ((JAXBElement<Code> ) value);
    }

    /**
     * Gets the value of the requisition property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Requisition }{@code >}
     *     
     */
    public JAXBElement<Requisition> getRequisition() {
        return requisition;
    }

    /**
     * Sets the value of the requisition property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Requisition }{@code >}
     *     
     */
    public void setRequisition(JAXBElement<Requisition> value) {
        this.requisition = ((JAXBElement<Requisition> ) value);
    }

    /**
     * Gets the value of the specialities property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpeciality }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSpeciality> getSpecialities() {
        return specialities;
    }

    /**
     * Sets the value of the specialities property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSpeciality }{@code >}
     *     
     */
    public void setSpecialities(JAXBElement<ArrayOfSpeciality> value) {
        this.specialities = ((JAXBElement<ArrayOfSpeciality> ) value);
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public JAXBElement<Code> getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public void setType(JAXBElement<Code> value) {
        this.type = ((JAXBElement<Code> ) value);
    }

    /**
     * Gets the value of the valid property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Period }{@code >}
     *     
     */
    public JAXBElement<Period> getValid() {
        return valid;
    }

    /**
     * Sets the value of the valid property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Period }{@code >}
     *     
     */
    public void setValid(JAXBElement<Period> value) {
        this.valid = ((JAXBElement<Period> ) value);
    }

}
