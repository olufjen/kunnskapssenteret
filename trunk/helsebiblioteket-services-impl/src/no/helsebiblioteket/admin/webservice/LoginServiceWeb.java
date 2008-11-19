package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.domain.IpAddress;
import no.helsebiblioteket.domain.Organization;
import no.helsebiblioteket.domain.User;
@SuppressWarnings("serial")

public class LoginServiceWeb implements LoginService {
	protected static final Log logger = LogFactory.getLog(LoginServiceWeb.class);
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private Options options;
	private QName logInIpAddress;
	
	public void init(){
		options = serviceClient.getOptions();
	    options.setTo(targetEPR);
	}
	public Organization logInIpAddress(IpAddress address) {
		Object[] loginIpAddressArgs = new Object[] { address };
		Class[] returnTypes = new Class[] { Organization.class };
		try {
			Object[] response = serviceClient.invokeBlocking(logInIpAddress,
			        loginIpAddressArgs, returnTypes);
			Organization organization = (Organization) response[0];
			return organization;
		} catch (AxisFault e) {
			// TODO What do we do here?
			logger.error(e);
		}
		return null;
	}
	public boolean logInUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	public void sendPasswordEmail(User user) {
		// TODO Auto-generated method stub
		
	}
	public void setServiceClient(RPCServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}
	public void setTargetEPR(EndpointReference targetEPR) {
		this.targetEPR = targetEPR;
	}
	public void setLogInIpAddress(QName logInIpAddress) {
		this.logInIpAddress = logInIpAddress;
	}
}
