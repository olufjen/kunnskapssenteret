
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CommunicationParty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommunicationParty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ElectronicAddresses" type="{http://register.nhn.no/Common}ArrayOfElectronicAddress" minOccurs="0"/>
 *         &lt;element name="HerId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LastChanged" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Municipality" type="{http://register.nhn.no/Common}Code" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParentBusinessType" type="{http://register.nhn.no/Common}Code" minOccurs="0"/>
 *         &lt;element name="ParentHerId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ParentName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParentOrganizationNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PhysicalAddresses" type="{http://register.nhn.no/Common}ArrayOfPhysicalAddress" minOccurs="0"/>
 *         &lt;element name="ServerTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://register.nhn.no/CommunicationParty}CommunicationPartyType" minOccurs="0"/>
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
@XmlType(name = "CommunicationParty", propOrder = {
    "active",
    "displayName",
    "electronicAddresses",
    "herId",
    "lastChanged",
    "municipality",
    "name",
    "parentBusinessType",
    "parentHerId",
    "parentName",
    "parentOrganizationNumber",
    "physicalAddresses",
    "serverTime",
    "type",
    "valid"
})
@XmlSeeAlso({
    Organization.class,
    Service.class,
    Department.class,
    OrganizationPerson.class
})
public class CommunicationParty {

    @XmlElement(name = "Active")
    protected Boolean active;
    @XmlElementRef(name = "DisplayName", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<String> displayName;
    @XmlElementRef(name = "ElectronicAddresses", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfElectronicAddress> electronicAddresses;
    @XmlElement(name = "HerId")
    protected Integer herId;
    @XmlElement(name = "LastChanged")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastChanged;
    @XmlElementRef(name = "Municipality", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Code> municipality;
    @XmlElementRef(name = "Name", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<String> name;
    @XmlElementRef(name = "ParentBusinessType", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Code> parentBusinessType;
    @XmlElement(name = "ParentHerId")
    protected Integer parentHerId;
    @XmlElementRef(name = "ParentName", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<String> parentName;
    @XmlElement(name = "ParentOrganizationNumber")
    protected Integer parentOrganizationNumber;
    @XmlElementRef(name = "PhysicalAddresses", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPhysicalAddress> physicalAddresses;
    @XmlElement(name = "ServerTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar serverTime;
    @XmlList
    @XmlElement(name = "Type")
    protected List<String> type;
    @XmlElementRef(name = "Valid", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Period> valid;

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDisplayName(JAXBElement<String> value) {
        this.displayName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the electronicAddresses property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}
     *     
     */
    public JAXBElement<ArrayOfElectronicAddress> getElectronicAddresses() {
        return electronicAddresses;
    }

    /**
     * Sets the value of the electronicAddresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}
     *     
     */
    public void setElectronicAddresses(JAXBElement<ArrayOfElectronicAddress> value) {
        this.electronicAddresses = ((JAXBElement<ArrayOfElectronicAddress> ) value);
    }

    /**
     * Gets the value of the herId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHerId() {
        return herId;
    }

    /**
     * Sets the value of the herId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHerId(Integer value) {
        this.herId = value;
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

    /**
     * Gets the value of the municipality property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public JAXBElement<Code> getMunicipality() {
        return municipality;
    }

    /**
     * Sets the value of the municipality property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public void setMunicipality(JAXBElement<Code> value) {
        this.municipality = ((JAXBElement<Code> ) value);
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setName(JAXBElement<String> value) {
        this.name = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the parentBusinessType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public JAXBElement<Code> getParentBusinessType() {
        return parentBusinessType;
    }

    /**
     * Sets the value of the parentBusinessType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public void setParentBusinessType(JAXBElement<Code> value) {
        this.parentBusinessType = ((JAXBElement<Code> ) value);
    }

    /**
     * Gets the value of the parentHerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParentHerId() {
        return parentHerId;
    }

    /**
     * Sets the value of the parentHerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParentHerId(Integer value) {
        this.parentHerId = value;
    }

    /**
     * Gets the value of the parentName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParentName() {
        return parentName;
    }

    /**
     * Sets the value of the parentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParentName(JAXBElement<String> value) {
        this.parentName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the parentOrganizationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParentOrganizationNumber() {
        return parentOrganizationNumber;
    }

    /**
     * Sets the value of the parentOrganizationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParentOrganizationNumber(Integer value) {
        this.parentOrganizationNumber = value;
    }

    /**
     * Gets the value of the physicalAddresses property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPhysicalAddress> getPhysicalAddresses() {
        return physicalAddresses;
    }

    /**
     * Sets the value of the physicalAddresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}
     *     
     */
    public void setPhysicalAddresses(JAXBElement<ArrayOfPhysicalAddress> value) {
        this.physicalAddresses = ((JAXBElement<ArrayOfPhysicalAddress> ) value);
    }

    /**
     * Gets the value of the serverTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getServerTime() {
        return serverTime;
    }

    /**
     * Sets the value of the serverTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setServerTime(XMLGregorianCalendar value) {
        this.serverTime = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the type property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getType() {
        if (type == null) {
            type = new ArrayList<String>();
        }
        return this.type;
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
