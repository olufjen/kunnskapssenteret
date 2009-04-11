package no.helsebiblioteket.admin.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationTypeBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private OrganizationService organizationService;
	
	public String someMethod(){
		this.organizationService.getOrganizationTypeListAll("");
		return "";
	}
	
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

}
