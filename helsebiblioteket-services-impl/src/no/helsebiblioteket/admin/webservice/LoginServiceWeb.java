package no.helsebiblioteket.admin.webservice;

import no.helsebiblioteket.admin.domain.IpAddress;
import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.User;
@SuppressWarnings("serial")

public class LoginServiceWeb implements LoginService {
	protected static final Log logger = LogFactory.getLog(LoginServiceWeb.class);
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private Options options;
	private QName logInIpAddress;
	private QName logInUser;
	private QName sendPasswordEmail;
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
	public User logInUser(User user) {
		// TODO: And the result is that this is needed. Urk!
		user.setOrganization(null);
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { User.class };
		try {
			Object[] response = serviceClient.invokeBlocking(this.logInUser,
			        args, returnTypes);
			return (User ) response[0];
		} catch (AxisFault e) {
			logger.error(e);
		}
		return null;
	}
	public boolean sendPasswordEmail(User user) {
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { Boolean.class  };
		try {
			Object[] response = serviceClient.invokeBlocking(this.sendPasswordEmail,
			        args, returnTypes);
			return (Boolean) response[0];
		} catch (AxisFault e) {
			logger.error(e);
			return false;
		}
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
	public static void main(String[] args) throws Exception {
		QName logInIpAddress = new QName("http://service.admin.helsebiblioteket.no", "logInIpAddress");
		EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis2/services/loginService");
		LoginServiceWeb loginService = new LoginServiceWeb();
		RPCServiceClient serviceClient = new RPCServiceClient();
		loginService.setServiceClient(serviceClient);
		loginService.setTargetEPR(targetEPR);
		loginService.setLogInIpAddress(logInIpAddress);
		loginService.init();
		
    	IpAddress ipAddress = new IpAddress();
    	ipAddress.setAddress("111.222.333");
    	Organization organization = loginService.logInIpAddress(ipAddress);
    	System.out.println("organization: " + organization.getName());
    	

		

//	<bean name="loginService" class="no.helsebiblioteket.admin.webservice.LoginServiceWeb" init-method="init">
//		<property name="serviceClient" ref="serviceClient" />
//		<property name="targetEPR" ref="targetEPR" />
//		<property name="logInIpAddress" ref="logInIpAddress" />
//	</bean>
//	
//	<bean name="serviceClient" class="org.apache.axis2.rpc.client.RPCServiceClient">
//	</bean>
//
//	<bean name="targetEPR" class="org.apache.axis2.addressing.EndpointReference">
//		<constructor-arg value="http://localhost:8080/axis2/services/loginService" />
//	</bean>
//	
//	<bean name="logInIpAddress" class="javax.xml.namespace.QName">
//		<constructor-arg value="" />
//		<constructor-arg value="logInIpAddress" />
//	</bean>
//
	}
	public void setLogInUser(QName logInUser) {
		this.logInUser = logInUser;
	}
	public void setSendPasswordEmail(QName sendPasswordEmail) {
		this.sendPasswordEmail = sendPasswordEmail;
	}
}
