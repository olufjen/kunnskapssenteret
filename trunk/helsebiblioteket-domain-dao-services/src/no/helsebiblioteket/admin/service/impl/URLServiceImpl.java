package no.helsebiblioteket.admin.service.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;

public class URLServiceImpl implements URLService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	public Boolean isAffected(Url url) {
		logger.info("Is this affected? " + url.getValue());
		// TODO: Complete this! Look up values in DB!
		return Boolean.TRUE;
	}
	public SingleResult<Url> translate(User user, Url url) {
		// TODO: Complete this! Look up values in DB!
		Url newUrl = new Url();
		newUrl.setValue("http://myproxy.com/file?url=" + url.getValue());
		logger.info("User: " + user.getUsername());
		logger.info("Translating " + url.getValue()  + " into " + newUrl.getValue());
		return new ValueResult<Url>(newUrl);
	}
	public SingleResult<Url> translate(Organization organization, Url url) {
		// TODO: Complete this! Look up values in DB!
		Url newUrl = new Url();
		newUrl.setValue("http://myproxy.com/file?url=" + url.getValue());
		logger.info("Organization: " + organization.getNameEnglish());
		logger.info("Translating " + url.getValue()  + " into " + newUrl.getValue());
		return new ValueResult<Url>(newUrl);
	}
	public SingleResult<Url> translate(User user, Organization organization, Url url){
		// TODO: Complete this! Look up values in DB!
		Url newUrl = new Url();
		if(hasAccess(organization, url)){
			this.translate(organization, url);
			newUrl.setValue("http://myproxy.com/file?url=" + url.getValue());
		} else if (hasAccess(user, url)){
			this.translate(user, url);
			newUrl.setValue("http://myproxy.com/file?url=" + url.getValue());
		} else {
			newUrl.setValue("http://myproxy.com/file?url=" + url.getValue());
		}
		return new ValueResult<Url>(newUrl);
	}
    public Boolean hasAccess(User user, Url url) {
		// TODO: Complete this! Look up values in DB!
		if(group(url).equals("digi")){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	public Boolean hasAccess(Organization organization, Url url) {
		// TODO: Complete this! Look up values in DB!
		if(group(url).equals("vg")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	public Boolean hasAccess(User user, Organization organization, Url url) {
		// TODO: Complete this! Look up values in DB!
		if(hasAccess(organization, url)){
			return Boolean.TRUE;
		} else if(hasAccess(user, url)){
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	public SingleResult<String> group(Url url){
		// TODO: Complete this! Calculate here!
		try {
			URL old;
			old = new URL(url.getValue());
			String hostname = old.getHost();
			String[] parts = hostname.split("\\.");
			return new ValueResult<String>(parts[1]);
		} catch (MalformedURLException e) {
			return new EmptyResult<String>();
		}
	}
}
