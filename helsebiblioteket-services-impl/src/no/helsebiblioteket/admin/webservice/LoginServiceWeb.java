package no.helsebiblioteket.admin.webservice;

import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.IpAddress;
import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInOrganizationResult;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInUserResult;
import no.helsebiblioteket.admin.domain.requestresult.SendPasswordEmailResult;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
@SuppressWarnings("serial")

public class LoginServiceWeb extends BasicWebService implements LoginService {
	protected static final Log logger = LogFactory.getLog(LoginServiceWeb.class);
	private QName logInIpAddressName;
	private QName logInUserName;
	private QName sendPasswordEmailName;
	// <paramtermap>
	// 		<entry name="loginUserByUsernamePassword" value="false">
	// 		<entry name="loginOrganizationByIpAddress" value="true">
	// </paramtermap>
	
	@SuppressWarnings("unchecked")
	@Override
	public LoggedInUserResult loginUserByUsernamePassword(String username, String password) {
		Object[] args = new Object[] { username, password };
		Class[] returnTypes = new Class[] { SingleResultUser.class };
		return (LoggedInUserResult) invoke(this.logInUserName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public LoggedInOrganizationResult loginOrganizationByIpAddress(IpAddress ipAddress) {
		Object[] args = new Object[] { ipAddress };
		Class[] returnTypes = new Class[] { LoggedInOrganizationResult.class };
		return (LoggedInOrganizationResult) invoke(this.logInIpAddressName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SendPasswordEmailResult sendPasswordEmail(String emailAddress, Email email) {
		Object[] args = new Object[] { emailAddress, email };
		Class[] returnTypes = new Class[] { SendPasswordEmailResult.class  };
		return (SendPasswordEmailResult)invoke(this.sendPasswordEmailName, args, returnTypes);
	}
	@Override
	public Log getLogger() {
		return logger;
	}

	public void setLogInIpAddressName(QName logInIpAddressName) {
		this.logInIpAddressName = logInIpAddressName;
	}
	public void setLogInUserName(QName logInUserName) {
		this.logInUserName = logInUserName;
	}
	public void setSendPasswordEmailName(QName sendPasswordEmailName) {
		this.sendPasswordEmailName = sendPasswordEmailName;
	}

	
	public static void main(String[] args) throws Exception {
		// TODO Fase2: Move to unit test package!
		QName logInIpAddress = new QName("http://service.admin.helsebiblioteket.no", "loginOrganizationByIpAddress");
		EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis2/services/loginService");
		LoginServiceWeb loginService = new LoginServiceWeb();
		RPCServiceClient serviceClient = new RPCServiceClient();
		loginService.setServiceClient(serviceClient);
		loginService.setTargetEPR(targetEPR);
		loginService.setLogInIpAddressName(logInIpAddress);
		loginService.init();
		
    	IpAddress ipAddress = new IpAddress();
    	ipAddress.setAddress("111.222.333");
    	@SuppressWarnings("unused")
		LoggedInOrganizationResult result = loginService.loginOrganizationByIpAddress(ipAddress);
//    	Organization organization = null;
//    	System.out.println("organization: " + organization.getName());
	}

}
