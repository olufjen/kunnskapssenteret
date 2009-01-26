package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
@SuppressWarnings("serial")

public class URLServiceWeb extends BasicWebService implements URLService {
	protected static final Log logger = LogFactory.getLog(URLServiceWeb.class);
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private Options options;
	private QName isAffectedName;
	private QName translateName;
	private QName hasAccessName;
	private QName groupName;
	
	public void init(){
		options = serviceClient.getOptions();
	    options.setTo(targetEPR);
	}
	
	public Boolean isAffected(Url url){
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.isAffectedName, args, returnTypes);
	}
    public SingleResult<Url> translate(User user, Url url){
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<Url>)invoke(this.translateName, args, returnTypes);
    }
    public SingleResult<Url> translate(Organization organization, Url url){
		Object[] args = new Object[] { organization, url };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<Url>)invoke(this.translateName, args, returnTypes);
    }
    public SingleResult<Url> translate(User user, Organization organization, Url url){
		Object[] args = new Object[] { user, organization, url };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<Url>)invoke(this.translateName, args, returnTypes);
    }
    public Boolean hasAccess(User user, Url url){
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.hasAccessName, args, returnTypes);
    }
    public Boolean hasAccess(Organization organization, Url url){
		Object[] args = new Object[] { organization, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.hasAccessName, args, returnTypes);
    }
    public Boolean hasAccess(User user, Organization organization, Url url){
		Object[] args = new Object[] { user, organization, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.hasAccessName, args, returnTypes);
    }
    public SingleResult<String> group(Url url){
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult)invoke(this.groupName, args, returnTypes);
    }
    

    public Log getLogger() {
		return this.logger;
	}
	public RPCServiceClient getServiceClient() {
		return this.serviceClient;
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
		// TODO: Move to test project!
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
		SingleResult<Url> translatedResult1 = loginService.translate(user, organization, url1);
		SingleResult<Url> translatedResult2 = loginService.translate(user, organization, url2);
		Url translated1 = null;
		Url translated2 = null;
    	System.out.println("isAffected1: " + isAffected1);
    	System.out.println("isAffected2: " + isAffected2);
    	System.out.println("translated1: " + translated1.getValue());
    	System.out.println("translated2: " + translated2.getValue());
	}
}
