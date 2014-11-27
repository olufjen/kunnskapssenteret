package no.naks.services.nhn.client;

import java.util.List;

import no.naks.services.nhn.client.adr.CommunicationPartyStub;


public class AxisClientProxyImpl implements AxisClientProxy {
	private CommunicationPartyStub adreg;
	private static final String NHN_TEST="https://ws-test.nhn.no/Basic/v1/AR?wsdl";
	
	
	/* (non-Javadoc)
	 * @see no.naks.services.nhn.client.AxisClientProxy#collectNHN()
	 */
	@Override
	public  void collectNHN(){
		AxisClientImpl client = new AxisClientImpl("");
		String axUrl =client.getNHN_TEST();
		List<CommunicationParty> organisations = client.connectToNHN(axUrl);
		int x = organisations.size();
		

		
	}
	


}
