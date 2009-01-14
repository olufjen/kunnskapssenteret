package no.helsebiblioteket.admin.service.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.URLService;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;

public class URLServiceImpl implements URLService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	public Boolean isAffected(Url url) {
		// TODO: Complete this! Look up values in DB!
		
		logger.info("Is this affected? " + url.getValue());
		boolean res = false;
		res = true;
		if(true) return res;

		return Boolean.FALSE;
	}
	public Url translate(User user, Organization organization, Url url) {
		// TODO: Complete this! Look up values in DB!
		Url newUrl = new Url();
		newUrl.setValue("http://myproxy.com/file?url=" + url.getValue());


		
		if(user !=null){
			logger.info("User: " + user.getUsername());
		} else {
			logger.info("User: " + user);
		}
		if(organization != null) {
			logger.info("Organization: " + organization.getName());
		} else {
			logger.info("Organization: " + organization);
		}
		logger.info("Translating " + url.getValue()  + " into " + newUrl.getValue());
		return newUrl;
	}
	public String group(Url url) {
		// TODO: Write!
		try {
			URL old;
			old = new URL(url.getValue());
			String hostname = old.getHost();
			String[] parts = hostname.split("\\.");
			return parts[1];
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public Boolean hasAccess(User user, Organization organization, Url url) {
//		if(user != null && "".equals(user.getUsername())){
		// TODO Auto-generated method stub
		if(group(url).equals("digi")){
			return Boolean.FALSE;
		}

		if(group(url).equals("vg")){
			return Boolean.TRUE;
		} else if(user != null){
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
