package no.helsebiblioteket.evs.plugin;

/*
 * @author Fredrik Sørensen (mail@fredriksorensen.com) and Leif Torger Grøndahl (ltg@kunnskapssenteret.no)
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import com.enonic.cms.api.plugin.HttpInterceptorPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.LoggedInOrganization;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.validator.IpAddressValidator;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInOrganizationResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class LogInInterceptor extends HttpInterceptorPlugin {
	private static final Log logger = LogFactory.getLog(LogInInterceptor.class);
	private static final String XForwardedForHeaderName = "X-Forwarded-For";
	private LoginService loginService;
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	public LogInInterceptor(){
		logger.debug("HttpInterceptorPluginAutoLoginHelsebiblioteket CREATED");
	}
	public void postHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
	}
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoggedInOrganization organization = this.loggedInOrganization();	
		LoggedInOrganizationResult res = null;
		String referringDomain = getRefererringDomainStringFromRequest(request);
		String domainKey = getKeyFromRequest(request);
		if (referringDomain != null && domainKey != null && !"".equals(referringDomain) && !"".equals(domainKey)) {
			res = this.loginService.loginOrganizationByReferringDomain(referringDomain, domainKey);
		}
		if (null != res && res.isSuccess()) {
			this.logInOrganization(res.getOrganization());
		} else if (organization == null) {
			IpAddress ipAddress = new IpAddress();
	    	ipAddress.setAddress(getXforwardedForOrRemoteAddress(request));
	    	if(IpAddressValidator.getInstance().isValidIPAddress(ipAddress.getAddress())){
		    	res = this.loginService.loginOrganizationByIpAddress(ipAddress);
	    		if (null == res) {
	    			logger.error("loginService.loginOrganizationByIpAddress returned null for IP '" + ipAddress + "'. This was not expected.");
	    		}
	    		else if(res.isSuccess()){
		    		this.logInOrganization(res.getOrganization());
		    	}
	    	}
		}
		return true;
	}
	
	private String getKeyFromRequest(HttpServletRequest request) {
		return request.getParameter("pwd");
	}
	
	
	private String getRefererringDomainStringFromRequest(HttpServletRequest request) {
		String referer = null;
		String domainString = null;
		domainString = request.getParameter("referer");
		if (null == domainString || "".equals(domainString)) {
			referer = request.getHeader("referer");
			if (referer != null && !"".equals(referer)) {
				try {
					URL url = new URL(referer);
					domainString = url.getHost();
				} catch (MalformedURLException mfue) {
					logger.warn("Unable to find valid referer for request. Message: " + mfue.getMessage());
				}
			}
		}
		return domainString;
	}
	
	public static String getXforwardedForOrRemoteAddress(HttpServletRequest request) {
        String ret = null;
        @SuppressWarnings("rawtypes")
		Enumeration en = request.getHeaderNames();
        while (en != null && en.hasMoreElements()){
            Object o = en.nextElement();
            if (o instanceof String) {
                String h = (String)o;
                if (LogInInterceptor.XForwardedForHeaderName.equalsIgnoreCase(h)) {
                    String xFf = request.getHeader(h);
                    logger.debug("Header " + LogInInterceptor.XForwardedForHeaderName + "=" + xFf);
                    if (null != xFf) {
	                    // According to spec xff-header should contain address separated by ", ", but is this always the case?
	                    // removing all whitespaces before split just in case whitespaces are not always set.
	                    xFf = xFf.replaceAll("\\s+", "");
	                    String xFfArray[] = xFf.split(",");
	                    // Reading the LAST element in xff-header based on Basefarms recommendation
	                    if (xFfArray != null && (xFfArray.length > 0)) {
	                    	ret = (String) xFfArray[(xFfArray.length - 1)];
	                    	logger.debug("Remote " + LogInInterceptor.XForwardedForHeaderName + " address=" + ret);
	                    }
	                    break;
                    }
                }
            }
        }
        return (ret != null ? ret : request.getRemoteAddr());
    }
	public void logInOrganization(LoggedInOrganization organization){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		// jan 2011: extra logging to nail enonic session trouble
		{
			LoggedInOrganization alreadyLoggedInOrganization = (LoggedInOrganization) session.getAttribute(sessionLoggedInOrganizationVarName);
			logger.info("Start login authenticated organization with  " + organization.getNameNorwegianNormal() + " into session id " + session.getId() + " created at " + session.getCreationTime());
			if (alreadyLoggedInOrganization != null) {
				logger.error("Logging " + organization.getNameNorwegianNormal() + " into existing session! Session currently occupied by " + alreadyLoggedInOrganization.getNameNorwegianNormal());
			} else {
				logger.info("Logging " + organization.getNameNorwegianNormal() + " into empty session");
			}
		}
		session.setAttribute(sessionLoggedInOrganizationVarName, organization);
	}
	private LoggedInOrganization loggedInOrganization(){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		return (LoggedInOrganization)session.getAttribute(sessionLoggedInOrganizationVarName);
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	// local test
	public static void main(String args[]) {
        String ret = null;
		String xFf = "191.168.2.10, 1.2.3.5";
        logger.info("Header " + LogInInterceptor.XForwardedForHeaderName + "=" + xFf);
        // xff-header should contain address separated by ", ", but is this always the case?
        // removing all whitespaces before split just in case whitespaces are not always set.
        xFf = xFf.replaceAll("\\s+", "");
        String xFfArray[] = xFf.split(",");
        // Reading the LAST element in xff-header based on Basefarms recommendation
        if (xFfArray != null && (xFfArray.length > 0)) {
        	ret = (String) xFfArray[(xFfArray.length - 1)];
        	logger.info("Remote " + LogInInterceptor.XForwardedForHeaderName + " address=" + ret);
        }
	}
}
