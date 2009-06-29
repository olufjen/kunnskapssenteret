package no.helsebiblioteket.admin.ssoservice;


import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.IpAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.domain.cache.key.CacheKey;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInOrganizationResult;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInUserResult;
import no.helsebiblioteket.admin.domain.requestresult.SendPasswordEmailResult;
@SuppressWarnings("serial")

public class LoginServiceSso extends SsoService implements LoginService {
	protected static final Log logger = LogFactory.getLog(LoginServiceSso.class);
	
	private LoginService loginService;
	
	@Override
	public LoggedInUserResult loginUserByUsernamePassword(String username, String password) {
		return loginService.loginUserByUsernamePassword(username, password);
	}
	@Override
	public LoggedInOrganizationResult loginOrganizationByIpAddress(IpAddress ipAddress) {		
		String key = (
				((ipAddress != null) ? (ipAddress.getAddress()) : "noip")
				);
		Object result = cacheHelper.findCache(CacheKey.loginServiceSsologinOrganizationByIpAddressCache, key);
		if (null == result) {
			result = loginService.loginOrganizationByIpAddress(ipAddress); 
			cacheHelper.addCache(CacheKey.loginServiceSsologinOrganizationByIpAddressCache, key, result);
		}
		return (LoggedInOrganizationResult) result;
		
	}
	@Override
	public LoggedInOrganizationResult loginOrganizationByReferringDomain(String accessDomain) {
		String key = (
				((accessDomain != null) ? (accessDomain) : "noreferrer")
				);
		Object result = cacheHelper.findCache(CacheKey.loginServiceSsologinOrganizationByReferringDomainCache, key);
		if (null == result) {
			result = loginService.loginOrganizationByReferringDomain(accessDomain); 
			cacheHelper.addCache(CacheKey.loginServiceSsologinOrganizationByReferringDomainCache, key, result);
		}
		return (LoggedInOrganizationResult) result;
	}
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