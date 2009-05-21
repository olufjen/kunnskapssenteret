package no.helsebiblioteket.evs.plugin;

import java.util.Enumeration;
import java.util.StringTokenizer;

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
	private LoginService loginService;
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	public LogInInterceptor(){
		logger.info("HttpInterceptorPluginAutoLoginHelsebiblioteket CREATED");
	}
	public void postHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
	}
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoggedInOrganization organization = this.loggedInOrganization();
		if(organization == null){
			IpAddress ipAddress = new IpAddress();
	    	ipAddress.setAddress(getXforwardedForOrRemoteAddress(request));
	    	if(IpAddressValidator.getInstance().isValidIPAddress(ipAddress.getAddress())){
		    	LoggedInOrganizationResult res = this.loginService.loginOrganizationByIpAddress(ipAddress);
	    		if (null == res) {
	    			logger.error("loginService.loginOrganizationByIpAddress returned null for IP '" + ipAddress + "'. This was not expected, but might happen if a request is sendt to the server just after the appserver has started and before the LogInInterceptor plugin has been initiated.");
	    		}
	    		else if(res.isSuccess()){
		    		this.logInOrganization(res.getOrganization());
		    	}
	    	}
		}
		return true;
	}
    @SuppressWarnings("unchecked")
	public static String getXforwardedForOrRemoteAddress(HttpServletRequest request) {
    	String XFF = "X-Forwarded-For";
        String ret = null;
        Enumeration en = request.getHeaderNames();

        while (en != null && en.hasMoreElements()){

            Object o = en.nextElement();
            if (o instanceof String) {

                String h = (String)o;
                if (XFF.equalsIgnoreCase(h)) {
                    String xFf = request.getHeader(h);
                    logger.debug("Header " + XFF + "=" + xFf);

                    StringTokenizer st = new StringTokenizer(xFf, ",");
                    // allways the first element
                    if (st.hasMoreElements()) {

                        Object e = st.nextElement();
                        if (e instanceof String) {
                            ret = (String)e;
                            logger.debug("Remote " + XFF + " address=" + ret);
                            break;
                        }
                    }
                }
            }
        }
        return (ret != null ? ret : request.getRemoteAddr());
    }
	public void logInOrganization(LoggedInOrganization organization){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		session.setAttribute(sessionLoggedInOrganizationVarName, organization);
	}
	private LoggedInOrganization loggedInOrganization(){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		return (LoggedInOrganization)session.getAttribute(sessionLoggedInOrganizationVarName);
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
