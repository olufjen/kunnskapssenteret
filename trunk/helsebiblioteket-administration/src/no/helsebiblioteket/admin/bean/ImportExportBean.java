package no.helsebiblioteket.admin.bean;

import no.helsebiblioteket.admin.service.importexport.ImportEndUsersService;
import no.helsebiblioteket.admin.service.importexport.ImportMemberOrganizationsService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImportExportBean {
	private ImportEndUsersService importEndUsersService;
	private ImportMemberOrganizationsService importMemberOrganizationsService;
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
	
    public void setImportEndUsersService(ImportEndUsersService importEndUsersService) {
    	this.importEndUsersService = importEndUsersService;
    }
    
    public void setImportMemberOrganizationsService(ImportMemberOrganizationsService importMemberOrganizationsService) {
    	this.importMemberOrganizationsService = importMemberOrganizationsService;
    }
    
	public String actionImportAllEndUsers() {
		logger.debug("method 'actionImportAllEndUsers' invoked");
		importEndUsersService.importAllEndUsers();
		return "login";
	}
	
	public String actionImportMemberOrganizations() {
		logger.debug("method 'actionImportMemberOrganizations' invoked");
		importMemberOrganizationsService.importAllMemberOrganizations();
		return "login";
	}
}