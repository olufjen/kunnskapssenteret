
package no.naks.services.nhn.client;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ValidationFault", targetNamespace = "http://register.nhn.no/Common")
public class ICommunicationPartyServiceCreateDepartmentValidationFaultFaultFaultMessage
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ValidationFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ICommunicationPartyServiceCreateDepartmentValidationFaultFaultFaultMessage(String message, ValidationFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ICommunicationPartyServiceCreateDepartmentValidationFaultFaultFaultMessage(String message, ValidationFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: no.naks.services.nhn.client.ValidationFault
     */
    public ValidationFault getFaultInfo() {
        return faultInfo;
    }

}
