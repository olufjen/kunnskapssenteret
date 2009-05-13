package no.helsebiblioteket.evs.plugin.result;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.w3c.dom.Element;

import com.enonic.cms.api.plugin.PluginEnvironment;

public class ResultHandler {
	// TODO Fase2: Set in bean, but global?
	private static String sessionResultsVarName = "hbresults";

	@SuppressWarnings("unchecked")
	public static void setResult(String key, org.w3c.dom.Document result) {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 

		Map<String, org.w3c.dom.Document> userMap = (Map<String, org.w3c.dom.Document>)
			session.getAttribute(sessionResultsVarName);
		
		if(userMap == null){
			userMap = new HashMap<String, org.w3c.dom.Document>();
			PluginEnvironment.getInstance().getCurrentSession().setAttribute(sessionResultsVarName, userMap);
		}
		userMap.put(key, result);
	}
	@SuppressWarnings("unchecked")
	public static Document getResult(String key) throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		HttpSession session = PluginEnvironment.getInstance().getCurrentSession(); 
		Map<String, org.w3c.dom.Document> userMap = (Map<String, org.w3c.dom.Document>)
			session.getAttribute(sessionResultsVarName);
		
		org.w3c.dom.Document result = null;
		if(userMap != null){
			result = userMap.get(key);
			userMap.remove(key);
		}
		if(result == null){
			result = emptyXML(key);
		}
		return XMLTranslator.translate(result);
	}
	private static org.w3c.dom.Document emptyXML(String key) throws ParserConfigurationException {
		UserToXMLTranslator translator = new UserToXMLTranslator();
		org.w3c.dom.Document result = translator.newDocument();
		Element element = result.createElement(key);
		Element empty = result.createElement("empty");
		element.appendChild(empty);
		result.appendChild(element);
		return result;
	}
}
