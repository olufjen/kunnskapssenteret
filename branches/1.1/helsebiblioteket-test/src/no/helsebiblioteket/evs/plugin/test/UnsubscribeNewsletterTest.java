package no.helsebiblioteket.evs.plugin.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import no.helsebiblioteket.evs.plugin.UnsubscribeNewsletterController;

public class UnsubscribeNewsletterTest {
	private final Log logger = LogFactory.getLog(getClass());
	
	@Test
	public void testUnsubscribe() throws Exception {
		UnsubscribeNewsletterController controllerPlugin = new UnsubscribeNewsletterController();
		HashMap fields = new HashMap<String, String>();
		fields.put("subscriptionKey", "subscriptionkey");
		HashMap urlParams = new HashMap<String, String>();
		fields.put("subscriptionKey", "hbkey");		
		controllerPlugin.setParameterNamesFields(fields);
		controllerPlugin.setParameterNamesUrlParams(urlParams);
		HttpServletRequest request = new MockHttpServletRequest();
		HttpServletResponse response = new MockHttpServletResponse();
		request.setAttribute("hbkey", "");
		controllerPlugin.handleRequest(request, response);
		Assert.assertNotNull("document was null", controllerPlugin.document);
		logger.info(controllerPlugin.document);
	}
}
