
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="GetCertificateDetailsForValidatingSignatureResult" type="{http://register.nhn.no/CertificateDetails}CertificateDetails" minOccurs="0"/>
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
    "getCertificateDetailsForValidatingSignatureResult"
})
@XmlRootElement(name = "GetCertificateDetailsForValidatingSignatureResponse")
public class GetCertificateDetailsForValidatingSignatureResponse {

    @XmlElementRef(name = "GetCertificateDetailsForValidatingSignatureResult", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<CertificateDetails> getCertificateDetailsForValidatingSignatureResult;

    /**
     * Gets the value of the getCertificateDetailsForValidatingSignatureResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CertificateDetails }{@code >}
     *     
     */
    public JAXBElement<CertificateDetails> getGetCertificateDetailsForValidatingSignatureResult() {
        return getCertificateDetailsForValidatingSignatureResult;
    }

    /**
     * Sets the value of the getCertificateDetailsForValidatingSignatureResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CertificateDetails }{@code >}
     *     
     */
    public void setGetCertificateDetailsForValidatingSignatureResult(JAXBElement<CertificateDetails> value) {
        this.getCertificateDetailsForValidatingSignatureResult = ((JAXBElement<CertificateDetails> ) value);
    }

}
