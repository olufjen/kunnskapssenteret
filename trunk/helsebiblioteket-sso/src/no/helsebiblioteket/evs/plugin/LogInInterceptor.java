package no.helsebiblioteket.evs.plugin;

import java.util.Enumeration;
import java.util.StringTokenizer;

import com.enonic.cms.api.plugin.HttpInterceptorPlugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class LogInInterceptor extends HttpInterceptorPlugin {
	private static final Log logger = LogFactory.getLog(LogInInterceptor.class);
	private LoginService loginService;
	private LoggedInFunction loggedInFunction;
	public LogInInterceptor(){
		System.out.println("HttpInterceptorPluginAutoLoginHelsebiblioteket CREATED");
		logger.info("HttpInterceptorPluginAutoLoginHelsebiblioteket CREATED");
	}
	public void postHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
	}
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberOrganization organization = this.loggedInFunction.loggedInOrganization();
		User user = this.loggedInFunction.loggedInUser();
		if(organization == null && user == null){
			IpAddress ipAddress = new IpAddress();
	    	ipAddress.setAddress(getXforwardedForOrRemoteAddress(request));
	    	SingleResultMemberOrganization res = this.loginService.loginOrganizationByIpAddress(ipAddress);
    		if(res instanceof ValueResultMemberOrganization){
	    		loggedInFunction.logInOrganization(((ValueResultMemberOrganization)res).getValue());
	    	}
		}
		return true;
	}
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
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public void setLoggedInFunction(LoggedInFunction loggedInFunction) {
		this.loggedInFunction = loggedInFunction;
	}
}
