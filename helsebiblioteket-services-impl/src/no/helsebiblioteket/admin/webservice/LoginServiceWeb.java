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
import no.helsebiblioteket.admin.domain.requestresult.SingleResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
@SuppressWarnings("serial")

public class LoginServiceWeb extends BasicWebService implements LoginService {
	protected static final Log logger = LogFactory.getLog(LoginServiceWeb.class);
	private QName logInIpAddressName;
	private QName logInUserName;
	private QName sendPasswordEmailName;
	public SingleResultUser loginUserByUsernamePassword(String username, String password) {
		Object[] args = new Object[] { username, password };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResultUser)invoke(this.logInUserName, args, returnTypes);
	}
	public SingleResultMemberOrganization loginOrganizationByIpAddress(IpAddress ipAddress) {
		Object[] args = new Object[] { ipAddress };
		Class[] returnTypes = new Class[] { SingleResultMemberOrganization.class };
		return (SingleResultMemberOrganization)invoke(this.logInIpAddressName, args, returnTypes);
	}
	public Boolean sendPasswordEmail(User user) {
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { Boolean.class  };
		return (Boolean)invoke(this.sendPasswordEmailName, args, returnTypes);
	}

	
	public Log getLogger() {
		return this.logger;
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
		// TODO: Move to unit test package!
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
    	SingleResultMemberOrganization result = loginService.loginOrganizationByIpAddress(ipAddress);
//    	Organization organization = null;
//    	System.out.println("organization: " + organization.getName());
	}
	
	public MemberOrganization loginOrganizationByIpAddressWS(IpAddress ipAddress) {
		Object[] args = new Object[] { ipAddress };
		Class[] returnTypes = new Class[] { MemberOrganization.class };
		Object result = invoke(this.logInIpAddressName, args, returnTypes);
		return (null != result) ? (MemberOrganization) result : null;
	}
	
	public User loginUserByUsernamePasswordWS(String username, String password) {
		Object[] args = new Object[] { username, password };
		Class[] returnTypes = new Class[] { User.class };
		Object result = invoke(this.logInUserName, args, returnTypes);
		return (result != null) ? (User) result : null;
	}
}
