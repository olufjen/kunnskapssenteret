package no.helsebiblioteket.admin.bean;

import no.helsebiblioteket.admin.service.importexport.ImportEndUsersService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WelcomeBean {
	private ImportEndUsersService importEndUsersService;
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
	
    public void setImportEndUsersService(ImportEndUsersService importEndUsersService) {
    	this.importEndUsersService = importEndUsersService;
    }
    
	public String actionImportAllEndUsers() {
		logger.info("method 'actionImportAllEndUsersOut' invoked");
		importEndUsersService.importAllEndUsers();
		return "welcome";
	}
}