package no.helsebiblioteket.admin.domain.cache.key;

public enum CacheKey {
	urlServiceWebIsAffectedCache,
	urlServiceWebTranslateUrlUserOrganizationCache,
	urlServiceWebTranslateUrlUserCache,
	urlServiceWebTranslateUrlOrganizationCache,
	urlServiceWebTranslateUrlNoneCache,
	urlServiceWebHasAccessUserCache,
	urlServiceWebHasAccessOrganizationCache,
	urlServiceWebHasAccessUserOrganizationCache,
	urlServiceWebHasAccessNoneCache,
	urlServiceWebGroupCache,
	organizationServiceWebGetOrganizationTypeByKeyCache,
	userServiceWebGetSystemByKeyCache,
	userServiceWebGetRoleByKeySystemCache,
	
	urlServiceSsoIsAffectedCache,
	urlServiceSsoTranslateUrlUserOrganizationCache,
	urlServiceSsoTranslateUrlUserCache,
	urlServiceSsoTranslateUrlOrganizationCache,
	urlServiceSsoTranslateUrlNoneCache,
	urlServiceSsoHasAccessUserCache,
	urlServiceSsoHasAccessOrganizationCache,
	urlServiceSsoHasAccessUserOrganizationCache,
	urlServiceSsoHasAccessNoneCache,
	urlServiceSsoGroupCache,
	organizationServiceSsoGetOrganizationTypeByKeyCache,
	userServiceSsoGetSystemByKeyCache,
	userServiceSsoGetRoleByKeySystemCache,
	organizationServiceSsoGetOrganizationListByAccessDomainCache,
	loginServiceSsologinOrganizationByReferringDomainCache,
	loginServiceSsologinOrganizationByIpAddressCache,
	
	staticListCache,
	defaultCache
}
