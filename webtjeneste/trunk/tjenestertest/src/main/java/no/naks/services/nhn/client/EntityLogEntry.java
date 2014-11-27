
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
 * <p>Java class for EntityLogEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityLogEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ByUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Changes" type="{http://register.nhn.no/Common}ArrayOfEntityLogEntryData" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityLogEntry", namespace = "http://register.nhn.no/Common", propOrder = {
    "byUser",
    "changes",
    "description",
    "time"
})
public class EntityLogEntry {

    @XmlElementRef(name = "ByUser", namespace = "http://register.nhn.no/Common", type = JAXBElement.class)
    protected JAXBElement<String> byUser;
    @XmlElementRef(name = "Changes", namespace = "http://register.nhn.no/Common", type = JAXBElement.class)
    protected JAXBElement<ArrayOfEntityLogEntryData> changes;
    @XmlElementRef(name = "Description", namespace = "http://register.nhn.no/Common", type = JAXBElement.class)
    protected JAXBElement<String> description;
    @XmlElement(name = "Time")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;

    /**
     * Gets the value of the byUser property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getByUser() {
        return byUser;
    }

    /**
     * Sets the value of the byUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setByUser(JAXBElement<String> value) {
        this.byUser = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the changes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntityLogEntryData }{@code >}
     *     
     */
    public JAXBElement<ArrayOfEntityLogEntryData> getChanges() {
        return changes;
    }

    /**
     * Sets the value of the changes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfEntityLogEntryData }{@code >}
     *     
     */
    public void setChanges(JAXBElement<ArrayOfEntityLogEntryData> value) {
        this.changes = ((JAXBElement<ArrayOfEntityLogEntryData> ) value);
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescription(JAXBElement<String> value) {
        this.description = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

}
