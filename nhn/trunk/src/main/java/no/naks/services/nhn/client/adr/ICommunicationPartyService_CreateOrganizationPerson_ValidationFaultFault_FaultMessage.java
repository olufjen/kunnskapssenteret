
/**
 * ICommunicationPartyService_CreateOrganizationPerson_ValidationFaultFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package no.naks.services.nhn.client.adr;

public class ICommunicationPartyService_CreateOrganizationPerson_ValidationFaultFault_FaultMessage extends java.lang.Exception{

    private static final long serialVersionUID = 1358948685419L;
    
    private no.naks.services.nhn.client.adr.CommunicationPartyStub.ValidationFaultE faultMessage;

    
        public ICommunicationPartyService_CreateOrganizationPerson_ValidationFaultFault_FaultMessage() {
            super("ICommunicationPartyService_CreateOrganizationPerson_ValidationFaultFault_FaultMessage");
        }

        public ICommunicationPartyService_CreateOrganizationPerson_ValidationFaultFault_FaultMessage(java.lang.String s) {
           super(s);
        }

        public ICommunicationPartyService_CreateOrganizationPerson_ValidationFaultFault_FaultMessage(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ICommunicationPartyService_CreateOrganizationPerson_ValidationFaultFault_FaultMessage(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(no.naks.services.nhn.client.adr.CommunicationPartyStub.ValidationFaultE msg){
       faultMessage = msg;
    }
    
    public no.naks.services.nhn.client.adr.CommunicationPartyStub.ValidationFaultE getFaultMessage(){
       return faultMessage;
    }
}
    