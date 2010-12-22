package no.helsebiblioteket.evs.plugin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import no.helsebiblioteket.admin.translator.UserToXMLTranslator;

import com.enonic.cms.api.plugin.HttpControllerPlugin;

/*
 * @author Leif Torger Grøndahl, kunnnskapssenteret.no
 * 
 */
public abstract class EcmsController extends HttpControllerPlugin {
//	private final Log logger = LogFactory.getLog(getClass());
	protected String resultSessionVarName;
	protected Map<String, String> parameterNamesUrlParams;
	protected Map<String, String> parameterNamesFields;
	Document document;
	Element element;
	protected Map<String, String> messagesMap = new HashMap<String, String>();
	protected Map<String, String> fieldValuesMap = new HashMap<String, String>();
	
	protected void populateXmlResult(Document document, Element element, boolean operationPerformed, boolean operationFailed) {
		Element status = document.createElement("status");
		if (operationPerformed) {
			status.appendChild(UserToXMLTranslator.element(document, "operationperformed", "true"));
			if(operationFailed) {
				status.appendChild(UserToXMLTranslator.element(document, "operationsucceeded", "false"));
			} else {
				status.appendChild(UserToXMLTranslator.element(document, "operationsucceeded", "true"));
			}
		} else {
			status.appendChild(UserToXMLTranslator.element(document, "operationperformed", "false"));
		}
		element.appendChild(status);
		
		if (messagesMap != null && messagesMap.size() > 0) {
			Element messages = document.createElement("messages");
			String messageValue = null;
			for (String messageKey : messagesMap.keySet()) {
				messageValue = messagesMap.get(messageKey);
				messages.appendChild(UserToXMLTranslator.element(document, messageKey, messageValue));
			}
			element.appendChild(messages);
		}
		
		if (fieldValuesMap != null && fieldValuesMap.size() > 0) {
			Element fieldValues = document.createElement("fieldvalues");
			String fieldValue = null;
			for (String fieldKey : fieldValuesMap.keySet()) {
				fieldValue = fieldValuesMap.get(fieldKey);
				fieldValues.appendChild(UserToXMLTranslator.element(document, fieldKey, fieldValue));
			}
			element.appendChild(fieldValues);
		}
		document.appendChild(element);
	}
	
	public void setResultSessionVarName(String resultSessionVarName) {
		this.resultSessionVarName = resultSessionVarName;
	}
	public void setParameterNamesUrlParams(Map<String, String> parameterNamesUrlParams) {
		this.parameterNamesUrlParams = parameterNamesUrlParams;
	}
	public void setParameterNamesFields(Map<String, String> parameterNamesFields) {
		this.parameterNamesFields = parameterNamesFields;
	}

	@Override
	public abstract void handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception;
}