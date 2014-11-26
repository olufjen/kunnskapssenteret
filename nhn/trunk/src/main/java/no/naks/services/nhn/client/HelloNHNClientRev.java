package no.naks.services.nhn.client;

import java.util.List;
import java.util.ArrayList;
import java.net.Authenticator;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;

import no.naks.services.nhn.client.adr.CommunicationPartyStub;

import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;



public class HelloNHNClientRev{
	private String NHN_TEST="https://ws-test.nhn.no/Basic/v1/AR?wsdl";
	private static CommunicationPartyStub adreg;
	public static void main(String[] args) throws Exception {
		System.out.println("Started 1");
        CommunicationParty_Service service = new CommunicationParty_Service();
        System.out.println("Started 1a");
       ICommunicationPartyService nhnclient = service.getBasicHttpBindingICommunicationPartyService();
       String url = "https://ws-test.nhn.no/Basic/v1/AR?wsdl";
   		adreg = new CommunicationPartyStub(url);
       
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername("Webservice");
		auth.setPassword("");
		auth.setPreemptiveAuthentication(true);

	

		Options options = adreg._getServiceClient().getOptions();

		options.setProperty(HTTPConstants.AUTHENTICATE, auth);
		
        ObjectFactory factory = new ObjectFactory();
        CommunicationParty localParty = factory.createCommunicationParty();
        
        String result = nhnclient.ping();
        System.out.println(result);
      CommunicationPartySearch search = new CommunicationPartySearch();

      search.setPageSize(5000);
      search.setPage(1);
  
      KeyValuePairOfstringstring valuePair = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair2 = factory.createKeyValuePairOfstringstring();  
      KeyValuePairOfstringstring valuePair3 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair4 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair5 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair6 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair7 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair8 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair9 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair10 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair11 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair12 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair13 = factory.createKeyValuePairOfstringstring();
      KeyValuePairOfstringstring valuePair14 = factory.createKeyValuePairOfstringstring();
      
      valuePair.setKey("Type");
      valuePair.setValue("Organisation");
     
      valuePair2.setKey("BusinessTypeCode");
      valuePair2.setValue("99");
      valuePair3.setKey("BusinessTypeCode");
      valuePair3.setValue("101");
      valuePair4.setKey("BusinessTypeCode");
      valuePair4.setValue("102");
      valuePair5.setKey("BusinessTypeCode");
      valuePair5.setValue("103");
      valuePair6.setKey("BusinessTypeCode");
      valuePair6.setValue("104");
      valuePair7.setKey("BusinessTypeCode");
      valuePair7.setValue("105");
      valuePair8.setKey("BusinessTypeCode");
      valuePair8.setValue("106");
      valuePair9.setKey("BusinessTypeCode");
      valuePair9.setValue("107");
      valuePair10.setKey("BusinessTypeCode");
      valuePair10.setValue("108");
      valuePair11.setKey("BusinessTypeCode");
      valuePair11.setValue("109");
      valuePair12.setKey("BusinessTypeCode");
      valuePair12.setValue("110");
      valuePair13.setKey("BusinessTypeCode");
      valuePair13.setValue("111");  
      valuePair14.setKey("BusinessTypeCode");
      valuePair14.setValue("112");
      
      ArrayOfKeyValuePairOfstringstring keyArray = factory.createArrayOfKeyValuePairOfstringstring();
      List<KeyValuePairOfstringstring> keyList = keyArray.getKeyValuePairOfstringstring();
      keyList.add(valuePair);
      keyList.add(valuePair2);
      keyList.add(valuePair3);
      keyList.add(valuePair4);
      keyList.add(valuePair5);
      keyList.add(valuePair6);
      keyList.add(valuePair7);
      keyList.add(valuePair8);
      keyList.add(valuePair9);
      keyList.add(valuePair10);
      keyList.add(valuePair11);
      keyList.add(valuePair12);
      keyList.add(valuePair13);
      keyList.add(valuePair14);
      
   
 
      QName searchQname = new javax.xml.namespace.QName("http://register.nhn.no/CommunicationParty", "ArrayOfKeyValuePairOfstringstring"); 
      JAXBElement<ArrayOfKeyValuePairOfstringstring> searchKey = new JAXBElement<ArrayOfKeyValuePairOfstringstring>(searchQname, ArrayOfKeyValuePairOfstringstring.class,keyArray);
      search.setSearchConstraints(searchKey);
      
      //search.
  //    search.setSearchConstraints(keyArray);
      Search searchParam = new Search();
      JAXBElement<CommunicationPartySearch> searchF = new JAXBElement<CommunicationPartySearch>(searchQname, CommunicationPartySearch.class,search);
      searchParam.setSearch(searchF);
      result = nhnclient.ping();
     
 	 if(result.equals("Pong")){
		 System.out.println("Connected to NHN");
/*		 
      try
      {
           SearchResponse partyresult = adreg.search(searchParam);
      }catch (ICommunicationPartyServiceSearchGenericFaultFaultFaultMessage e){
    	  System.out.println(e.getMessage());
      }
*/      
        ArrayOfCommunicationParty pParty = nhnclient.searchById("38");
        CommunicationParty party = null;
        Integer herId = new Integer(0);
    //    unmarshaller.un
        List<CommunicationParty> parties = pParty.getCommunicationParty();
        if (parties != null && parties.size() > 0){
        	party = parties.get(0);
        	herId =  party.getHerId();
        }
        
   //    Department department = .nhn.client.getDepartmentDetails(herId);
        boolean partLoop = false;
        CommunicationParty newParty = null;
        int i = 2;
        do {
        	partLoop = false;
        	Integer herIdx = new Integer(i);
        	try {
        		newParty = nhnclient.getCommunicationPartyDetails(herIdx);
        		herId = herIdx;
        	}catch (ICommunicationPartyServiceGetCommunicationPartyDetailsGenericFaultFaultFaultMessage ec){
        		System.out.println(ec.getMessage()+ " "+i);
        		partLoop = true;
        		i++;
        }
        }while (partLoop);
        JAXBElement<String> displayName = party.getDisplayName();
        JAXBElement<Code> municipality = party.getMunicipality();
        Code kommune = party.getMunicipality().getValue();
        //Code kommune = municipality.getValue();
        String parent = party.getParentName().getValue();
        JAXBElement<String> kommuneKodetext = kommune.getCodeText();
        
        JAXBElement<String> name = party.getName(); 
        JAXBElement<Code> parentBusinessType = party.getParentBusinessType();
        String dname = displayName.getValue();
//        boolean tt = localParty.active;
       String kommuneKode = kommune.getCodeValue().getValue();
       String kommuneText = kommune.getCodeText().getValue();
        System.out.println(":"+ herId+" "+parent+" "+ dname+" "+kommuneKode+" "+kommuneText+" "+parties.size());
        
 //       CommunicationPartyStatistics stats = .nhn.client.getCommunicationPartyStatistics();
 //       System.out.println(stats.toString());
      //  .nhn.client.
 	 }
    }

}
