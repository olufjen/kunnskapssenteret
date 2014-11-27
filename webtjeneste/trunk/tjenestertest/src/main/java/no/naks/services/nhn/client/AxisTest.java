package no.naks.services.nhn.client;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import no.naks.services.nhn.client.adr.CommunicationPartyStub;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.Ping;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.Search;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.CommunicationPartySearch;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.SearchResponse;
//import no.naks.services.nhn.client.adr.CommunicationPartyStub.SearchResult;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.CommunicationParty;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.CommunicationPartyType;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.ArrayOfKeyValuePairOfstringstring;
import no.naks.services.nhn.client.adr.ICommunicationPartyService_Search_GenericFaultFault_FaultMessage;

//import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;

public class AxisTest {
	private CommunicationPartyStub adreg;
	private static final String NHN_TEST="https://ws-test.nhn.no/Basic/v1/AR?wsdl";
	
	
	public static void main(String[] args){
		AxisTest test = new AxisTest();
		try {
			test.connectToNHN(NHN_TEST);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void connectToNHN(String url) throws RemoteException{
		adreg = new CommunicationPartyStub(url);
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername("Webservice");
		auth.setPassword("");
		auth.setPreemptiveAuthentication(true);

		Options options = adreg._getServiceClient().getOptions();

		options.setProperty(HTTPConstants.AUTHENTICATE, auth);
		
		no.naks.services.nhn.client.adr.CommunicationPartyStub.Ping ping52 = new Ping();
	
		 if("Pong".equals(adreg.ping(ping52).getPingResult())){
			 System.out.println("Connected to NHN");
			 
		 }
 
		 CommunicationPartySearch comSearch = new CommunicationPartySearch();
		 ArrayOfKeyValuePairOfstringstring searchArray = new ArrayOfKeyValuePairOfstringstring();
		 
		   comSearch.setPageSize(5000);
		   comSearch.setPage(1);
		  
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair2 = new KeyValuePairOfstringstring();  
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair3 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair4 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair5 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair6 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair7 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair8 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair9 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair10 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair11 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair12 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair13 = new KeyValuePairOfstringstring();
		   no.naks.services.nhn.client.adr.CommunicationPartyStub.KeyValuePairOfstringstring valuePair14 = new KeyValuePairOfstringstring();
		      
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
		      
		      no.naks.services.nhn.client.adr.CommunicationPartyStub.ArrayOfKeyValuePairOfstringstring keyArray = new ArrayOfKeyValuePairOfstringstring();
//		      keyArray.addKeyValuePairOfstringstring(valuePair);
//		      keyArray.addKeyValuePairOfstringstring(valuePair2);
		      keyArray.addKeyValuePairOfstringstring(valuePair3);
		      keyArray.addKeyValuePairOfstringstring(valuePair4);
		      keyArray.addKeyValuePairOfstringstring(valuePair5);
		      keyArray.addKeyValuePairOfstringstring(valuePair6);
		      keyArray.addKeyValuePairOfstringstring(valuePair7);
		      keyArray.addKeyValuePairOfstringstring(valuePair8);
		      keyArray.addKeyValuePairOfstringstring(valuePair9);
		      keyArray.addKeyValuePairOfstringstring(valuePair10);
		      keyArray.addKeyValuePairOfstringstring(valuePair11);
		      keyArray.addKeyValuePairOfstringstring(valuePair12);
		      keyArray.addKeyValuePairOfstringstring(valuePair13);
		      keyArray.addKeyValuePairOfstringstring(valuePair14);
		      

	
		
		 comSearch.setPage(1);
		 comSearch.setPageSize(5000);
		 QName searchQname = new javax.xml.namespace.QName("http://register.nhn.no/CommunicationParty", "ArrayOfKeyValuePairOfstringstring"); 
		  JAXBElement<ArrayOfKeyValuePairOfstringstring> searchKey = new JAXBElement<ArrayOfKeyValuePairOfstringstring>(searchQname, ArrayOfKeyValuePairOfstringstring.class,keyArray);

		
		 comSearch.setSearchConstraints(keyArray);
		 Search search = new  no.naks.services.nhn.client.adr.CommunicationPartyStub.Search();
		search.setSearch(comSearch);
	
		 try {
			
			SearchResponse results = adreg.search(search);
			CommunicationParty[] resultArray = results.getSearchResult().getResults().getCommunicationParty();
			int i = 0;
			CommunicationPartyType type = null;
			for(CommunicationParty p : resultArray){
					type = p.getType();
					if (type.toString().equals("Organization")||type.toString().equals("Department") ){
						i++;
						System.out.println(p.getHerId()+" - "+p.getName()+ " "+type.toString()+ " "+i);
					}
		
		
			}
				
			
		} catch (ICommunicationPartyService_Search_GenericFaultFault_FaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 

	}

}
