
/**
 * ICommunicationPartyService_GetCertificateForValidatingSignature_GenericFaultFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package no.naks.services.nhn.client.adr;

public class ICommunicationPartyService_GetCertificateForValidatingSignature_GenericFaultFault_FaultMessage extends java.lang.Exception{

    private static final long serialVersionUID = 1358948685092L;
    
    private no.naks.services.nhn.client.adr.CommunicationPartyStub.GenericFaultE faultMessage;

    
        public ICommunicationPartyService_GetCertificateForValidatingSignature_GenericFaultFault_FaultMessage() {
            super("ICommunicationPartyService_GetCertificateForValidatingSignature_GenericFaultFault_FaultMessage");
        }

        public ICommunicationPartyService_GetCertificateForValidatingSignature_GenericFaultFault_FaultMessage(java.lang.String s) {
           super(s);
        }

        public ICommunicationPartyService_GetCertificateForValidatingSignature_GenericFaultFault_FaultMessage(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ICommunicationPartyService_GetCertificateForValidatingSignature_GenericFaultFault_FaultMessage(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(no.naks.services.nhn.client.adr.CommunicationPartyStub.GenericFaultE msg){
       faultMessage = msg;
    }
    
    public no.naks.services.nhn.client.adr.CommunicationPartyStub.GenericFaultE getFaultMessage(){
       return faultMessage;
    }
}
    