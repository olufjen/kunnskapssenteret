package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.service.LoginService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.plugin.HttpAutoLoginPlugin;

public class AutoLogin extends HttpAutoLoginPlugin{
	private final Log logger = LogFactory.getLog(getClass());
	private LoginService loginService;
	private String sessionVarName;
	public String getAuthenticatedUser(HttpServletRequest request) throws Exception {
		
		if(true) return "";
		
		// TODO: Remove this plugin!
		
		Object sessionVar = request.getSession().getAttribute(this.sessionVarName);
		
		String longName = "kunnskapssenteret\\leift";
		
    	if(sessionVar != null){
    		return longName;
    	}
    	// FIXME: Use new names!
    	// TODO: Create numbered users!
    	//		forgiftninger
    	IpAddress ipAddress = new IpAddress();
    	ipAddress.setAddress(LoginInterceptor.getXforwardedForOrRemoteAddress(request));
    	Organization organization = this.loginService.logInIpAddress(ipAddress);

    	if(false) organization = null;
    	
		this.logger.info("Logging in " + organization);
    	
    	LoggedInFunction.logIn(this.sessionVarName, request.getSession(), organization);
		return longName;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void setSessionVarName(String sessionVarName) {
		this.sessionVarName = sessionVarName;
	}
}
