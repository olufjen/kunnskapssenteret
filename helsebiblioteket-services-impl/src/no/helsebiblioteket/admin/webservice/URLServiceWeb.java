package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.cache.key.CacheKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.AccessResult;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;
@SuppressWarnings("serial")

public class URLServiceWeb extends BasicWebService implements URLService {
	protected static final Log logger = LogFactory.getLog(URLServiceWeb.class);
	private QName isAffectedName;
	private QName translateUrlUserName;
	private QName translateUrlOrganizationName;
	private QName translateUrlUserOrganizationName;
	private QName translateUrlNoneName;
	private QName hasAccessUserName;
	private QName hasAccessOrganizationName;
	private QName hasAccessUserOrganizationName;
	private QName hasAccessNoneName;
	
	private QName groupName;
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isAffected(Url url){
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invokeCached(CacheKey.urlServiceWebIsAffectedCache, url.getStringValue(), this.isAffectedName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
    public SingleResultUrl translateUrlUser(UserListItem user, Url url){
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		String key = (
				((user != null) ? (user.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		return (SingleResultUrl)invokeCached(CacheKey.urlServiceWebTranslateUrlUserCache, key, this.translateUrlUserName, args, returnTypes);
    }
	@SuppressWarnings("unchecked")
	@Override
    public SingleResultUrl translateUrlOrganization(OrganizationListItem organization, Url url){
		Object[] args = new Object[] { organization, url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		String key = (
				((organization != null) ? (organization.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		return (SingleResultUrl)invokeCached(CacheKey.urlServiceWebTranslateUrlOrganizationCache, key, this.translateUrlOrganizationName, args, returnTypes);
    }
	@SuppressWarnings("unchecked")
	@Override
    public SingleResultUrl translateUrlUserOrganization(UserListItem user, OrganizationListItem organization, Url url){
		Object[] args = new Object[] { user, organization, url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		String key = (
				((user != null) ? (user.getId() + "-") : "") + 
				((organization) != null ? (organization.getId() + "-") : "") +
				((url != null) ? (url.getStringValue()) : "")
				);
		return (SingleResultUrl) invokeCached(CacheKey.urlServiceWebTranslateUrlUserOrganizationCache, key, this.translateUrlUserOrganizationName, args, returnTypes);
    }
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultUrl translateUrlNone(Url url) {
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		String key = (url != null) ? url.getStringValue() : "";
		return (SingleResultUrl)invokeCached(CacheKey.urlServiceWebTranslateUrlNoneCache, key, this.translateUrlNoneName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
    public AccessResult hasAccessUser(UserListItem user, Url url){
		Object[] args = new Object[] { user, url };
		Class[] returnTypes = new Class[] { AccessResult.class };
		String key = (
				((user != null) ? (user.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		return (AccessResult)invokeCached(CacheKey.urlServiceWebHasAccessUserCache, key, this.hasAccessUserName, args, returnTypes);
    }
	@SuppressWarnings("unchecked")
	@Override
    public AccessResult hasAccessOrganization(OrganizationListItem organization, Url url){
		Object[] args = new Object[] { organization, url };
		Class[] returnTypes = new Class[] { AccessResult.class };
		String key = (
				((organization != null) ? (organization.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		return (AccessResult)invokeCached(CacheKey.urlServiceWebHasAccessOrganizationCache, key, this.hasAccessOrganizationName, args, returnTypes);
    }
	@SuppressWarnings("unchecked")
	@Override
    public AccessResult hasAccessUserOrganization(UserListItem user, OrganizationListItem organization, Url url){
		Object[] args = new Object[] { user, organization, url };
		Class[] returnTypes = new Class[] { AccessResult.class };
		String key = (
				((organization != null) ? (organization.getId() + "-") : "") + 
				((user != null) ? (user.getId() + "-") : "") +
				((url != null) ? (url.getStringValue()) : "")
				);
		return (AccessResult) invokeCached(CacheKey.urlServiceWebHasAccessUserOrganizationCache, key, this.hasAccessUserOrganizationName, args, returnTypes);
    }
	@SuppressWarnings("unchecked")
	@Override
	public AccessResult hasAccessNone(Url url) {
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { AccessResult.class };
		String key = (
				((url != null) ? (url.getStringValue()) : "")
				);
		return (AccessResult)invokeCached(CacheKey.urlServiceWebHasAccessNoneCache, key, this.hasAccessNoneName, args, returnTypes);
	}
	@SuppressWarnings("unchecked")
	@Override
    public SingleResultString group(Url url) {
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { SingleResultString.class };
		String key = (
				((url != null) ? (url.getStringValue()) : "")
				);
		return (SingleResultString)invokeCached(CacheKey.urlServiceWebGroupCache, key, this.groupName, args, returnTypes);
    }

//    public AccessType getAccessTypeForUserAndMemberOrganization(User user, MemberOrganization memberOrganization, Url url) {
//		Object[] args = new Object[] { user, memberOrganization, url };
//		Class[] returnTypes = new Class[] { AccessType.class };
//		String key = ( 
//				((user) != null ? (user.getId() + "-") : "") +
//				((memberOrganization) != null ? (memberOrganization.getOrganization().getId() + "-") : "") +
//				((url != null) ? (url.getStringValue()) : "")
//				);
//		return (AccessType) invokeCached(CacheKey.accessTypeForUserAndMemberOrganizationCache, key, this.accessTypeForUserAndMemberOrganizationName, args, returnTypes);
//	}
    
    public Log getLogger() {
		return logger;
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
	public void setGroupName(QName groupName) {
		this.groupName = groupName;
	}
	public void setTranslateUrlNoneName(QName translateUrlNoneName) {
		this.translateUrlNoneName = translateUrlNoneName;
	}
	public void setHasAccessNoneName(QName hasAccessNoneName) {
		this.hasAccessNoneName = hasAccessNoneName;
	}

	@SuppressWarnings("null")
	public static void main(String[] args) throws Exception {
		// TODO Fase2: Move to test project!
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
		
		UserListItem user = new UserListItem();
		OrganizationListItem organization = new OrganizationListItem();
		Url url1 = new Url();
		url1.setStringValue("http://proxy.helsebiblioteket.no/login?url=http://www.legehandboka.no");
		Url url2 = new Url();
		url2.setStringValue("http://www.g-i-n.net.proxy.helsebiblioteket.no/");
		Boolean isAffected1 = loginService.isAffected(url1);
		Boolean isAffected2 = loginService.isAffected(url2);
		@SuppressWarnings("unused")
		SingleResultUrl translatedResult1 = loginService.translateUrlUserOrganization(user, organization, url1);
		@SuppressWarnings("unused")
		SingleResultUrl translatedResult2 = loginService.translateUrlUserOrganization(user, organization, url2);
		Url translated1 = null;
		Url translated2 = null;
    	System.out.println("isAffected1: " + isAffected1);
    	System.out.println("isAffected2: " + isAffected2);
    	System.out.println("translated1: " + translated1.getStringValue());
    	System.out.println("translated2: " + translated2.getStringValue());
	}
}
