package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;
@SuppressWarnings("serial")

public class URLServiceWeb extends BasicWebService implements URLService {
	protected static final Log logger = LogFactory.getLog(URLServiceWeb.class);
	private QName isAffectedName;
	private QName translateName;
	private QName hasAccessName;
	private QName groupName;
	
	public Boolean isAffected(Url url){
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.isAffectedName, args, returnTypes);
	}
    public SingleResultUrl translateUrlUser(User user, Url url){
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		return (SingleResultUrl)invoke(this.translateName, args, returnTypes);
    }
    public SingleResultUrl translateUrlOrganization(MemberOrganization organization, Url url){
		Object[] args = new Object[] { organization, url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		return (SingleResultUrl)invoke(this.translateName, args, returnTypes);
    }
    public SingleResultUrl translateUrlUserOrganization(User user, MemberOrganization organization, Url url){
		Object[] args = new Object[] { user, organization, url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		return (SingleResultUrl)invoke(this.translateName, args, returnTypes);
    }
    public Boolean hasAccessUser(User user, Url url){
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.hasAccessName, args, returnTypes);
    }
    public Boolean hasAccessOrganization(MemberOrganization organization, Url url){
		Object[] args = new Object[] { organization, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.hasAccessName, args, returnTypes);
    }
    public Boolean hasAccessUserOrganization(User user, MemberOrganization organization, Url url){
		Object[] args = new Object[] { user, organization, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.hasAccessName, args, returnTypes);
    }
    public SingleResultString group(Url url){
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { SingleResultString.class };
		return (SingleResultString)invoke(this.groupName, args, returnTypes);
    }
    

    public Log getLogger() {
		return this.logger;
	}
	
	
	public void setIsAffectedName(QName isAffectedName) {
		this.isAffectedName = isAffectedName;
	}
	public void setTranslateName(QName translateName) {
		this.translateName = translateName;
	}
	public void setHasAccessName(QName hasAccessName) {
		this.hasAccessName = hasAccessName;
	}
	
	
	public void setGroupName(QName groupName) {
		this.groupName = groupName;
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
		MemberOrganization organization = new MemberOrganization();
		Url url1 = new Url();
		url1.setStringValue("http://proxy.helsebiblioteket.no/login?url=http://www.legehandboka.no");
		Url url2 = new Url();
		url2.setStringValue("http://www.g-i-n.net.proxy.helsebiblioteket.no/");
		Boolean isAffected1 = loginService.isAffected(url1);
		Boolean isAffected2 = loginService.isAffected(url2);
		SingleResultUrl translatedResult1 = loginService.translateUrlUserOrganization(user, organization, url1);
		SingleResultUrl translatedResult2 = loginService.translateUrlUserOrganization(user, organization, url2);
		Url translated1 = null;
		Url translated2 = null;
    	System.out.println("isAffected1: " + isAffected1);
    	System.out.println("isAffected2: " + isAffected2);
    	System.out.println("translated1: " + translated1.getStringValue());
    	System.out.println("translated2: " + translated2.getStringValue());
	}
	public String groupWS(Url url) {
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { String.class };
		Object result = invoke(this.groupName, args, returnTypes);
		return (result != null) ? (String) result : null;
	}
}
