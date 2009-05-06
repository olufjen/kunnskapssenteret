package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.cache.key.CacheKey;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;
@SuppressWarnings("serial")

public class URLServiceWeb extends BasicWebService implements URLService {
	protected static final Log logger = LogFactory.getLog(URLServiceWeb.class);
	private QName isAffectedName;
	private QName translateUrlUserName;
	private QName translateUrlOrganizationName;
	private QName translateUrlUserOrganizationName;
	private QName hasAccessUserName;
	private QName hasAccessOrganizationUserName;
	private QName hasAccessOrganizationName;
	private QName hasAccessUserOrganizationName;
	private QName hasAccessOrganizationUserOrganizationName;
	private QName accessTypeForUserAndMemberOrganizationName;
	
	private QName groupName;
	
	@Override
	public Boolean isAffected(Url url){
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invokeCached(CacheKey.affectedUrlCache, url.getStringValue(), this.isAffectedName, args, returnTypes);
	}
	@Override
    public SingleResultUrl translateUrlUser(User user, Url url){
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		return (SingleResultUrl)invoke(this.translateUrlUserName, args, returnTypes);
    }
	@Override
    public SingleResultUrl translateUrlOrganization(MemberOrganization organization, Url url){
		Object[] args = new Object[] { organization, url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		return (SingleResultUrl)invoke(this.translateUrlOrganizationName, args, returnTypes);
    }
	@Override
    public SingleResultUrl translateUrlUserOrganization(User user, MemberOrganization organization, Url url){
		Object[] args = new Object[] { user, organization, url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		String key = (
				((user != null) ? (user.getId() + "-") : "") + 
				((organization) != null ? (organization.getOrganization().getId() + "-") : "") +
				url
				);
		return (SingleResultUrl) invokeCached(CacheKey.translateUrlUserOrganizationCache, key, this.translateUrlUserOrganizationName, args, returnTypes);
    }
	@Override
    public Boolean hasAccessUser(User user, Url url){
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.hasAccessUserName, args, returnTypes);
    }
	@Override
	public Boolean hasAccessOrganizationUser(OrganizationUser user, Url url) {
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.hasAccessOrganizationUserName, args, returnTypes);
	}
	@Override
    public Boolean hasAccessOrganization(MemberOrganization organization, Url url){
		Object[] args = new Object[] { organization, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.hasAccessOrganizationName, args, returnTypes);
    }
	@Override
    public Boolean hasAccessUserOrganization(User user, MemberOrganization organization, Url url){
		Object[] args = new Object[] { user, organization, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean) invoke(this.hasAccessUserOrganizationName, args, returnTypes);
    }
	@Override
	public Boolean hasAccessOrganizationUserOrganization(OrganizationUser user, MemberOrganization organization, Url url) {
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean) invoke(this.hasAccessOrganizationUserOrganizationName, args, returnTypes);
	}
	@Override
    public SingleResultString group(Url url){
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { SingleResultString.class };
		return (SingleResultString)invoke(this.groupName, args, returnTypes);
    }

	@Override
    public AccessType getAccessTypeForUserAndMemberOrganization(User user, MemberOrganization memberOrganization, Url url) {
		Object[] args = new Object[] { user, memberOrganization, url };
		Class[] returnTypes = new Class[] { AccessType.class };
		String key = ( 
				((user) != null ? (user.getId() + "-") : "") +
				((memberOrganization) != null ? (memberOrganization.getOrganization().getId() + "-") : "") +
				url
				);
		return (AccessType) invokeCached(CacheKey.accessTypeForUserAndMemberOrganizationCache, key, this.accessTypeForUserAndMemberOrganizationName, args, returnTypes);
	}
    
    public Log getLogger() {
		return this.logger;
	}
	
	public void setIsAffectedName(QName isAffectedName) {
		this.isAffectedName = isAffectedName;
	}
	public void setTranslateUrlUserName(QName translateUrlUserName) {
		this.translateUrlUserName = translateUrlUserName;
	}
	public void setTranslateUrlOrganizationName(QName translateUrlOrganizationName) {
		this.translateUrlOrganizationName = translateUrlOrganizationName;
	}
	public void setTranslateUrlUserOrganizationName(QName translateUrlUserOrganizationName) {
		this.translateUrlUserOrganizationName = translateUrlUserOrganizationName;
	}
	public void setHasAccessUserName(QName hasAccessUserName) {
		this.hasAccessUserName = hasAccessUserName;
	}
	public void setHasAccessOrganizationName(QName hasAccessOrganizationName) {
		this.hasAccessOrganizationName = hasAccessOrganizationName;
	}
	public void setHasAccessUserOrganizationName(QName hasAccessUserOrganizationName) {
		this.hasAccessUserOrganizationName = hasAccessUserOrganizationName;
	}
	public void setHasAccessOrganizationUserOrganizationName(QName hasAccessOrganizationUserOrganizationName) {
		this.hasAccessOrganizationUserOrganizationName = hasAccessOrganizationUserOrganizationName;
	}
	public void setGroupName(QName groupName) {
		this.groupName = groupName;
	}
	public void setAccessTypeForUserAndMemberOrganizationName(QName accessTypeForUserAndMemberOrganizationName) {
		this.accessTypeForUserAndMemberOrganizationName = accessTypeForUserAndMemberOrganizationName;
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
		loginService.setTranslateUrlOrganizationName(translateName);
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
}