package no.helsebiblioteket.admin.webservice;

import no.helsebiblioteket.admin.domain.IpAddress;
import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.User;
@SuppressWarnings("serial")

public class LoginServiceWeb extends BasicWebService implements LoginService {
	protected static final Log logger = LogFactory.getLog(LoginServiceWeb.class);
	private QName logInIpAddress;
	private QName logInUser;
	private QName sendPasswordEmail;
	public SingleResult<User> loginUserByUsernamePassword(String username, String password) {
		Object[] args = new Object[] { username, password };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<User>)invoke(this.logInUser, args, returnTypes);
	}
	public SingleResult<MemberOrganization> loginOrganizationByIpAddress(IpAddress ipAddress) {
		Object[] args = new Object[] { ipAddress };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<MemberOrganization>)invoke(this.logInIpAddress, args, returnTypes);
	}
	public Boolean sendPasswordEmail(User user) {
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { Boolean.class  };
		return (Boolean)invoke(this.sendPasswordEmail, args, returnTypes);
	}

	
	public Log getLogger() {
		return this.logger;
	}
	public void setLogInIpAddress(QName logInIpAddress) {
		this.logInIpAddress = logInIpAddress;
	}
	public void setLogInUser(QName logInUser) {
		this.logInUser = logInUser;
	}
	public void setSendPasswordEmail(QName sendPasswordEmail) {
		this.sendPasswordEmail = sendPasswordEmail;
	}

	
	public static void main(String[] args) throws Exception {
		// TODO: Move to unit test package!
		QName logInIpAddress = new QName("http://service.admin.helsebiblioteket.no", "loginOrganizationByIpAddress");
		EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis2/services/loginService");
		LoginServiceWeb loginService = new LoginServiceWeb();
		RPCServiceClient serviceClient = new RPCServiceClient();
		loginService.setServiceClient(serviceClient);
		loginService.setTargetEPR(targetEPR);
		loginService.setLogInIpAddress(logInIpAddress);
		loginService.init();
		
    	IpAddress ipAddress = new IpAddress();
    	ipAddress.setAddress("111.222.333");
    	SingleResult<MemberOrganization> result = loginService.loginOrganizationByIpAddress(ipAddress);
//    	Organization organization = null;
//    	System.out.println("organization: " + organization.getName());
	}
}
