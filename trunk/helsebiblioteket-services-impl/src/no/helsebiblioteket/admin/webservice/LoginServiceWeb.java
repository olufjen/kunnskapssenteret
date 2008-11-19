package no.helsebiblioteket.admin.webservice;

import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.domain.IpAddress;
import no.helsebiblioteket.domain.Organization;
import no.helsebiblioteket.domain.User;
@SuppressWarnings("serial")

public class LoginServiceWeb implements LoginService {
//	private RPCServiceClient serviceClient;
	
	
	public Organization logInIpAddress(IpAddress address) {
//	    Object[] loginIpAddressArgs = new Object[] { address };
//        Class[] returnTypes = new Class[] { Organization.class };
////	    serviceClient.invokeRobust(loginName, loginIpAddressArgs);
//        Object[] response = serviceClient.invokeBlocking(loginName,
//                loginIpAddressArgs, returnTypes);
//        Organization organization = (Organization) response[0];
		// TODO Auto-generated method stub
		return null;
	}
	public boolean logInUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	public void sendPasswordEmail(User user) {
		// TODO Auto-generated method stub
		
	}
}
