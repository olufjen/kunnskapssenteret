//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.29 at 10:55:06 AM CEST 
//


package no.kith.xmlstds.emok.msghead;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import no.kith.xmlstds.emok.codes.CS;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ComMethod" type="{http://www.kith.no/xmlstds/msghead/2006-05-24}CS" minOccurs="0"/>
 *         &lt;element name="RoleReceiver" type="{http://www.kith.no/xmlstds/msghead/2006-05-24}CS"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.kith.no/xmlstds/msghead/2006-05-24}Organisation" minOccurs="0"/>
 *           &lt;choice>
 *             &lt;element ref="{http://www.kith.no/xmlstds/msghead/2006-05-24}Patient" minOccurs="0"/>
 *             &lt;element ref="{http://www.kith.no/xmlstds/msghead/2006-05-24}Person" minOccurs="0"/>
 *             &lt;element ref="{http://www.kith.no/xmlstds/msghead/2006-05-24}HealthcareProfessional" minOccurs="0"/>
 *           &lt;/choice>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "comMethod",
    "roleReceiver",
    "organisation",
    "patient",
    "person",
    "healthcareProfessional"
})
@XmlRootElement(name = "OtherReceiver", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
public class OtherReceiver {

    @XmlElement(name = "ComMethod", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected CS comMethod;
    @XmlElement(name = "RoleReceiver", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24", required = true)
    protected CS roleReceiver;
    @XmlElement(name = "Organisation", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected Organisation organisation;
    @XmlElement(name = "Patient", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected Patient patient;
    @XmlElement(name = "Person", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected Person person;
    @XmlElement(name = "HealthcareProfessional", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected HealthcareProfessional healthcareProfessional;

    /**
     * Gets the value of the comMethod property.
     * 
     * @return
     *     possible object is
     *     {@link CS }
     *     
     */
    public CS getComMethod() {
        return comMethod;
    }

    /**
     * Sets the value of the comMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CS }
     *     
     */
    public void setComMethod(CS value) {
        this.comMethod = value;
    }

    /**
     * Gets the value of the roleReceiver property.
     * 
     * @return
     *     possible object is
     *     {@link CS }
     *     
     */
    public CS getRoleReceiver() {
        return roleReceiver;
    }

    /**
     * Sets the value of the roleReceiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link CS }
     *     
     */
    public void setRoleReceiver(CS value) {
        this.roleReceiver = value;
    }

    /**
     * Gets the value of the organisation property.
     * 
     * @return
     *     possible object is
     *     {@link Organisation }
     *     
     */
    public Organisation getOrganisation() {
        return organisation;
    }

    /**
     * Sets the value of the organisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Organisation }
     *     
     */
    public void setOrganisation(Organisation value) {
        this.organisation = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     * @return
     *     possible object is
     *     {@link Patient }
     *     
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Patient }
     *     
     */
    public void setPatient(Patient value) {
        this.patient = value;
    }

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link Person }
     *     
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link Person }
     *     
     */
    public void setPerson(Person value) {
        this.person = value;
    }

    /**
     * Gets the value of the healthcareProfessional property.
     * 
     * @return
     *     possible object is
     *     {@link HealthcareProfessional }
     *     
     */
    public HealthcareProfessional getHealthcareProfessional() {
        return healthcareProfessional;
    }

    /**
     * Sets the value of the healthcareProfessional property.
     * 
     * @param value
     *     allowed object is
     *     {@link HealthcareProfessional }
     *     
     */
    public void setHealthcareProfessional(HealthcareProfessional value) {
        this.healthcareProfessional = value;
    }

}