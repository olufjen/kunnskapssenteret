package no.helsebiblioteket.admin.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.AccessResult;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultString;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultString;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUrl;

/**
 * Service used to rewrite URLs on websites. The results
 * from this should be cahced on clients. The services
 * uses no DAOs, but uses other services to perform its
 * tasks.
 * 
 * @author Fredrik S.
 */
public class URLServiceImpl implements URLService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private AccessService accessService;
	private String proxyPrefix;
	
	/**
	 * This checks if a URL is relevant(affected) for the proxy
	 * server. If the URL is found in the supplier sources table,
	 * it may be relevant for the proxy.
	 * Match criteria is: The URL passed as an argument to this method
	 * must match a "starts with" criteria for any stored URL.
	 * 
	 */
	@Override
	public Boolean isAffected(Url url) {
		SingleResultSupplierSource supplierSourceResult = accessService.getSupplierSourceByUrlStartsWith(url);
		if (supplierSourceResult instanceof EmptyResultSupplierSource) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}
	@Override
	public SingleResultUrl translateUrlNone(Url url) {
		return this.translateUrlUserOrganizationInternal(null, null, url);
	}
	/**
	 * Translates a URL. If the user has Access he will
	 * be sent to the proxy. Otherwise there must be a
	 * page with information about what to do.
	 * 
	 */
	@Override
	public SingleResultUrl translateUrlUser(UserListItem user, Url url) {
		return this.translateUrlUserOrganizationInternal(user, null, url);
	}
	/**
	 * Translates a URL for an organization. If the organization
	 * has access it may be sent to the proxy, but not if it 
	 * can be sent directly.
	 * 
	 */
	@Override
	public SingleResultUrl translateUrlOrganization(OrganizationListItem organization, Url url) {
		return this.translateUrlUserOrganizationInternal(null, organization, url);
	}
	/**
	 * Translates a URL for either organization or user.
	 * 
	 * Default behavior of this method is to return a proxified version of the URL
	 * that is sent as a parameter to it.
	 * The only exception to this rule is if a requestor is assigned access of type "proxy_exclude"
	 * to a given resource, and if no requestor-access further down
	 * the requester hierarchy overrides this rule.
	 * 
	 * The requester hierarchy is ordered as follows:
	 * 1) User role of type "no role" (this is default access level for any requestor, both authenticated / known and not authenticated / unknown)
	 * 2) organization type
	 * 3) organization
	 * 4) user role
	 * 5) user
	 * 
	 * A requester is assigned the "proxy exclude" access type in two known cases:
	 * 1) if the user/role/org has access to a given resource that exceeds what is offered through the proxy access. 
	 * 2) if the resource authenticates requester(s) in their end based on either GeoIP or other authentication mechanism. 
	 * 
	 */
	@Override
	public SingleResultUrl translateUrlUserOrganization(UserListItem user, OrganizationListItem memberOrganization, Url url) {
		return this.translateUrlUserOrganizationInternal(user, memberOrganization, url);
	}

	
	private SingleResultUrl translateUrlUserOrganizationInternal(UserListItem userListItem, OrganizationListItem organizationListItem, Url url) {
		Url newUrl = new Url();
		boolean proxify = true;
		
		proxify = proxify && !proxyExclude(getAccessTypeForAll(url));
		
		if (organizationListItem != null) {
			proxify = proxify && !proxyExclude(getAccessTypeForOrganizationType(organizationListItem.getTypeKey(), url));
			proxify = proxify && !proxyExclude(getAccessTypeForMemberOrganization(organizationListItem, url));
		}
		
		if (userListItem != null) {
			if (userListItem.getRoleKeys() != null) {
				for (UserRoleKey role : userListItem.getRoleKeys()) {
					proxify = proxify && !proxyExclude(getAccessTypeForUserRole(role, url));
				}
			}
			// TODO Fase2: handle user access
		}
		
		// TODO Fase2: add an image to link to illustrate whether requester has access or not.
		
		if (proxify) {
			// TODO: In case any existing "&amp;'s in URL: double replace, or use negative lookahead regexp
			url.setStringValue(url.getStringValue().replace("&amp;", "&"));
			url.setStringValue(url.getStringValue().replace("&", "&amp;"));
			newUrl.setStringValue(this.proxyPrefix + url.getStringValue());
		} else {
			newUrl.setStringValue(url.getStringValue());
		}
		
		return new ValueResultUrl(newUrl);
	}
	private boolean proxyExclude(AccessType accessType) {
		if (accessType != null &&
				accessType.getKey().getValue().equals(AccessTypeKey.proxy_exclude.getValue()) &&
				accessType.getCategory().getValue().equals(AccessTypeCategory.GRANT.getValue())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Loads the Access list for a user and checks if the URL
	 * is in the list.
	 */
	@Override
    public AccessResult hasAccessUser(UserListItem user, Url url) {
		return this.getAccessResultForUserAndMemberOrganization(user, null, url);
	}
	/**
	 * Loads the Access list for an organization and checks if the URL
	 * is in the list.
	 */
	@Override
	public AccessResult hasAccessOrganization(OrganizationListItem organization, Url url) {
		return this.getAccessResultForUserAndMemberOrganization(null, organization, url);
	}
	/**
	 * Checks if an organization has access first, then check the user.
	 * If none of them have, the URL and the resource it identifies
	 * is not available to the user or the organization.
	 */
	@Override
	public AccessResult hasAccessUserOrganization(UserListItem user, OrganizationListItem organization, Url url) {
		return this.getAccessResultForUserAndMemberOrganization(user, organization, url);
	}
	@Override
	public AccessResult hasAccessNone(Url url) {
		return this.getAccessResultForUserAndMemberOrganization(null, null, url);
	}
	/**
	 * Finds the group value for a URL. Loads the resources, finds
	 * the URL and the group name for it.
	 *  
	 */
	@Override
	public SingleResultString group(Url url){
		SingleResultSupplierSource supplierSourceResult = accessService.getSupplierSourceByUrlStartsWith(url);
		if (supplierSourceResult instanceof ValueResultSupplierSource) {
			return new ValueResultString(((ValueResultSupplierSource) supplierSourceResult).getValue().getProxyDatabaseName());
		}
		return new EmptyResultString();
	}

	private AccessResult getAccessResultForUserAndMemberOrganization(UserListItem user, OrganizationListItem memberOrganization, Url url) {
		AccessType accessType = this.getAccessTypeForUserAndMemberOrganization(user, memberOrganization, url);
		if(accessType.getCategory().getValue().equals(AccessTypeCategory.DENY.getValue())){
			return AccessResult.logup;
		}else if(accessType.getCategory().getValue().equals(AccessTypeCategory.GRANT.getValue()) &&
				accessType.getKey().getValue().equals(AccessTypeKey.proxy_exclude_all.getValue())) {
			return AccessResult.exclude;
		} else if(accessType.getCategory().getValue().equals(AccessTypeCategory.GRANT.getValue()) &&
				accessType.getKey().getValue().equals(AccessTypeKey.proxy_include_all.getValue())){
			return AccessResult.include;
		} else if(accessType.getCategory().getValue().equals(AccessTypeCategory.GRANT.getValue()) &&
				accessType.getKey().getValue().equals(AccessTypeKey.proxy_exclude.getValue())){
			return AccessResult.exclude;
		} else if(accessType.getCategory().getValue().equals(AccessTypeCategory.GRANT.getValue()) &&
				accessType.getKey().getValue().equals(AccessTypeKey.proxy_include.getValue())){
			return AccessResult.include;
		} else {
			return AccessResult.logup;
		}
	}

	private AccessType getAccessTypeForUserAndMemberOrganization(UserListItem userListItem, OrganizationListItem organizationListItem, Url url) {
		AccessType accessType = new AccessType(AccessTypeCategory.DENY, AccessTypeKey.general);
		AccessType accessTypeTmp = null;
		
		accessType = (accessTypeTmp = getAccessTypeForAll(url)) != null ? accessTypeTmp : accessType;
		
		if (organizationListItem != null) {
			accessType = (accessTypeTmp = getAccessTypeForOrganizationType(organizationListItem.getTypeKey(), url)) != null ? accessTypeTmp : accessType;
			accessType = (accessTypeTmp = getAccessTypeForMemberOrganization(organizationListItem, url)) != null ? accessTypeTmp : accessType;
		}
		
		if (userListItem != null) {
			if (userListItem.getRoleKeys() != null) {
				for (UserRoleKey role : userListItem.getRoleKeys()) {
					accessType = (accessTypeTmp = getAccessTypeForUserRole(role, url)) != null ? accessTypeTmp : accessType;
				}
			}
			// Axis2 does not accept arrays of complex types
			// This is a fix to be able to pass user roles to this service via Axis2
			if (accessType.getCategory().equals(AccessTypeCategory.DENY) && accessType.getKey().equals(AccessTypeKey.general) && (userListItem.getRoleKeyValuesAsStrings() != null)) {
				for (String roleKeyValue : userListItem.getRoleKeyValuesAsStrings()) {
					accessType = (accessTypeTmp = getAccessTypeForUserRole(new UserRoleKey(roleKeyValue), url)) != null ? accessTypeTmp : accessType;
				}
			}
		}
		
		return accessType;
	}
	
	private AccessType getAccessTypeForOrganizationType(OrganizationTypeKey organizationType, Url url) {
		AccessType accessType = null;
		ListResultResourceAccessListItem organizationTypeAccessList = this.accessService.getAccessListByOrganizationType(organizationType);
		accessType = getAccessTypeForResourceAccessList(url, organizationTypeAccessList);
		return accessType;
	}
	
	private AccessType getAccessTypeForMemberOrganization(OrganizationListItem memberOrganization, Url url) {
		AccessType accessType = null;
		ListResultResourceAccessListItem organizationTypeAccessList = this.accessService.getAccessListByOrganization(memberOrganization);
		accessType = getAccessTypeForResourceAccessList(url, organizationTypeAccessList);
		return accessType;
	}
	private AccessType getAccessTypeForAll(Url url) {
		AccessType accessType = null;
		ListResultResourceAccessListItem organizationTypeAccessList = this.accessService.getAccessListForAll();
		accessType = getAccessTypeForResourceAccessList(url, organizationTypeAccessList);
		return accessType;
	}
	
	private AccessType getAccessTypeForUserRole(UserRoleKey userRole, Url url) {
		AccessType accessType = null;
		ListResultResourceAccessListItem organizationTypeAccessList = this.accessService.getAccessListByRole(userRole);
		accessType = getAccessTypeForResourceAccessList(url, organizationTypeAccessList);
		return accessType;
	}
	
	private AccessType getAccessTypeForResourceAccessList(Url url, ListResultResourceAccessListItem accessList) {
		AccessType accessType = new AccessType();
		for (ResourceAccessListItem access : accessList.getList()) {
			if (url.getStringValue().startsWith(access.getUrl().getStringValue())) {
				accessType.setCategory(access.getCategory());
				accessType.setKey(access.getKey());
				return accessType;
			}
		}
		return null;
    }
	
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	public void setProxyPrefix(String proxyPrefix) {
		this.proxyPrefix = proxyPrefix;
	}
}
