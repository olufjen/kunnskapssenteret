package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultString;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccess;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultString;
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
	 * it may  be relevant for the proxy, but only if ...
	 * 
	 *  TODO: What are the criteria here?
	 * 
	 */
	public Boolean isAffected(Url url) {
		// TODO: Improve efficiency by not fetching all!
		ListResultSupplierSource list = this.accessService.getSupplierSourceListAll("");
		for (SupplierSource supplierSource : list.getList()) {
			if(supplierSource.getUrl().getStringValue().equals(url.getStringValue())){
				// TODO: How is the correct way to check this?
				//       We will hopefully not need to parse it
				//       and do all kinds of stuff.
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	/**
	 * Translates a URL. If the user has Access he will
	 * be sent to the proxy. Otherwise there must be a
	 * page with information about what to do.
	 * 
	 */
	public SingleResultUrl translateUrlUser(User user, Url url) {
		// TODO: Use "redirectport" somewhere?
		// TODO: Include deny.
		// TODO: Check for 'provided by'.
		// TODO: Check for 'offered by'.
		Url newUrl = new Url();
		if(this.hasAccessUser(user, url)){
			// TODO: When to send through proxy?
			newUrl.setStringValue(this.proxyPrefix + url.getStringValue());
		} else {
			// TODO: When to send directly?
			newUrl.setStringValue(url.getStringValue());
		}
		return new ValueResultUrl(newUrl);
	}
	/**
	 * Translates a URL for an organization. If the organization
	 * has access it may be sent to the proxy, but not if it 
	 * can be sent directly.
	 * 
	 * TODO: When to send an organization directly?
	 * 
	 */
	public SingleResultUrl translateUrlOrganization(MemberOrganization organization, Url url) {
		Url newUrl = new Url();
		if(hasAccessOrganization(organization, url)){
			// TODO: When to send through proxy?
			newUrl.setStringValue(this.proxyPrefix + url.getStringValue());
		} else {
			// TODO: When to send directly?
			newUrl.setStringValue(url.getStringValue());
		}
		return new ValueResultUrl(newUrl);
	}
	/**
	 * Translates a URL for either organization of user.
	 * If the organization has access, it will be used
	 * because that may go around the proxy and relive
	 * it from traffic.
	 * 
	 * TODO: An important question (since we do not have
	 *       the lock symbol on the links yet) is whether
	 *       someone who dosn't have access will be sent
	 *       to the proxy or directly to the server?
	 *       Sometimes one and sometimes the other?
	 */
	public SingleResultUrl translateUrlUserOrganization(User user, MemberOrganization organization, Url url){
		Url newUrl = new Url();
		if(hasAccessOrganization(organization, url)){
			return this.translateUrlOrganization(organization, url);
		} else if (hasAccessUser(user, url)){
			return this.translateUrlUser(user, url);
		} else {
			// TODO: When to send directly?
			newUrl.setStringValue(url.getStringValue());
		}
		return new ValueResultUrl(newUrl);
	}
	/**
	 * Loads the Access list for a user and checks if the URL
	 * is in the list.
	 * 
	 * TODO: I think we must check for Access type and resource
	 *       type. 
	 */
    public Boolean hasAccessUser(User user, Url url) {
    	ListResultResourceAccessListItem userAccessList = this.accessService.getAccessListByUser(user);
    	Boolean test = checkAccess(url, userAccessList.getList());
    	if(test != null){ return test; }
    	
    	// TODO: Trust the user list and organization or reload?
    	for (Role role : user.getRoleList()) {
        	ListResultResourceAccessListItem userRoleAccess = this.accessService.getAccessListByRole(role);
        	test = checkAccess(url, userRoleAccess.getList());
        	if(test != null){ return test; }
		}
    	ListResultResourceAccessListItem organizationAccessList = this.accessService.getAccessListByOrganization(user.getOrganization());
		test = checkAccess(url, organizationAccessList.getList());
		if(test != null){ return test; }
		
		ListResultResourceAccessListItem organizationTypeAccessList = this.accessService.getAccessListByOrganizationType(user.getOrganization().getType());
		test = checkAccess(url, organizationTypeAccessList.getList());
		if(test != null){ return test; }
		
		return Boolean.FALSE;
	}
    private Boolean checkAccess(Url url, ResourceAccessListItem[] resourceAccesses) {
    	for (ResourceAccessListItem access : resourceAccesses) {
    		if(url.getStringValue().equals(
    				access.getUrl().getStringValue())){
        		// If GRANT and general -> OK
        		if(access.getCategory().getValue().equals(
        				AccessTypeCategory.GRANT.getValue()) &&
        					access.getKey().getValue().equals(
        							AccessTypeKey.general.getValue())){
            		return true;
        		}
        		// If DENY and general -> NOT OK
        		if(access.getCategory().getValue().equals(
        				AccessTypeCategory.DENY.getValue()) &&
        				access.getKey().getValue().equals(
        						AccessTypeKey.general.getValue())){
        			return false;
        		}
        	}
		}
    	return null;
    }
	/**
	 * Loads the Access list for an organization and checks if the URL
	 * is in the list.
	 * 
	 */
	public Boolean hasAccessOrganization(MemberOrganization organization, Url url) {
    	ListResultResourceAccessListItem accessList = this.accessService.getAccessListByOrganization(organization.getOrganization());
		Boolean test = checkAccess(url, accessList.getList());
		if(test != null){ return test; }

		ListResultResourceAccessListItem organizationTypeAccessList = this.accessService.getAccessListByOrganizationType(organization.getOrganization().getType());
		test = checkAccess(url, organizationTypeAccessList.getList());
		if(test != null){ return test; }

		return Boolean.FALSE;
	}
	/**
	 * Checks if an organization has access first, then check the user.
	 * If none of them have, the URL and the resource it identifies
	 * is not available to the user or the organization.
	 */
	public Boolean hasAccessUserOrganization(User user, MemberOrganization organization, Url url) {
		if(hasAccessOrganization(organization, url)){
			return Boolean.TRUE;
		} else if(hasAccessUser(user, url)){
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	/**
	 * Finds the group value for a URL. Loads the resources, finds
	 * the URL and the group name for it.
	 * 
	 * TODO: The database will be extended with a new field.
	 *       Do not use supplierSource.getName().
	 *  
	 */
	public SingleResultString group(Url url){
		ListResultSupplierSource list = this.accessService.getSupplierSourceListAll("");
		for (SupplierSource supplierSource : list.getList()) {
			if(supplierSource.getUrl().getStringValue().equals(url.getStringValue())){
    			// TODO: Is this all?
				return new ValueResultString(supplierSource.getSupplierSourceName());
			}
		}
		return new EmptyResultString();
	}
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	public void setProxyPrefix(String proxyPrefix) {
		this.proxyPrefix = proxyPrefix;
	}
}
