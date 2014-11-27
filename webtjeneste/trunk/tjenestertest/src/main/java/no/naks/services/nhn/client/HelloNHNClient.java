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



public class HelloNHNClient{
	
	public static void main(String[] args) throws Exception {
		System.out.println("Started 1");
        CommunicationParty_Service service = new CommunicationParty_Service();
        System.out.println("Started 1a");
       ICommunicationPartyService nhnclient = service.getBasicHttpBindingICommunicationPartyService();
 /*       
        .nhn.client.ClientCredentials.UserName.UserName = "webservice";
        .nhn.client.ClientCredentials.UserName.Password = "";



*/
    	System.out.println("Started 2");
        JAXBContext jc = JAXBContext.newInstance("no.naks.services.nhn.client");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
 //       unmarshaller.setValidating(true);
        System.out.println("Started 3");
        NHNAuthenticator auth = new NHNAuthenticator("webservice","");
        Authenticator.setDefault(auth);
        Node node = null;
        
        ObjectFactory factory = new ObjectFactory();
        CommunicationParty localParty = factory.createCommunicationParty();
        
        String result = nhnclient.ping();
        System.out.println(result);
      CommunicationPartySearch search = factory.createCommunicationPartySearch();
      KeyValuePairOfstringstring valuePair = factory.createKeyValuePairOfstringstring();
      valuePair.setKey("HerId");
      valuePair.setValue("0");
      KeyValuePairOfstringstring valuePair2 = factory.createKeyValuePairOfstringstring();
      valuePair2.setKey("HerId");
      valuePair2.setValue("100");
      List<KeyValuePairOfstringstring> keyList = new ArrayList(1);
      keyList.add(valuePair);
      keyList.add(valuePair2);
      ArrayOfKeyValuePairOfstringstring keyArray = factory.createArrayOfKeyValuePairOfstringstring();
      keyArray.keyValuePairOfstringstring = keyList;
  
      Integer page = new Integer(1);
      Integer noofPages = new Integer(100);
      search.setPage(page);
      search.setPageSize(noofPages);
      QName searchQname = new javax.xml.namespace.QName("http://register.nhn.no/CommunicationParty", "ArrayOfKeyValuePairOfstringstring"); 
      JAXBElement<ArrayOfKeyValuePairOfstringstring> searchKey = new JAXBElement<ArrayOfKeyValuePairOfstringstring>(searchQname, ArrayOfKeyValuePairOfstringstring.class,keyArray);
      search.setSearchConstraints(searchKey);
      //search.
  //    search.setSearchConstraints(keyArray);
      Search searchParam = factory.createSearch();
      JAXBElement<CommunicationPartySearch> searchF = new JAXBElement<CommunicationPartySearch>(searchQname, CommunicationPartySearch.class,search);
      searchParam.setSearch(searchF);
      
      try
      {
            SearchResult partyresult = nhnclient.search(search);
      }catch (ICommunicationPartyServiceSearchGenericFaultFaultFaultMessage e){
    	  System.out.println(e.getMessage());
      }
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
