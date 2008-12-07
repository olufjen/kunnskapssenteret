package no.helsebiblioteket.admin.service.impl;

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
}
