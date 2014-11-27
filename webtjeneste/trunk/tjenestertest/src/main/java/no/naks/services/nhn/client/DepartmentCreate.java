
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DepartmentCreate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DepartmentCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusinessType" type="{http://register.nhn.no/Common}Code" minOccurs="0"/>
 *         &lt;element name="DisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ElectronicAddresses" type="{http://register.nhn.no/Common}ArrayOfElectronicAddress" minOccurs="0"/>
 *         &lt;element name="ParentHerId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PhysicalAddresses" type="{http://register.nhn.no/Common}ArrayOfPhysicalAddress" minOccurs="0"/>
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
@XmlType(name = "DepartmentCreate", propOrder = {
    "businessType",
    "displayName",
    "electronicAddresses",
    "parentHerId",
    "physicalAddresses",
    "valid"
})
public class DepartmentCreate {

    @XmlElementRef(name = "BusinessType", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Code> businessType;
    @XmlElementRef(name = "DisplayName", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<String> displayName;
    @XmlElementRef(name = "ElectronicAddresses", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfElectronicAddress> electronicAddresses;
    @XmlElement(name = "ParentHerId")
    protected Integer parentHerId;
    @XmlElementRef(name = "PhysicalAddresses", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPhysicalAddress> physicalAddresses;
    @XmlElementRef(name = "Valid", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Period> valid;

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
