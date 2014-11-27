
package no.naks.services.nhn.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValidationFault complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationFault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Errors" type="{http://register.nhn.no/Common}ArrayOfValidationError" minOccurs="0"/>
 *         &lt;element name="HasErrors" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationFault", namespace = "http://register.nhn.no/Common", propOrder = {
    "errors",
    "hasErrors"
})
public class ValidationFault {

    @XmlElementRef(name = "Errors", namespace = "http://register.nhn.no/Common", type = JAXBElement.class)
    protected JAXBElement<ArrayOfValidationError> errors;
    @XmlElement(name = "HasErrors")
    protected Boolean hasErrors;

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfValidationError }{@code >}
     *     
     */
    public JAXBElement<ArrayOfValidationError> getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfValidationError }{@code >}
     *     
     */
    public void setErrors(JAXBElement<ArrayOfValidationError> value) {
        this.errors = ((JAXBElement<ArrayOfValidationError> ) value);
    }

    /**
     * Gets the value of the hasErrors property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasErrors() {
        return hasErrors;
    }

    /**
     * Sets the value of the hasErrors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasErrors(Boolean value) {
        this.hasErrors = value;
    }

}
