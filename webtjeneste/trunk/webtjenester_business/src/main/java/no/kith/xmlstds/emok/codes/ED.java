//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.20 at 10:41:19 AM CEST 
//


package no.kith.xmlstds.emok.codes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ED complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ED">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.kith.no/xmlstds/emok/codes}base">
 *       &lt;attribute ref="{http://www.kith.no/xmlstds/emok/codes}MT"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ED")
@XmlSeeAlso({
    TN.class
})
public class ED
    extends Base
{

    @XmlAttribute(name = "MT", namespace = "http://www.kith.no/xmlstds/emok/codes")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mt;

    /**
     * Gets the value of the mt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMT() {
        return mt;
    }

    /**
     * Sets the value of the mt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMT(String value) {
        this.mt = value;
    }

}
