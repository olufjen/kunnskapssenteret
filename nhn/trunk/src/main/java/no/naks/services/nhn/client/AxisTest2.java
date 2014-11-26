package no.naks.services.nhn.client;

import java.util.List;

import no.naks.services.nhn.client.adr.CommunicationPartyStub;


public class AxisTest2 {
	private CommunicationPartyStub adreg;
	private static final String NHN_TEST="https://ws-test.nhn.no/Basic/v1/AR?wsdl";
	
	
	public static void main(String[] args){
		AxisClientImpl client = new AxisClientImpl("");
		String axUrl =client.getNHN_TEST();
		List<CommunicationParty> organisations = client.connectToNHN(axUrl);
		int x = organisations.size();
		

		
	}
	


}
