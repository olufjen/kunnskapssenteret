package no.naks.services.nhn.client;

import java.rmi.RemoteException;
import java.util.List;

public interface AxisClient {

	public String getNHN_TEST();




	public void setNHN_TEST(String nHN_TEST);




	public List connectToNHN(String url);
}
