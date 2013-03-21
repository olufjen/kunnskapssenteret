package no.helsebiblioteket.evs.plugin;

/*
 * @author Fredrik Sørensen (mail@fredriksorensen.com) and Leif Torger Grøndahl (ltg@kunnskapssenteret.no)
 */

import com.enonic.cms.api.plugin.ext.http.HttpInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class LogInInterceptor extends HttpInterceptor {
	private static final Log logger = LogFactory.getLog(LogInInterceptor.class);
	private LogInOrganization logInOrganization;
	
	public void setLogInOrganization(LogInOrganization logInOrganization) {
		this.logInOrganization = logInOrganization;
	}

	public LogInInterceptor(){
		logger.debug("HttpInterceptorPluginAutoLoginHelsebiblioteket CREATED");
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logInOrganization.logIn(request, response);
		return true;
	}
	
}
