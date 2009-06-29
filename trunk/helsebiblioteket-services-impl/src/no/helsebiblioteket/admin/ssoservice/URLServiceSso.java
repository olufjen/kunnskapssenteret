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
	
	@Override
	public Boolean isAffected(Url url){
		return urlService.isAffected(url);
	}
	@Override
    public SingleResultUrl translateUrlUser(UserListItem user, Url url){
		String key = (
				((user != null) ? (user.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceSsoTranslateUrlUserCache, key);
		if (result == null) {
			result = urlService.translateUrlUser(user, url);
			cacheHelper.addCache(CacheKey.urlServiceSsoTranslateUrlUserCache, key, result);
		}
		return (SingleResultUrl) result;
    }
	@Override
    public SingleResultUrl translateUrlOrganization(OrganizationListItem organization, Url url){
		String key = (
				((organization != null) ? (organization.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceSsoTranslateUrlOrganizationCache, key);
		if (result == null) {
			result = urlService.translateUrlOrganization(organization, url);
			cacheHelper.addCache(CacheKey.urlServiceSsoTranslateUrlOrganizationCache, key, result);
		}
		return (SingleResultUrl) result;
    }
	
	@Override
    public SingleResultUrl translateUrlUserOrganization(UserListItem user, OrganizationListItem organization, Url url){
		String key = (
				((user != null) ? (user.getId() + "-") : "") + 
				((organization) != null ? (organization.getId() + "-") : "") +
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceSsoTranslateUrlUserOrganizationCache, key);
		if (result == null) {
			result = urlService.translateUrlUserOrganization(user, organization, url);
			cacheHelper.addCache(CacheKey.urlServiceSsoTranslateUrlUserOrganizationCache, key, result);
		}
		return (SingleResultUrl) result;
    }
	@Override
	public SingleResultUrl translateUrlNone(Url url) {
		String key = (url != null) ? url.getStringValue() : "nourl";
		Object result = cacheHelper.findCache(CacheKey.urlServiceSsoTranslateUrlNoneCache, key);
		if (result == null) {
			result = urlService.translateUrlNone(url);
			cacheHelper.addCache(CacheKey.urlServiceSsoTranslateUrlNoneCache, key, result);
		}
		return (SingleResultUrl) result;
	}
	@Override
    public AccessResult hasAccessUser(UserListItem user, Url url) {
		String key = (
				((user != null) ? (user.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceSsoHasAccessUserCache, key);
		if (result == null) {
			result = urlService.hasAccessUser(user, url);
			cacheHelper.addCache(CacheKey.urlServiceSsoHasAccessUserCache, key, result);
		}
		return (AccessResult) result;
    }
	@Override
    public AccessResult hasAccessOrganization(OrganizationListItem organization, Url url){
		String key = (
				((organization != null) ? (organization.getId() + "-") : "") + 
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceSsoHasAccessOrganizationCache, key);
		if (result == null) {
			result = urlService.hasAccessOrganization(organization, url);
			cacheHelper.addCache(CacheKey.urlServiceSsoHasAccessOrganizationCache, key, result);
		}
		return (AccessResult) result;
    }
	@Override
    public AccessResult hasAccessUserOrganization(UserListItem user, OrganizationListItem organization, Url url){
		String key = (
				((organization != null) ? (organization.getId() + "-") : "") + 
				((user != null) ? (user.getId() + "-") : "") +
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceSsoHasAccessUserOrganizationCache, key);
		if (result == null) {
			result = urlService.hasAccessUserOrganization(user, organization, url);
			cacheHelper.addCache(CacheKey.urlServiceSsoHasAccessUserOrganizationCache, key, result);
		}
		return (AccessResult) result;
    }
	@Override
	public AccessResult hasAccessNone(Url url) {
		String key = (
				((url != null) ? (url.getStringValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceSsoHasAccessNoneCache, key);
		if (result == null) {
			result = urlService.hasAccessNone(url);
			cacheHelper.addCache(CacheKey.urlServiceSsoHasAccessNoneCache, key, result);
		}
		return (AccessResult) result;
	}
	@Override
    public SingleResultString group(Url url) {
		String key = (
				((url != null) ? (url.getStringValue()) : "nokey")
				);
		Object result = cacheHelper.findCache(CacheKey.urlServiceSsoGroupCache, key);
		if (result == null) {
			result = urlService.group(url);
			cacheHelper.addCache(CacheKey.urlServiceSsoGroupCache, key, result);
		}
		return (SingleResultString) result;
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