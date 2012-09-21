package no.helsebiblioteket.evs.plugin;

import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.enonic.cms.api.plugin.PluginEnvironment;

import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.translator.UserToXMLTranslator;
import no.helsebiblioteket.evs.plugin.result.ResultHandler;

/*
 * @author Leif Torger Grøndahl, kunnnskapssenteret.no
 * 
 */
public final class UnsubscribeNewsletterController extends EcmsController {
	private final Log logger = LogFactory.getLog(getClass());
	private UserService userService;
	
	private String gotoUrl;

	String subscriptionKey;
	
	private static enum localParameterNamesUrlParams {
		subscriptionKey
	}
	
	private static enum localParameterNamesFields {
		subscriptionKey
	}
	
	UserToXMLTranslator translator;
	
	private void initXmlResult() throws ParserConfigurationException {
		translator = new UserToXMLTranslator();
		document = translator.newDocument();
		element = document.createElement(this.resultSessionVarName);
	}
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		subscriptionKey = request.getParameter(parameterNamesFields.get(localParameterNamesFields.subscriptionKey.name()));
		if (null == subscriptionKey || "".equals(subscriptionKey)) {
			subscriptionKey = request.getParameter(parameterNamesUrlParams.get(localParameterNamesUrlParams.subscriptionKey.name()));
		}
		subscriptionKey = (subscriptionKey != null && !"".equals(subscriptionKey)) ? URLDecoder.decode(subscriptionKey, "UTF-8") : subscriptionKey;
		initXmlResult();
		messagesMap.clear();
		fieldValuesMap.clear();
		
		if (subscriptionKey == null || "".equals(subscriptionKey)) {
			messagesMap.put(parameterNamesFields.get(localParameterNamesFields.subscriptionKey.name()), "NO_VALUE");
			fieldValuesMap.put(parameterNamesFields.get(localParameterNamesFields.subscriptionKey.name()), "");
			populateXmlResult(document, element, false, false);
		} else {
			fieldValuesMap = new HashMap<String, String>();
			fieldValuesMap.put(parameterNamesFields.get(localParameterNamesFields.subscriptionKey.name()), subscriptionKey);
			try {
		    	if (userService.unsubscribeNewsletter(subscriptionKey)) {
		    		populateXmlResult(document, element, true, false);
		    	} else {
		    		messagesMap.put(parameterNamesFields.get(localParameterNamesFields.subscriptionKey.name()), "NOT_VALID");
		    		populateXmlResult(document, element, true, true);
		    	}
			} catch (Exception e) {
				logger.error("Error occurred trying to unsubscribe user with subscription key '" + subscriptionKey + "'", e);
	    		messagesMap.put("genericerror", "UNKNOWN_ERROR");
				populateXmlResult(document, element, true, true);
			}
		}
		ResultHandler.setResult(this.resultSessionVarName, document, request.getSession());
		response.sendRedirect(gotoUrl);
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getGotoUrl() {
		return gotoUrl;
	}

	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}
}