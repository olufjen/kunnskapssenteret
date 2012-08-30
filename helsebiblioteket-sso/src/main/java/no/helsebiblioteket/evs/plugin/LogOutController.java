package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enonic.cms.api.plugin.PluginEnvironment;
import com.enonic.cms.api.plugin.ext.http.HttpController;

public class LogOutController extends HttpController {
	private String gotoUrl;
	private PluginEnvironment pluginEnvironment;
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String sessionLoggedInOrganizationVarName = "hbloggedinorganization";
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String gotoUrl = request.getParameter("goto");
		if(gotoUrl==null) gotoUrl = this.gotoUrl;
		String logoutUserString = request.getParameter("logoutuser");
		String logoutOrganizationString = request.getParameter("logoutorganization");
		boolean logoutUser = (logoutUserString != null && Boolean.valueOf(logoutUserString));
		boolean logoutOrganization = (logoutOrganizationString != null && Boolean.valueOf(logoutOrganizationString));
		if (logoutUser) {
			this.logOutUser();
		}
		if (logoutOrganization) {
			logOutOrganization();
		}
		response.sendRedirect(gotoUrl);
	}
	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}
	public void setPluginEnvironment(PluginEnvironment pluginEnvironment) {
		this.pluginEnvironment = pluginEnvironment;
	}
	public void logOutUser(){
		
		HttpSession session = pluginEnvironment.getCurrentSession(); 
		session.setAttribute(sessionLoggedInUserVarName, null);
	}
	public void logOutOrganization() {
		HttpSession session = pluginEnvironment.getCurrentSession(); 
		session.setAttribute(sessionLoggedInOrganizationVarName, null);
	}
}