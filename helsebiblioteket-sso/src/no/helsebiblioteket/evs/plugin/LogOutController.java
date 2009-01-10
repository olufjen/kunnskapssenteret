package no.helsebiblioteket.evs.plugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.enonic.cms.api.plugin.HttpControllerPlugin;
import com.enonic.cms.api.plugin.PluginEnvironment;

public class LogOutController extends HttpControllerPlugin {
//	private final Log logger = LogFactory.getLog(getClass());
	private String gotoUrl;
	private LoggedInFunction loggedInFunction;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession();
		
		// TODO: Config!
		String gotoUrl = request.getParameter("goto");
		if(gotoUrl==null) gotoUrl = this.gotoUrl;
		
		loggedInFunction.logOut();
		response.sendRedirect(gotoUrl);
	}
	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}
	public void setLoggedInFunction(LoggedInFunction loggedInFunction) {
		this.loggedInFunction = loggedInFunction;
	}
}
