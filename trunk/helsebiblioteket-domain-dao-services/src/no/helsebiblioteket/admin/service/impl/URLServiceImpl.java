package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;

public class URLServiceImpl implements URLService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private SupplierSourceDao supplierSourceDao;
	private String proxyPrefix;
	public Boolean isAffected(Url url) {
		// TODO: Improve efficiency by not fetching all!
		List<SupplierSource> list = this.supplierSourceDao.getSupplierSourceListAll();
		for (SupplierSource supplierSource : list) {
			if(supplierSource.getUrl().getValue().equals(url.getValue())){
				// TODO: How is the correct way to check this?
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	public SingleResult<Url> translate(User user, Url url) {
		Url newUrl = new Url();
		if(this.hasAccess(user, url)){
			// TODO: When to send through proxy?
			newUrl.setValue(this.proxyPrefix + url.getValue());
		} else {
			// TODO: When to send directly?
			newUrl.setValue(url.getValue());
		}
		return new ValueResult<Url>(newUrl);
	}
	public SingleResult<Url> translate(Organization organization, Url url) {
		Url newUrl = new Url();
		if(hasAccess(organization, url)){
			// TODO: When to send through proxy?
			newUrl.setValue(this.proxyPrefix + url.getValue());
		} else {
			// TODO: When to send directly?
			newUrl.setValue(url.getValue());
		}
		return new ValueResult<Url>(newUrl);
	}
	public SingleResult<Url> translate(User user, Organization organization, Url url){
		Url newUrl = new Url();
		if(hasAccess(organization, url)){
			return this.translate(organization, url);
		} else if (hasAccess(user, url)){
			return this.translate(user, url);
		} else {
			// TODO: When to send directly?
			newUrl.setValue(url.getValue());
		}
		return new ValueResult<Url>(newUrl);
	}
    public Boolean hasAccess(User user, Url url) {
    	for (Access access : user.getAccessList()) {
    		if(url.getValue().equals(access.getSupplierSource().getUrl().getValue())){
    			// TODO: Is this all?
    			return Boolean.TRUE;
    		}
		}
		return Boolean.FALSE;
	}
	public Boolean hasAccess(Organization organization, Url url) {
    	for (Access access : organization.getAccessList()) {
    		if(url.getValue().equals(access.getSupplierSource().getUrl().getValue())){
    			// TODO: Is this all?
    			return Boolean.TRUE;
    		}
		}
		return Boolean.FALSE;
	}
	public Boolean hasAccess(User user, Organization organization, Url url) {
		if(hasAccess(organization, url)){
			return Boolean.TRUE;
		} else if(hasAccess(user, url)){
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	public SingleResult<String> group(Url url){
		List<SupplierSource> list = this.supplierSourceDao.getSupplierSourceListAll();
		for (SupplierSource supplierSource : list) {
			if(supplierSource.getUrl().getValue().equals(url.getValue())){
    			// TODO: Is this all?
				return new ValueResult<String>(supplierSource.getName());
			}
		}
		return new EmptyResult<String>();
	}
}
