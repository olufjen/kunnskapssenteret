package no.helsebiblioteket.admin.ssoservice;


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

public class URLServiceSso extends SsoService implements URLService {
	protected static final Log logger = LogFactory.getLog(URLServiceSso.class);
	
	private URLService urlService;
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isAffected(Url url){
		return urlService.isAffected(url); 
	}
	@SuppressWarnings("unchecked")
	@Override
    public SingleResultUrl translateUrlUser(UserListItem user, Url url){
		String key = (
				((user != null) ? (user.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		
		Object result = cacheHelper.findCache(CacheKey.urlServiceWebTranslateUrlUserCache, key);
		return (result != null) ? (SingleResultUrl) result : urlService.translateUrlUser(user, url);
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
		
		Object result = cacheHelper.findCache(CacheKey.urlServiceWebTranslateUrlOrganizationCache, key);
		return (result != null) ? (SingleResultUrl) result : urlService.translateUrlOrganization(organization, url);
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
		
		Object result = cacheHelper.findCache(CacheKey.urlServiceWebTranslateUrlUserOrganizationCache, key);
		return (result != null) ? (SingleResultUrl) result : urlService.translateUrlUserOrganization(user, organization, url);
    }
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultUrl translateUrlNone(Url url) {
		Object[] args = new Object[] { url };
		Class[] returnTypes = new Class[] { SingleResultUrl.class };
		String key = (url != null) ? url.getStringValue() : "nokey";
		Object result = cacheHelper.findCache(CacheKey.urlServiceWebTranslateUrlNoneCache, key);
		return (result != null) ? (SingleResultUrl) result : urlService.translateUrlNone(url);
	}
	@SuppressWarnings("unchecked")
	@Override
    public AccessResult hasAccessUser(UserListItem user, Url url){
		String key = (
				((user != null) ? (user.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceWebHasAccessUserCache, key);
		return (result != null) ? (AccessResult) result : urlService.hasAccessUser(user, url);
    }
	@SuppressWarnings("unchecked")
	@Override
    public AccessResult hasAccessOrganization(OrganizationListItem organization, Url url){
		String key = (
				((organization != null) ? (organization.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceWebHasAccessOrganizationCache, key);
		return (result != null) ? (AccessResult) result : urlService.hasAccessOrganization(organization, url);
    }
	@SuppressWarnings("unchecked")
	@Override
    public AccessResult hasAccessUserOrganization(UserListItem user, OrganizationListItem organization, Url url){
		String key = (
				((organization != null) ? (organization.getId() + "-") : "") + 
				((user != null) ? (user.getId() + "-") : "") +
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceWebHasAccessUserOrganizationCache, key);
		return (result != null) ? (AccessResult) result : urlService.hasAccessUserOrganization(user, organization, url);
    }
	@SuppressWarnings("unchecked")
	@Override
	public AccessResult hasAccessNone(Url url) {
		String key = (
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceWebHasAccessNoneCache, key);
		return (result != null) ? (AccessResult) result : urlService.hasAccessNone(url);
	}
	@SuppressWarnings("unchecked")
	@Override
    public SingleResultString group(Url url) {
		String key = (
				((url != null) ? (url.getStringValue()) : "nokey")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceWebGroupCache, key);
		return (result != null) ? (SingleResultString) result : urlService.group(url);
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
	
	public void setUrlService(URLService urlService) {
		this.urlService = urlService;
	}
}