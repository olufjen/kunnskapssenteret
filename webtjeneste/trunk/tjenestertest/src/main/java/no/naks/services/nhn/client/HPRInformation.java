
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
 * <p>Java class for HPRInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HPRInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Authorizations" type="{http://register.nhn.no/HPR}ArrayOfAuthorization" minOccurs="0"/>
 *         &lt;element name="HPRNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastChanged" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HPRInformation", namespace = "http://register.nhn.no/HPR", propOrder = {
    "authorizations",
    "hprNo",
    "lastChanged"
})
public class HPRInformation {

    @XmlElementRef(name = "Authorizations", namespace = "http://register.nhn.no/HPR", type = JAXBElement.class)
    protected JAXBElement<ArrayOfAuthorization> authorizations;
    @XmlElementRef(name = "HPRNo", namespace = "http://register.nhn.no/HPR", type = JAXBElement.class)
    protected JAXBElement<String> hprNo;
    @XmlElement(name = "LastChanged")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastChanged;

    /**
     * Gets the value of the authorizations property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfAuthorization }{@code >}
     *     
     */
    public JAXBElement<ArrayOfAuthorization> getAuthorizations() {
        return authorizations;
    }

    /**
     * Sets the value of the authorizations property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfAuthorization }{@code >}
     *     
     */
    public void setAuthorizations(JAXBElement<ArrayOfAuthorization> value) {
        this.authorizations = ((JAXBElement<ArrayOfAuthorization> ) value);
    }

    /**
     * Gets the value of the hprNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHPRNo() {
        return hprNo;
    }

    /**
     * Sets the value of the hprNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHPRNo(JAXBElement<String> value) {
        this.hprNo = ((JAXBElement<String> ) value);
    }

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

}
