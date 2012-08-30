package no.helsebiblioteket.evs.plugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.domain.LoggedInUser;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInUserResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.evs.plugin.result.ResultHandler;

import com.enonic.cms.api.plugin.PluginEnvironment;
import com.enonic.cms.api.plugin.ext.http.HttpController;

public final class LogInController extends HttpController {
	private final Log logger = LogFactory.getLog(getClass());
	private String sessionLoggedInUserVarName = "hbloggedinuser";
	private String resultSessionVarName;
	private LoginService loginService;
	private Map<String, String> parameterNames;
	private PluginEnvironment pluginEnvironment;
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
   		this.login(request, response);
    	this.logger.info("LogInController DONE");
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		Document result = translator.newDocument();
		Element element = result.createElement(this.resultSessionVarName);
		
    	String username = request.getParameter(this.parameterNames.get("username"));
    	String password = request.getParameter(this.parameterNames.get("password"));
    	if(username == null) { username = ""; }
    	if(password == null) { password = ""; }
    	username = username.trim();
    	password = password.trim();
    	String redirectTo = null;
       	if(username.length() != 0 && password.length() != 0){
       		LoggedInUserResult resultUser = this.loginService.loginUserByUsernamePassword(username, password);
       		if( ! resultUser.isSuccess()){
        		makeXML(username, password, result, element);
        		String from = request.getParameter(this.parameterNames.get("from"));
        		redirectTo = from;
       		} else {
	       		// Found user!
       			HttpSession session = pluginEnvironment.getCurrentSession(); 
       			//sessionLogging(request, username, session);
       			session.setAttribute(this.sessionLoggedInUserVarName, resultUser.getUser());

       			element.appendChild(result.createElement("success"));
	       		String gotoUrl = request.getParameter(this.parameterNames.get("goto"));
	       		redirectTo = gotoUrl;
        	}
       	} else {
    		makeXML(username, password, result, element);
    		String from = request.getParameter(this.parameterNames.get("from"));
    		redirectTo = from;
       	}
		result.appendChild(element);
		ResultHandler.setResult(this.resultSessionVarName, result);
		// redirect must be done after "setResult". Or else: java.lang.IllegalStateException
		if (redirectTo != null) {
			response.sendRedirect(redirectTo);
		} else {
			logger.warn("tried to log in user, but do not know where to redirect");
		}
	}
	
	private void makeXML(String username, String password, Document document, Element element) {
		boolean lookup = true;
		
		Element values = document.createElement("values");
		values.appendChild(UserToXMLTranslator.element(document, "username", username));
		values.appendChild(UserToXMLTranslator.element(document, "password", password));
		element.appendChild(values);

		Element messages = document.createElement("messages");
		if(username.length() == 0) {
			messages.appendChild(UserToXMLTranslator.element(document, "username", "NO_USERNAME"));
			lookup = false;
		}
		if(password.length() == 0) {
			messages.appendChild(UserToXMLTranslator.element(document, "password", "NO_PASSWORD"));
			lookup = false;
		}
		element.appendChild(messages);

		Element summary = document.createElement("summary");
		if(lookup){
			summary.appendChild(UserToXMLTranslator.element(document, "summary", "UNKNOWN_USER"));
		}
		element.appendChild(summary);
	}
	
	private void sessionLogging(HttpServletRequest request, String username,
			HttpSession session) {
		// jms, 13.01.2011: sanity check to handle login/session mixup problem.
		// if a user already exist in session we probably are messing with someone 
		// elses session ...
		logger.info("Start login authenticated user with username " + username + " into session id " + session.getId() + " created at " + session.getCreationTime());
		
		HttpSession requestSession = request.getSession();
		if(requestSession == null){
			logger.info("requestSession is NULL for user " + username + 
					" while pluginSession has id " + session.getId() + " and is created at " + session.getCreationTime());
		} else if(requestSession.getId() == null ){
			logger.info("requestSession has ID NULL for user " + username + 
					" while pluginSession has id " + session.getId() + " and is created at " + session.getCreationTime());
			
		} else if( ! requestSession.getId().equals(session.getId())){
			logger.info("requestSession is HAS DIFFERENT ID for user " + username + " and has session id " + requestSession.getId() + " and is created at " + requestSession.getCreationTime() +
					" while pluginSession has id " + session.getId() + " and is created at " + session.getCreationTime());
		} else if(requestSession != session){
			logger.info("requestSession HAS SAME ID AS requestSession for user " + username + " and has session id " + requestSession.getId() + " and is created at " + requestSession.getCreationTime() +
					" while pluginSession has id " + session.getId() + " and is created at " + session.getCreationTime());
		} else {
			logger.info("requestSession IS IDENTICAL WITH requestSession for user " + username + " where session id " + requestSession.getId() + " and is created at " + requestSession.getCreationTime());
		}
		
		long time = System.currentTimeMillis();
		LoggedInUser alreadyLoggedInUser = (LoggedInUser) session.getAttribute(this.sessionLoggedInUserVarName);
		Long lastTime = (Long) session.getAttribute("hb_trace_loggedinusertime");
		if (alreadyLoggedInUser != null) {
			if(lastTime != null){
				if(lastTime.longValue() >= time - 1000){
					logger.info("Logging user into very recent session. Name: " + username +
							". Session currently occupied by " + alreadyLoggedInUser.getUsername() +
							". Last time: " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(lastTime)) +
							" and now " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(time)));
				} else {
					logger.info("Logging user into older session. Name: " + username +
							". Session currently occupied by " + alreadyLoggedInUser.getUsername() +
							". Last time: " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(lastTime)) +
							" and now " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(time)));
				}
			} else {
				// Never happens?
				logger.info("Logging user into session with NULL time. Name: " + username +
						". Session currently occupied by " + alreadyLoggedInUser.getUsername() +
						". Last time is NULL " +
						" and now " + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(time)));
			}
		} else {
			logger.info("Logging user into empty session. Name: " + username + ".");
		}
		session.setAttribute("hb_trace_loggedinusertime", new Long(time));
	}
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public void setParameterNames(Map<String, String> parameterNames) {
		this.parameterNames = parameterNames;
	}
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
	}
	public void setPluginEnvironment(PluginEnvironment pluginEnvironment) {
		this.pluginEnvironment = pluginEnvironment;
	}
}
