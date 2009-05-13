package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enonic.cms.api.plugin.HttpControllerPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

public class LogOutController extends HttpControllerPlugin {
	private String gotoUrl;
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String gotoUrl = request.getParameter("goto");
		if(gotoUrl==null) gotoUrl = this.gotoUrl;
		this.logOutUser();
		response.sendRedirect(gotoUrl);
	}
	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}
	public void logOutUser(){
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		session.setAttribute(sessionLoggedInUserVarName, null);
	}
}
