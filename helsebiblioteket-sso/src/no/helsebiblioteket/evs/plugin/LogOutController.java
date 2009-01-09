package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

public class LogOutController extends HttpControllerPlugin {
//	private final Log logger = LogFactory.getLog(getClass());
	private String gotoUrl;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoggedInFunction.logOut();
		response.sendRedirect(this.gotoUrl);
	}
	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}
}
