//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.06 at 10:43:47 AM CEST 
//


package no.naks.biovigilans_admin.web.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{}title"/>
 *         &lt;element ref="{}seecat" minOccurs="0"/>
 *         &lt;element ref="{}see" minOccurs="0"/>
 *         &lt;element ref="{}seeAlso" minOccurs="0"/>
 *         &lt;element ref="{}code" minOccurs="0"/>
 *         &lt;element ref="{}manif" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element ref="{}subcat"/>
 *           &lt;element ref="{}term" maxOccurs="unbounded" minOccurs="0"/>
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
    "title",
    "seecat",
    "see",
    "seeAlso",
    "code",
    "manif",
    "subcat",
    "term"
})
@XmlRootElement(name = "mainTerm")
public class MainTerm {

    @XmlElement(required = true)
    protected Title title;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String seecat;
    protected String see;
    protected String seeAlso;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String code;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String manif;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String subcat;
    protected List<Term> term;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link Title }
     *     
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link Title }
     *     
     */
    public void setTitle(Title value) {
        this.title = value;
    }

    /**
     * Gets the value of the seecat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeecat() {
        return seecat;
    }

    /**
     * Sets the value of the seecat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeecat(String value) {
        this.seecat = value;
    }

    /**
     * Gets the value of the see property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSee() {
        return see;
    }

    /**
     * Sets the value of the see property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSee(String value) {
        this.see = value;
    }

    /**
     * Gets the value of the seeAlso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeeAlso() {
        return seeAlso;
    }

    /**
     * Sets the value of the seeAlso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeeAlso(String value) {
        this.seeAlso = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the manif property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManif() {
        return manif;
    }

    /**
     * Sets the value of the manif property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManif(String value) {
        this.manif = value;
    }

    /**
     * Gets the value of the subcat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubcat() {
        return subcat;
    }

    /**
     * Sets the value of the subcat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubcat(String value) {
        this.subcat = value;
    }

    /**
     * Gets the value of the term property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the term property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Term }
     * 
     * 
     */
    public List<Term> getTerm() {
        if (term == null) {
            term = new ArrayList<Term>();
        }
        return this.term;
    }

}
