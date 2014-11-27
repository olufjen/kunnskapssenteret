
/**
 * ICommunicationPartyService_GetCertificateForValidatingSignature_ValidationFaultFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */

package no.naks.services.nhn.client.adr;

public class ICommunicationPartyService_GetCertificateForValidatingSignature_ValidationFaultFault_FaultMessage extends java.lang.Exception{
    
    private no.naks.services.nhn.client.adr.CommunicationPartyStub.ValidationFaultE faultMessage;

    
        public ICommunicationPartyService_GetCertificateForValidatingSignature_ValidationFaultFault_FaultMessage() {
            super("ICommunicationPartyService_GetCertificateForValidatingSignature_ValidationFaultFault_FaultMessage");
        }

        public ICommunicationPartyService_GetCertificateForValidatingSignature_ValidationFaultFault_FaultMessage(java.lang.String s) {
           super(s);
        }

        public ICommunicationPartyService_GetCertificateForValidatingSignature_ValidationFaultFault_FaultMessage(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ICommunicationPartyService_GetCertificateForValidatingSignature_ValidationFaultFault_FaultMessage(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(no.naks.services.nhn.client.adr.CommunicationPartyStub.ValidationFaultE msg){
       faultMessage = msg;
    }
    
    public no.naks.services.nhn.client.adr.CommunicationPartyStub.ValidationFaultE getFaultMessage(){
       return faultMessage;
    }
}
    