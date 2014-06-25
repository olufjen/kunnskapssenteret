package no.helsebiblioteket.evs.plugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.plugin.PluginEnvironment;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.LoggedInOrganization;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInOrganizationResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.validator.IpAddressValidator;

/*
 * @author Leif Torger Grøndahl, leiftorger@gmail.com
 */
public class LogInOrganization {
	private static final Log logger = LogFactory.getLog(LogInOrganization.class);
	public static final String XForwardedForHeaderName = "X-Forwarded-For";
	private LoginService loginService;
	private PluginEnvironment pluginEnvironment;
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	private static boolean useXForwardedForHeader = false;
	public void setUseXForwardedForHeader(boolean useXForwardedForHeader) {
		LogInOrganization.useXForwardedForHeader = useXForwardedForHeader;
	}

	public LogInOrganization() {
	}

	public void logIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        if (LogInOrganization.useXForwardedForHeader) {
	        while (en != null && en.hasMoreElements()){
	            Object o = en.nextElement();
	            if (o instanceof String) {
	                String h = (String)o;
	                if (LogInOrganization.XForwardedForHeaderName.equalsIgnoreCase(h)) {
	                    String xFf = request.getHeader(h);
	                    logger.debug("Header " + LogInOrganization.XForwardedForHeaderName + "=" + xFf);
	                    if (null != xFf) {
		                    // According to spec xff-header should contain address separated by ", ", but is this always the case?
		                    // removing all whitespaces before split just in case whitespaces are not always set.
		                    xFf = xFf.replaceAll("\\s+", "");
		                    String xFfArray[] = xFf.split(",");
		                    // Reading the LAST element in xff-header based on Basefarms recommendation
		                    if (xFfArray != null && (xFfArray.length > 0)) {
		                    	ret = (String) xFfArray[(xFfArray.length - 1)];
		                    	logger.debug("Remote " + LogInOrganization.XForwardedForHeaderName + " address=" + ret);
		                    }
		                    break;
	                    }
	                }
	            }
	        }
        }
        ret = ret != null ? ret : request.getRemoteAddr();
        logger.info("User/Org IP (useXForwardedForHeader=" + useXForwardedForHeader + "): " + ret);
        return ret;
	}

	public void logInOrganization(LoggedInOrganization organization, LoggedInOrganization alreadyLoggedInOrganization, HttpServletRequest request){
		HttpSession session = pluginEnvironment.getCurrentSession();
		session.setAttribute(sessionLoggedInOrganizationVarName, new LoggedInOrganizationWrapper(organization));
	}

	private LoggedInOrganization loggedInOrganization(){
		HttpSession session = pluginEnvironment.getCurrentSession();
        LoggedInOrganizationWrapper wrapped = (LoggedInOrganizationWrapper)session.getAttribute(sessionLoggedInOrganizationVarName);
        return wrapped != null ? wrapped.getWrapped() : null;
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
        logger.info("Header " + LogInOrganization.XForwardedForHeaderName + "=" + xFf);
        // xff-header should contain address separated by ", ", but is this always the case?
        // removing all whitespaces before split just in case whitespaces are not always set.
        xFf = xFf.replaceAll("\\s+", "");
        String xFfArray[] = xFf.split(",");
        // Reading the LAST element in xff-header based on Basefarms recommendation
        if (xFfArray != null && (xFfArray.length > 0)) {
        	ret = (String) xFfArray[(xFfArray.length - 1)];
        	logger.info("Remote " + LogInOrganization.XForwardedForHeaderName + " address=" + ret);
        }
	}
}
