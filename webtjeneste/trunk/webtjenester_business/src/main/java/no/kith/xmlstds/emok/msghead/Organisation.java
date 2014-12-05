//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.29 at 10:55:06 AM CEST 
//


package no.kith.xmlstds.emok.msghead;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import no.kith.xmlstds.emok.codes.CV;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="OrganisationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TypeOrganisation" type="{http://www.kith.no/xmlstds/msghead/2006-05-24}CV" minOccurs="0"/>
 *         &lt;element name="Ident" type="{http://www.kith.no/xmlstds/msghead/2006-05-24}Ident" maxOccurs="unbounded"/>
 *         &lt;element name="Address" type="{http://www.kith.no/xmlstds/msghead/2006-05-24}Address" minOccurs="0"/>
 *         &lt;element name="TeleCom" type="{http://www.kith.no/xmlstds/msghead/2006-05-24}TeleCom" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.kith.no/xmlstds/msghead/2006-05-24}Organisation" minOccurs="0"/>
 *         &lt;element ref="{http://www.kith.no/xmlstds/msghead/2006-05-24}HealthcareProfessional" minOccurs="0"/>
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
    "organisationName",
    "typeOrganisation",
    "ident",
    "address",
    "teleCom",
    "organisation",
    "healthcareProfessional"
})
@XmlRootElement(name = "Organisation", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
public class Organisation {

    @XmlElement(name = "OrganisationName", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected String organisationName;
    @XmlElement(name = "TypeOrganisation", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected CV typeOrganisation;
    @XmlElement(name = "Ident", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected List<Ident> ident;
    @XmlElement(name = "Address", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected Address address;
    @XmlElement(name = "TeleCom", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected List<TeleCom> teleCom;
    @XmlElement(name = "Organisation", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected Organisation organisation;
    @XmlElement(name = "HealthcareProfessional", namespace = "http://www.kith.no/xmlstds/msghead/2006-05-24")
    protected HealthcareProfessional healthcareProfessional;

    /**
     * Gets the value of the organisationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganisationName() {
        return organisationName;
    }

    /**
     * Sets the value of the organisationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganisationName(String value) {
        this.organisationName = value;
    }

    /**
     * Gets the value of the typeOrganisation property.
     * 
     * @return
     *     possible object is
     *     {@link CV }
     *     
     */
    public CV getTypeOrganisation() {
        return typeOrganisation;
    }

    /**
     * Sets the value of the typeOrganisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CV }
     *     
     */
    public void setTypeOrganisation(CV value) {
        this.typeOrganisation = value;
    }

    /**
     * Gets the value of the ident property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ident property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ident }
     * 
     * 
     */
    public List<Ident> getIdent() {
        if (ident == null) {
            ident = new ArrayList<Ident>();
        }
        return this.ident;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the teleCom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the teleCom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTeleCom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TeleCom }
     * 
     * 
     */
    public List<TeleCom> getTeleCom() {
        if (teleCom == null) {
            teleCom = new ArrayList<TeleCom>();
        }
        return this.teleCom;
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