package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
@SuppressWarnings("serial")

public class URLServiceWeb implements URLService {
	protected static final Log logger = LogFactory.getLog(URLServiceWeb.class);
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private Options options;
	private QName isAffectedName;
	private QName translateName;
	public void init(){
		options = serviceClient.getOptions();
	    options.setTo(targetEPR);
	}
	public Boolean isAffected(Url url) {
		// FIXME: Testing!
		boolean res = false;
		res = true;
		if(true) return res;
		
		
		
		
		
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { Boolean.class };
		try {
			Object[] response = serviceClient.invokeBlocking(this.isAffectedName, args, returnTypes);
			Boolean result = (Boolean) response[0];
			return result;
		} catch (AxisFault e) {
			// TODO What do we do here?
			logger.error(e);
			return false;
		}
	}
	public Url translate(User user, Organization organization, Url url) {
		// FIXME: Testing!
		Url res = new Url();
		res.setValue("proxy:" + url.getValue());
		if(true) return res;

		Object[] args = new Object[] { user, organization, url };
		Class[] returnTypes = new Class[] { Url.class };
		try {
			Object[] response = serviceClient.invokeBlocking(this.translateName, args, returnTypes);
			Url result = (Url) response[0];
			return result;
		} catch (AxisFault e) {
			// TODO What do we do here?
			logger.error(e);
			return null;
		}
	}
	public String group(Url url) {
		// FIXME: Testing!
		String res = "vg";
		return res;
	}
	public Boolean hasAccess(User user, Organization organization, Url url) {
		// FIXME: Testing!
		boolean res = false;
		res = true;
		return res;
	}
	public void setServiceClient(RPCServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}
	public void setTargetEPR(EndpointReference targetEPR) {
		this.targetEPR = targetEPR;
	}
	public void setIsAffectedName(QName isAffectedName) {
		this.isAffectedName = isAffectedName;
	}
	public void setTranslateName(QName translateName) {
		this.translateName = translateName;
	}
	public static void main(String[] args) throws Exception {
		QName isAffectedName = new QName("http://service.admin.helsebiblioteket.no", "isAffected");
		QName translateName = new QName("http://service.admin.helsebiblioteket.no", "translate");
		EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis2/services/urlService");
		URLServiceWeb loginService = new URLServiceWeb();
		RPCServiceClient serviceClient = new RPCServiceClient();
		loginService.setServiceClient(serviceClient);
		loginService.setTargetEPR(targetEPR);
		loginService.setIsAffectedName(isAffectedName);
		loginService.setTranslateName(translateName);
		loginService.init();
		
		User user = new User();
		Organization organization = new Organization();
		Url url1 = new Url();
		url1.setValue("http://proxy.helsebiblioteket.no/login?url=http://www.legehandboka.no");
		Url url2 = new Url();
		url2.setValue("http://www.g-i-n.net.proxy.helsebiblioteket.no/");
		Boolean isAffected1 = loginService.isAffected(url1);
		Boolean isAffected2 = loginService.isAffected(url2);
		Url translated1 = loginService.translate(user, organization, url1);
		Url translated2 = loginService.translate(user, organization, url2);
    	System.out.println("isAffected1: " + isAffected1);
    	System.out.println("isAffected2: " + isAffected2);
    	System.out.println("translated1: " + translated1.getValue());
    	System.out.println("translated2: " + translated2.getValue());
	}
}
