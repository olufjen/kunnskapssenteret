package no.helsebiblioteket.evs.plugin;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Element;

import com.enonic.cms.api.plugin.PluginEnvironment;

public class LoggedInFunction{
	private static Map<String, Map<String, org.w3c.dom.Document>> results = new HashMap<String, Map<String,org.w3c.dom.Document>>();
	private static Map<String, Object> loggedIn = new HashMap<String, Object>();
	public static void logIn(String varName, HttpSession session, Object user){
		// TODO: Do this in a much better way!
		// FIXME: Replace names!		
//		String longName = "kunnskapssenteret\\leift";
//		String shortName = "leift";
//		loggedIn.put(shortName, user);
//       	Client client = ClientFactory.getLocalClient();
//       	client.
//       	client.login(shortName, "");
//		session.setAttribute(varName, user);
		PluginEnvironment.getInstance().getCurrentSession().setAttribute("loggedIn", user);
	}
	public static void logOut(String varName, HttpSession session){
//       	Client client = ClientFactory.getLocalClient();
//       	String userName = client.getUserName();
//		loggedIn.remove(userName);
//       	client.logout();
//		session.setAttribute(varName, null);
		PluginEnvironment.getInstance().getCurrentSession().setAttribute("loggedIn", null);
	}
	public static Object loggedIn() {
//    	Client client = ClientFactory.getLocalClient();
//    	String name = client.getUserName();
//    	name = client.getRunAsUserName();
//		return loggedIn.get(name);
		return PluginEnvironment.getInstance().getCurrentSession().getAttribute("loggedIn");
	}
	public Document getUserAsXML() throws JDOMException, IOException, ParserConfigurationException, TransformerException {
		return translate(LoggedInDataController.printUser());
	}
	public static void setResult(String key, StringBuffer result) {
		// FIXME: Write this now!

	}
	public static void setResult(String key, org.w3c.dom.Document result) {
//		Client client = ClientFactory.getLocalClient();
//		String username = client.getUserName();
//		Map<String, org.w3c.dom.Document> userMap = results.get(username);
		Map<String, org.w3c.dom.Document> userMap = (Map<String, org.w3c.dom.Document>) PluginEnvironment.getInstance().getCurrentSession().getAttribute("result");
		if(userMap == null){
			userMap = new HashMap<String, org.w3c.dom.Document>();
			PluginEnvironment.getInstance().getCurrentSession().setAttribute("result", userMap);
//			results.put(username, userMap);
		}
		userMap.put(key, result);

		// TODO Do this in a better way!
		
	}
	public Document getResult(String key) throws JDOMException, IOException, ParserConfigurationException, TransformerException {
//		Client client = ClientFactory.getLocalClient();
//		String username = client.getUserName();
//		Map<String, org.w3c.dom.Document> userMap = results.get(username);
		Map<String, org.w3c.dom.Document> userMap = (Map<String, org.w3c.dom.Document>) PluginEnvironment.getInstance().getCurrentSession().getAttribute("result");
		org.w3c.dom.Document result = null;
		if(userMap != null){
			result = userMap.get(key);
			// TODO: Re-insert!
//			userMap.remove(key);
		}
		if(result == null){
			result = emptyXML(key);
		}
		return translate(result);
	}
	private org.w3c.dom.Document emptyXML(String key) throws ParserConfigurationException {
		// TODO: Make initial XML for forms!
		UserToXMLTranslator translator = new UserToXMLTranslator();
		org.w3c.dom.Document result = translator.newDocument();
		Element element = result.createElement(key);
		Element empty = result.createElement("empty");
		element.appendChild(empty);
		result.appendChild(element);
		return result;
	}
	private Document translate(org.w3c.dom.Document document) throws TransformerException, JDOMException, IOException{
		// TODO: Different JDOM classes. Fix!
		Source source = new DOMSource(document);
        StringWriter stringWriter = new StringWriter();
		Result result = new StreamResult(stringWriter);
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.transform(source, result);
		
		SAXBuilder parser = new SAXBuilder();
    	StringReader sr = new StringReader(stringWriter.getBuffer().toString());
    	Document doc = parser.build(sr);
		return doc;
		
	}
}
