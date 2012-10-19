package no.helsebiblioteket.evs.plugin;

/*
 * @author Fredrik Sørensen (mail@fredriksorensen.com) and Leif Torger Grøndahl (ltg@kunnskapssenteret.no)
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import com.enonic.cms.api.plugin.PluginEnvironment;
import com.enonic.cms.api.plugin.ext.http.HttpInterceptor;

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

public final class LogInInterceptor extends HttpInterceptor {
	private static final Log logger = LogFactory.getLog(LogInInterceptor.class);
	public static final String XForwardedForHeaderName = "X-Forwarded-For";
	private LoginService loginService;
	private PluginEnvironment pluginEnvironment;
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	private static boolean useXForwardedForHeader = false;
	public void setUseXForwardedForHeader(boolean useXForwardedForHeader) {
		LogInInterceptor.useXForwardedForHeader = useXForwardedForHeader;
	}
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
			this.logInOrganization(res.getOrganization(), organization, request);
		} else if (organization == null) {
			IpAddress ipAddress = new IpAddress();
	    	ipAddress.setAddress(getXforwardedForOrRemoteAddress(request));
	    	if(IpAddressValidator.getInstance().isValidIPAddress(ipAddress.getAddress())){
		    	res = this.loginService.loginOrganizationByIpAddress(ipAddress);
	    		if (null == res) {
	    			logger.error("loginService.loginOrganizationByIpAddress returned null for IP '" + ipAddress + "'. This was not expected.");
	    		}
	    		else if(res.isSuccess()){
		    		this.logInOrganization(res.getOrganization(), organization, request);
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
        if (LogInInterceptor.useXForwardedForHeader) {
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
        }
        return (ret != null ? ret : request.getRemoteAddr());
    }
	
	public void logInOrganization(LoggedInOrganization organization, LoggedInOrganization alreadyLoggedInOrganization, HttpServletRequest request){
		HttpSession session = pluginEnvironment.getCurrentSession();
		//sessionLogging(organization, alreadyLoggedInOrganization, request, session);
		session.setAttribute(sessionLoggedInOrganizationVarName, organization);
	}
	
	private LoggedInOrganization loggedInOrganization(){
		HttpSession session = pluginEnvironment.getCurrentSession(); 
		return (LoggedInOrganization)session.getAttribute(sessionLoggedInOrganizationVarName);
	}
	
	private void sessionLogging(LoggedInOrganization organization,
			LoggedInOrganization alreadyLoggedInOrganization,
			HttpServletRequest request, HttpSession session) {
		// jan 2011: extra logging to nail enonic session trouble
		logger.info("Start login authenticated organization with  " + organization.getNameNorwegianNormal() + " into session id " + session.getId() + " created at " + session.getCreationTime());
		
		
		HttpSession requestSession = request.getSession();
		if(requestSession == null){
				logger.info("requestSession is NULL for organization " + organization.getNameNorwegianNormal() + 
						" while pluginSession has id " + session.getId() + " and is created at " + session.getCreationTime());
		} else if(requestSession.getId() == null ){
				logger.info("requestSession has ID NULL for organization " + organization.getNameNorwegianNormal() + 
						" while pluginSession has id " + session.getId() + " and is created at " + session.getCreationTime());
			
		} else if( ! requestSession.getId().equals(session.getId())){
				logger.info("requestSession is HAS DIFFERENT ID for organization " + organization.getNameNorwegianNormal() + " and has session id " + requestSession.getId() + " and is created at " + requestSession.getCreationTime() +
						" while pluginSession has id " + session.getId() + " and is created at " + session.getCreationTime());
		} else if(requestSession != session){
				logger.info("requestSession HAS SAME ID AS requestSession for organization " + organization.getNameNorwegianNormal() + " and has session id " + requestSession.getId() + " and is created at " + requestSession.getCreationTime() +
						" while pluginSession has id " + session.getId() + " and is created at " + session.getCreationTime());
		} else {
				logger.info("requestSession IS IDENTICAL WITH requestSession for organization " + organization.getNameNorwegianNormal() + " where session id " + requestSession.getId() + " and is created at " + requestSession.getCreationTime());
		}

		long time = System.currentTimeMillis();
		Long lastTime = (Long) session.getAttribute("hb_trace_loggedinorgtime");
		if (alreadyLoggedInOrganization != null) {
			if(lastTime != null){
				if(lastTime.longValue() >= time - 1000){
					logger.info("Logging organization into very recent session. Name: " + organization.getNameNorwegianNormal() +
							". Session currently occupied by " + alreadyLoggedInOrganization.getNameNorwegianNormal() +
							". Last time: " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(lastTime)) +
							" and now " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(time)));
				} else {
					logger.info("Logging organization into older session. Name: " + organization.getNameNorwegianNormal() +
							". Session currently occupied by " + alreadyLoggedInOrganization.getNameNorwegianNormal() +
							". Last time: " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(lastTime)) +
							" and now " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(time)));
				}
			} else {
				// Never happens?
				logger.info("Logging organization into session with NULL time. Name: " + organization.getNameNorwegianNormal() +
						". Session currently occupied by " + alreadyLoggedInOrganization.getNameNorwegianNormal() +
						". Last time is NULL " +
						" and now " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(time)));
			}
		} else {
			logger.info("Logging organization into empty session. Name: " + organization.getNameNorwegianNormal() + " ");
		}
		session.setAttribute("hb_trace_loggedinorgtime", new Long(time));
	}
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public void setPluginEnvironment(PluginEnvironment pluginEnvironment) {
		this.pluginEnvironment = pluginEnvironment;
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
