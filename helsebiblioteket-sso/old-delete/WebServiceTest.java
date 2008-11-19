package no.helsebiblioteket.evs.plugin;

import javax.xml.namespace.QName;

import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.webservice.LoginServiceWeb;
import no.helsebiblioteket.domain.IpAddress;
import no.helsebiblioteket.domain.Organization;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.springframework.ws.client.core.WebServiceMessageCallback;
//import org.springframework.ws.client.core.WebServiceTemplate;
//import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
//import org.springframework.ws.transport.WebServiceMessageSender;
//import org.springframework.ws.transport.http.CommonsHttpMessageSender;
//import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;
//import org.springframework.ws.transport.jms.JmsMessageSender;

public class WebServiceTest {
	protected static final Log logger = LogFactory.getLog(WebServiceTest.class);
	private LoginService loginServiceBean;
	
	public static void main(String[] args) {
		WebServiceTest test = new WebServiceTest();
		test.loginServiceBean = new LoginServiceWeb();
		
		// TODO: This is done with Spring!
//		test.LoginServicebean.
		
		
		RPCServiceClient serviceClient;
		try {
			serviceClient = new RPCServiceClient();
			EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis2/services/loginService");
			Options options = serviceClient.getOptions();
		    options.setTo(targetEPR);
		    QName loginName = new QName("http://service.admin.helsebiblioteket.no", "logInIpAddress");
		    IpAddress address = new IpAddress();
		    address.setAddress("111.222.333.444");

		    Object[] loginIpAddressArgs = new Object[] { address };
	        Class[] returnTypes = new Class[] { Organization.class };
//		    serviceClient.invokeRobust(loginName, loginIpAddressArgs);
	        Object[] response = serviceClient.invokeBlocking(loginName,
	                loginIpAddressArgs, returnTypes);
	        Organization organization = (Organization) response[0];
	        if (organization == null) {
	            System.out.println("No result!");
	        } else {
	        	System.out.println("Org: " + organization.getName());
	        }

		    
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
	    //		
//		
//		String wsdlURL = "http://localhost:8080/helsebiblioteket-administration/ws/userService/userService.wsdl";
//		String namespace = "http://mycompany.com/hr/definitions";
//		String serviceName = "userService";
//		
//		String msg = "<message xmlns=\"http://tempuri.org\">Hello Web Service World</message>";
//		StringWriter out = new StringWriter();
//		StreamSource source = new StreamSource(new StringReader(msg));
//		StreamResult result = new StreamResult(out);
//		
//		
//		SaajSoapMessageFactory messageFactory = null;
//		try {
//			messageFactory = new SaajSoapMessageFactory(MessageFactory.newInstance());
//		} catch (SOAPException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);
//		webServiceTemplate.setDefaultUri(wsdlURL);
//		WebServiceMessageSender ms = new HttpUrlConnectionMessageSender();
//		webServiceTemplate.setMessageSender(ms);
//		
//		webServiceTemplate.sendSourceAndReceiveToResult(wsdlURL,
//				source, result);
//		
//		logger.info("WEBSERVICECALL - RESULT: " + out.toString());
//		
////		ActiveMQC
////		JmsMessageSender jmsMS = new JmsMessageSender();
//		
//		
//		
//		
//		
		
		
		
//		webServiceTemplate.sendAndReceive(new , responseCallback)
		
//		QName serviceQN = new QName(namespace, serviceName);
//		        
//		ServiceFactory serviceFactory = ServiceFactory.newInstance();
		/* The "new URL(wsdlURL)" parameter is optional */
//		Service service = serviceFactory.createService(new URL(wsdlURL), serviceQN);
	}
}
