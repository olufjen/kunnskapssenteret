package no.helsebiblioteket.admin.webservice;

import java.util.Map;

import no.helsebiblioteket.admin.domain.IpAddress;
import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
@SuppressWarnings("serial")

public class LoginServiceWeb extends BasicWebService implements LoginService {
	protected static final Log logger = LogFactory.getLog(LoginServiceWeb.class);
	private QName logInIpAddressName;
	private QName logInUserName;
	private QName sendPasswordEmailName;
	private Map<String, Boolean>cachedFor = null;
	// <paramtermap>
	// 		<entry name="loginUserByUsernamePassword" value="false">
	// 		<entry name="loginOrganizationByIpAddress" value="true">
	// </paramtermap>
	
	public SingleResultUser loginUserByUsernamePassword(String username, String password) {
		Object[] args = new Object[] { username, password };
		Class[] returnTypes = new Class[] { SingleResult.class };
		// TODO: Remove this:
		SingleResultUser res = (SingleResultUser)invoke(this.logInUserName, args, returnTypes);
		User user;
		if(res instanceof ValueResultOrganizationUser){
			user = ((ValueResultOrganizationUser)res).getValue().getUser();
		} else if(res instanceof ValueResultUser){
			user = ((ValueResultUser)res).getValue();
		} else {
			user = null;
		}
		if(user!=null && user.getRoleList() == null) user.setRoleList(new Role[0]);
		return res;
	}
	public SingleResultMemberOrganization loginOrganizationByIpAddress(IpAddress ipAddress) {
		Object[] args = new Object[] { ipAddress };
		Class[] returnTypes = new Class[] { SingleResultMemberOrganization.class };
		
		// if ... 
		
		
		
		return (SingleResultMemberOrganization)invoke(this.logInIpAddressName, args, returnTypes);
	}
	public Boolean sendPasswordEmail(String emailAddress) {
		Object[] args = new Object[] { emailAddress };
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
	
	
	
	
	@Override
	public SingleResultPosition hmmm(Person person) {
		// TODO Auto-generated method stub
		return null;
	}
}
