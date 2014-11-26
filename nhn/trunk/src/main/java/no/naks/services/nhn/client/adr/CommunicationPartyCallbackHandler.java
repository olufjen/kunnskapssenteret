
/**
 * CommunicationPartyCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package no.naks.services.nhn.client.adr;

    /**
     *  CommunicationPartyCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CommunicationPartyCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CommunicationPartyCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CommunicationPartyCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for createService method
            * override this method for handling normal response from createService operation
            */
           public void receiveResultcreateService(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.CreateServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createService operation
           */
            public void receiveErrorcreateService(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for search method
            * override this method for handling normal response from search operation
            */
           public void receiveResultsearch(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.SearchResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from search operation
           */
            public void receiveErrorsearch(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getChangeLog method
            * override this method for handling normal response from getChangeLog operation
            */
           public void receiveResultgetChangeLog(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetChangeLogResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getChangeLog operation
           */
            public void receiveErrorgetChangeLog(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCommunicationPartyDetails method
            * override this method for handling normal response from getCommunicationPartyDetails operation
            */
           public void receiveResultgetCommunicationPartyDetails(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetCommunicationPartyDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCommunicationPartyDetails operation
           */
            public void receiveErrorgetCommunicationPartyDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addCustomAttribute method
            * override this method for handling normal response from addCustomAttribute operation
            */
           public void receiveResultaddCustomAttribute(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.AddCustomAttributeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCustomAttribute operation
           */
            public void receiveErroraddCustomAttribute(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getServiceDetails method
            * override this method for handling normal response from getServiceDetails operation
            */
           public void receiveResultgetServiceDetails(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetServiceDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getServiceDetails operation
           */
            public void receiveErrorgetServiceDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setCommunicationPartyValid method
            * override this method for handling normal response from setCommunicationPartyValid operation
            */
           public void receiveResultsetCommunicationPartyValid(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.SetCommunicationPartyValidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setCommunicationPartyValid operation
           */
            public void receiveErrorsetCommunicationPartyValid(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createOrganization method
            * override this method for handling normal response from createOrganization operation
            */
           public void receiveResultcreateOrganization(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.CreateOrganizationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createOrganization operation
           */
            public void receiveErrorcreateOrganization(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchById method
            * override this method for handling normal response from searchById operation
            */
           public void receiveResultsearchById(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.SearchByIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchById operation
           */
            public void receiveErrorsearchById(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCertificate method
            * override this method for handling normal response from getCertificate operation
            */
           public void receiveResultgetCertificate(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetCertificateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCertificate operation
           */
            public void receiveErrorgetCertificate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateDepartmentDetails method
            * override this method for handling normal response from updateDepartmentDetails operation
            */
           public void receiveResultupdateDepartmentDetails(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.UpdateDepartmentDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateDepartmentDetails operation
           */
            public void receiveErrorupdateDepartmentDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCustomAttributes method
            * override this method for handling normal response from getCustomAttributes operation
            */
           public void receiveResultgetCustomAttributes(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetCustomAttributesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCustomAttributes operation
           */
            public void receiveErrorgetCustomAttributes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOrganizationDetails method
            * override this method for handling normal response from getOrganizationDetails operation
            */
           public void receiveResultgetOrganizationDetails(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetOrganizationDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOrganizationDetails operation
           */
            public void receiveErrorgetOrganizationDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateOrganizationDetails method
            * override this method for handling normal response from updateOrganizationDetails operation
            */
           public void receiveResultupdateOrganizationDetails(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.UpdateOrganizationDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateOrganizationDetails operation
           */
            public void receiveErrorupdateOrganizationDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateOrganizationPersonDetails method
            * override this method for handling normal response from updateOrganizationPersonDetails operation
            */
           public void receiveResultupdateOrganizationPersonDetails(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.UpdateOrganizationPersonDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateOrganizationPersonDetails operation
           */
            public void receiveErrorupdateOrganizationPersonDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOrganizationUpdateStatistics method
            * override this method for handling normal response from getOrganizationUpdateStatistics operation
            */
           public void receiveResultgetOrganizationUpdateStatistics(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetOrganizationUpdateStatisticsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOrganizationUpdateStatistics operation
           */
            public void receiveErrorgetOrganizationUpdateStatistics(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDepartmentDetails method
            * override this method for handling normal response from getDepartmentDetails operation
            */
           public void receiveResultgetDepartmentDetails(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetDepartmentDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDepartmentDetails operation
           */
            public void receiveErrorgetDepartmentDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchCertificatesForValidatingSignature method
            * override this method for handling normal response from searchCertificatesForValidatingSignature operation
            */
           public void receiveResultsearchCertificatesForValidatingSignature(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.SearchCertificatesForValidatingSignatureResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchCertificatesForValidatingSignature operation
           */
            public void receiveErrorsearchCertificatesForValidatingSignature(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchCertificatesForEncryption method
            * override this method for handling normal response from searchCertificatesForEncryption operation
            */
           public void receiveResultsearchCertificatesForEncryption(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.SearchCertificatesForEncryptionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchCertificatesForEncryption operation
           */
            public void receiveErrorsearchCertificatesForEncryption(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCertificateForValidatingSignature method
            * override this method for handling normal response from getCertificateForValidatingSignature operation
            */
           public void receiveResultgetCertificateForValidatingSignature(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetCertificateForValidatingSignatureResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCertificateForValidatingSignature operation
           */
            public void receiveErrorgetCertificateForValidatingSignature(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateServiceDetails method
            * override this method for handling normal response from updateServiceDetails operation
            */
           public void receiveResultupdateServiceDetails(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.UpdateServiceDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateServiceDetails operation
           */
            public void receiveErrorupdateServiceDetails(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeCustomAttribute method
            * override this method for handling normal response from removeCustomAttribute operation
            */
           public void receiveResultremoveCustomAttribute(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.RemoveCustomAttributeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeCustomAttribute operation
           */
            public void receiveErrorremoveCustomAttribute(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createOrganizationPerson method
            * override this method for handling normal response from createOrganizationPerson operation
            */
           public void receiveResultcreateOrganizationPerson(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.CreateOrganizationPersonResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createOrganizationPerson operation
           */
            public void receiveErrorcreateOrganizationPerson(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCommunicationPartyStatistics method
            * override this method for handling normal response from getCommunicationPartyStatistics operation
            */
           public void receiveResultgetCommunicationPartyStatistics(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetCommunicationPartyStatisticsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCommunicationPartyStatistics operation
           */
            public void receiveErrorgetCommunicationPartyStatistics(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createDepartment method
            * override this method for handling normal response from createDepartment operation
            */
           public void receiveResultcreateDepartment(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.CreateDepartmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createDepartment operation
           */
            public void receiveErrorcreateDepartment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCertificateDetailsForEncryption method
            * override this method for handling normal response from getCertificateDetailsForEncryption operation
            */
           public void receiveResultgetCertificateDetailsForEncryption(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetCertificateDetailsForEncryptionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCertificateDetailsForEncryption operation
           */
            public void receiveErrorgetCertificateDetailsForEncryption(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for ping method
            * override this method for handling normal response from ping operation
            */
           public void receiveResultping(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.PingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from ping operation
           */
            public void receiveErrorping(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCertificateForEncryption method
            * override this method for handling normal response from getCertificateForEncryption operation
            */
           public void receiveResultgetCertificateForEncryption(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetCertificateForEncryptionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCertificateForEncryption operation
           */
            public void receiveErrorgetCertificateForEncryption(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCertificateDetailsForValidatingSignature method
            * override this method for handling normal response from getCertificateDetailsForValidatingSignature operation
            */
           public void receiveResultgetCertificateDetailsForValidatingSignature(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetCertificateDetailsForValidatingSignatureResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCertificateDetailsForValidatingSignature operation
           */
            public void receiveErrorgetCertificateDetailsForValidatingSignature(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOrganizationPersonDetails method
            * override this method for handling normal response from getOrganizationPersonDetails operation
            */
           public void receiveResultgetOrganizationPersonDetails(
                    no.naks.services.nhn.client.adr.CommunicationPartyStub.GetOrganizationPersonDetailsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOrganizationPersonDetails operation
           */
            public void receiveErrorgetOrganizationPersonDetails(java.lang.Exception e) {
            }
                


    }
    