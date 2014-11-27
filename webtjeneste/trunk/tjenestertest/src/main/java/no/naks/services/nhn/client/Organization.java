
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Organization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Organization">
 *   &lt;complexContent>
 *     &lt;extension base="{http://register.nhn.no/CommunicationParty}CommunicationParty">
 *       &lt;sequence>
 *         &lt;element name="BusinessType" type="{http://register.nhn.no/Common}Code" minOccurs="0"/>
 *         &lt;element name="Departments" type="{http://register.nhn.no/CommunicationParty}ArrayOfDepartment" minOccurs="0"/>
 *         &lt;element name="IndustryCodes" type="{http://register.nhn.no/Common}ArrayOfCode" minOccurs="0"/>
 *         &lt;element name="OrganizationNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="People" type="{http://register.nhn.no/CommunicationParty}ArrayOfOrganizationPerson" minOccurs="0"/>
 *         &lt;element name="Services" type="{http://register.nhn.no/CommunicationParty}ArrayOfService" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organization", propOrder = {
    "businessType",
    "departments",
    "industryCodes",
    "organizationNumber",
    "people",
    "services"
})
public class Organization
    extends CommunicationParty
{

    @XmlElementRef(name = "BusinessType", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Code> businessType;
    @XmlElementRef(name = "Departments", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfDepartment> departments;
    @XmlElementRef(name = "IndustryCodes", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfCode> industryCodes;
    @XmlElement(name = "OrganizationNumber")
    protected Integer organizationNumber;
    @XmlElementRef(name = "People", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOrganizationPerson> people;
    @XmlElementRef(name = "Services", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfService> services;

    /**
     * Gets the value of the businessType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public JAXBElement<Code> getBusinessType() {
        return businessType;
    }

    /**
     * Sets the value of the businessType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public void setBusinessType(JAXBElement<Code> value) {
        this.businessType = ((JAXBElement<Code> ) value);
    }

    /**
     * Gets the value of the departments property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDepartment }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDepartment> getDepartments() {
        return departments;
    }

    /**
     * Sets the value of the departments property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDepartment }{@code >}
     *     
     */
    public void setDepartments(JAXBElement<ArrayOfDepartment> value) {
        this.departments = ((JAXBElement<ArrayOfDepartment> ) value);
    }

    /**
     * Gets the value of the industryCodes property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCode> getIndustryCodes() {
        return industryCodes;
    }

    /**
     * Sets the value of the industryCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}
     *     
     */
    public void setIndustryCodes(JAXBElement<ArrayOfCode> value) {
        this.industryCodes = ((JAXBElement<ArrayOfCode> ) value);
    }

    /**
     * Gets the value of the organizationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrganizationNumber() {
        return organizationNumber;
    }

    /**
     * Sets the value of the organizationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrganizationNumber(Integer value) {
        this.organizationNumber = value;
    }

    /**
     * Gets the value of the people property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOrganizationPerson }{@code >}
     *     
     */
    public JAXBElement<ArrayOfOrganizationPerson> getPeople() {
        return people;
    }

    /**
     * Sets the value of the people property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOrganizationPerson }{@code >}
     *     
     */
    public void setPeople(JAXBElement<ArrayOfOrganizationPerson> value) {
        this.people = ((JAXBElement<ArrayOfOrganizationPerson> ) value);
    }

    /**
     * Gets the value of the services property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfService }{@code >}
     *     
     */
    public JAXBElement<ArrayOfService> getServices() {
        return services;
    }

    /**
     * Sets the value of the services property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfService }{@code >}
     *     
     */
    public void setServices(JAXBElement<ArrayOfService> value) {
        this.services = ((JAXBElement<ArrayOfService> ) value);
    }

}
