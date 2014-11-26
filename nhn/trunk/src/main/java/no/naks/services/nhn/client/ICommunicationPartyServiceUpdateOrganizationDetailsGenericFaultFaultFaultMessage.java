
package no.naks.services.nhn.client;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "GenericFault", targetNamespace = "http://register.nhn.no/Common")
public class ICommunicationPartyServiceUpdateOrganizationDetailsGenericFaultFaultFaultMessage
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private GenericFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ICommunicationPartyServiceUpdateOrganizationDetailsGenericFaultFaultFaultMessage(String message, GenericFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ICommunicationPartyServiceUpdateOrganizationDetailsGenericFaultFaultFaultMessage(String message, GenericFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: no.naks.services.nhn.client.GenericFault
     */
    public GenericFault getFaultInfo() {
        return faultInfo;
    }

}
