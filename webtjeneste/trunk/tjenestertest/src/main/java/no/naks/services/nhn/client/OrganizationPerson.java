
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrganizationPerson complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrganizationPerson">
 *   &lt;complexContent>
 *     &lt;extension base="{http://register.nhn.no/CommunicationParty}CommunicationParty">
 *       &lt;sequence>
 *         &lt;element name="Departments" type="{http://register.nhn.no/CommunicationParty}ArrayOfDepartment" minOccurs="0"/>
 *         &lt;element name="Organizations" type="{http://register.nhn.no/CommunicationParty}ArrayOfOrganization" minOccurs="0"/>
 *         &lt;element name="Person" type="{http://register.nhn.no/HPR}Person" minOccurs="0"/>
 *         &lt;element name="Properties" type="{http://register.nhn.no/Common}ArrayOfCode" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationPerson", propOrder = {
    "departments",
    "organizations",
    "person",
    "properties",
    "title"
})
public class OrganizationPerson
    extends CommunicationParty
{

    @XmlElementRef(name = "Departments", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfDepartment> departments;
    @XmlElementRef(name = "Organizations", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOrganization> organizations;
    @XmlElementRef(name = "Person", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Person> person;
    @XmlElementRef(name = "Properties", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfCode> properties;
    @XmlElementRef(name = "Title", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<String> title;

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
     * Gets the value of the organizations property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOrganization }{@code >}
     *     
     */
    public JAXBElement<ArrayOfOrganization> getOrganizations() {
        return organizations;
    }

    /**
     * Sets the value of the organizations property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfOrganization }{@code >}
     *     
     */
    public void setOrganizations(JAXBElement<ArrayOfOrganization> value) {
        this.organizations = ((JAXBElement<ArrayOfOrganization> ) value);
    }

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Person }{@code >}
     *     
     */
    public JAXBElement<Person> getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Person }{@code >}
     *     
     */
    public void setPerson(JAXBElement<Person> value) {
        this.person = ((JAXBElement<Person> ) value);
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCode> getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}
     *     
     */
    public void setProperties(JAXBElement<ArrayOfCode> value) {
        this.properties = ((JAXBElement<ArrayOfCode> ) value);
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTitle(JAXBElement<String> value) {
        this.title = ((JAXBElement<String> ) value);
    }

}
