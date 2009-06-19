package no.helsebiblioteket.admin.ssoservice;

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

public class LoginServiceSso extends SsoService implements LoginService {
	protected static final Log logger = LogFactory.getLog(LoginServiceSso.class);
	
	private LoginService loginService;
	
	@SuppressWarnings("unchecked")
	@Override
	public LoggedInUserResult loginUserByUsernamePassword(String username, String password) {
		return loginService.loginUserByUsernamePassword(username, password);
	}
	@SuppressWarnings("unchecked")
	@Override
	public LoggedInOrganizationResult loginOrganizationByIpAddress(IpAddress ipAddress) {
		return loginService.loginOrganizationByIpAddress(ipAddress);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SendPasswordEmailResult sendPasswordEmail(String emailAddress, Email email) {
		return loginService.sendPasswordEmail(emailAddress, email);
	}
	@Override
	public Log getLogger() {
		return logger;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
