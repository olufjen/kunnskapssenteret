
package no.naks.services.nhn.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCertificateDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCertificateDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CertificateDetails" type="{http://register.nhn.no/CertificateDetails}CertificateDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCertificateDetails", namespace = "http://register.nhn.no/CertificateDetails", propOrder = {
    "certificateDetails"
})
public class ArrayOfCertificateDetails {

    @XmlElement(name = "CertificateDetails", nillable = true)
    protected List<CertificateDetails> certificateDetails;

    /**
     * Gets the value of the certificateDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificateDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificateDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertificateDetails }
     * 
     * 
     */
    public List<CertificateDetails> getCertificateDetails() {
        if (certificateDetails == null) {
            certificateDetails = new ArrayList<CertificateDetails>();
        }
        return this.certificateDetails;
    }

}
