package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public class LogOutController extends HttpControllerPlugin {
//	private final Log logger = LogFactory.getLog(getClass());
	private String loggedInSessionVarName;
	private String gotoUrl;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoggedInFunction.logOut(this.loggedInSessionVarName, request.getSession());
		response.sendRedirect(this.gotoUrl);
	}
	public void setLoggedInSessionVarName(String loggedInSessionVarName) {
		this.loggedInSessionVarName = loggedInSessionVarName;
	}
	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}
}
