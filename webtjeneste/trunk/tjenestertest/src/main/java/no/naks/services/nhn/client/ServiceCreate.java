
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceCreate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceCreate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ElectronicAddresses" type="{http://register.nhn.no/Common}ArrayOfElectronicAddress" minOccurs="0"/>
 *         &lt;element name="LocationDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ParentHerId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PhysicalAddresses" type="{http://register.nhn.no/Common}ArrayOfPhysicalAddress" minOccurs="0"/>
 *         &lt;element name="ServiceCode" type="{http://register.nhn.no/Common}Code" minOccurs="0"/>
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
@XmlType(name = "ServiceCreate", propOrder = {
    "electronicAddresses",
    "locationDescription",
    "parentHerId",
    "physicalAddresses",
    "serviceCode",
    "valid"
})
public class ServiceCreate {

    @XmlElementRef(name = "ElectronicAddresses", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfElectronicAddress> electronicAddresses;
    @XmlElementRef(name = "LocationDescription", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<String> locationDescription;
    @XmlElement(name = "ParentHerId")
    protected Integer parentHerId;
    @XmlElementRef(name = "PhysicalAddresses", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfPhysicalAddress> physicalAddresses;
    @XmlElementRef(name = "ServiceCode", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Code> serviceCode;
    @XmlElementRef(name = "Valid", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Period> valid;

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
     * Gets the value of the locationDescription property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocationDescription() {
        return locationDescription;
    }

    /**
     * Sets the value of the locationDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocationDescription(JAXBElement<String> value) {
        this.locationDescription = ((JAXBElement<String> ) value);
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
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public JAXBElement<Code> getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Code }{@code >}
     *     
     */
    public void setServiceCode(JAXBElement<Code> value) {
        this.serviceCode = ((JAXBElement<Code> ) value);
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
