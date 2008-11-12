package no.helsebiblioteket.admin.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewUserBean {
    protected final Log logger = LogFactory.getLog(getClass());
    public String newEndUser() {
		logger.info("method 'newEndUser' invoked");
		return "create-enduser";
	}
    public String newAdministrator() {
		logger.info("method 'newEndUser' invoked");
		return "create-administrator";
	}
}
