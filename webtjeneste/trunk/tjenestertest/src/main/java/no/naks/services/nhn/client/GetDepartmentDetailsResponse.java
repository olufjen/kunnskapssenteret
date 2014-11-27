
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
 *         &lt;element name="GetDepartmentDetailsResult" type="{http://register.nhn.no/CommunicationParty}Department" minOccurs="0"/>
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
    "getDepartmentDetailsResult"
})
@XmlRootElement(name = "GetDepartmentDetailsResponse")
public class GetDepartmentDetailsResponse {

    @XmlElementRef(name = "GetDepartmentDetailsResult", namespace = "http://register.nhn.no/CommunicationParty", type = JAXBElement.class)
    protected JAXBElement<Department> getDepartmentDetailsResult;

    /**
     * Gets the value of the getDepartmentDetailsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Department }{@code >}
     *     
     */
    public JAXBElement<Department> getGetDepartmentDetailsResult() {
        return getDepartmentDetailsResult;
    }

    /**
     * Sets the value of the getDepartmentDetailsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Department }{@code >}
     *     
     */
    public void setGetDepartmentDetailsResult(JAXBElement<Department> value) {
        this.getDepartmentDetailsResult = ((JAXBElement<Department> ) value);
    }

}
