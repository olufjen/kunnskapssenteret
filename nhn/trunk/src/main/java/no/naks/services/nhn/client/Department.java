
package no.naks.services.nhn.client;

import java.io.Serializable;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Department complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Department">
 *   &lt;complexContent>
 *     &lt;extension base="{http://register.nhn.no/CommunicationParty}CommunicationParty">
 *       &lt;sequence>
 *         &lt;element name="BusinessType" type="{http://register.nhn.no/Common}Code" minOccurs="0"/>
 *         &lt;element name="IndustryCodes" type="{http://register.nhn.no/Common}ArrayOfCode" minOccurs="0"/>
 *         &lt;element name="People" type="{http://register.nhn.no/CommunicationParty}ArrayOfOrganizationPerson" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Department", propOrder = {
    "businessType",
    "industryCodes",
    "people"
})
public class Department
    extends CommunicationParty implements Serializable
{

    @XmlElementRef(name = "BusinessType", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Code> businessType;
    @XmlElementRef(name = "IndustryCodes", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfCode> industryCodes;
    @XmlElementRef(name = "People", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfOrganizationPerson> people;

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

}
