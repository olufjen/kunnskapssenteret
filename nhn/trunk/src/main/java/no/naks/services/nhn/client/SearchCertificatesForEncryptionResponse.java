
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
 *         &lt;element name="SearchCertificatesForEncryptionResult" type="{http://register.nhn.no/CertificateDetails}ArrayOfCertificateDetails" minOccurs="0"/>
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
    "searchCertificatesForEncryptionResult"
})
@XmlRootElement(name = "SearchCertificatesForEncryptionResponse")
public class SearchCertificatesForEncryptionResponse {

    @XmlElementRef(name = "SearchCertificatesForEncryptionResult", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<ArrayOfCertificateDetails> searchCertificatesForEncryptionResult;

    /**
     * Gets the value of the searchCertificatesForEncryptionResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCertificateDetails }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCertificateDetails> getSearchCertificatesForEncryptionResult() {
        return searchCertificatesForEncryptionResult;
    }

    /**
     * Sets the value of the searchCertificatesForEncryptionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCertificateDetails }{@code >}
     *     
     */
    public void setSearchCertificatesForEncryptionResult(JAXBElement<ArrayOfCertificateDetails> value) {
        this.searchCertificatesForEncryptionResult = ((JAXBElement<ArrayOfCertificateDetails> ) value);
    }

}
