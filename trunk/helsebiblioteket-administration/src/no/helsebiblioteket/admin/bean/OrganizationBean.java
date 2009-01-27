package no.helsebiblioteket.admin.bean;

import java.util.List;

import javax.faces.component.html.HtmlDataTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.listobjects.OrganizationListItem;
import no.helsebiblioteket.admin.requestresult.FirstPageRequest;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private OrganizationService organizationService;
	private String searchinput;
	private List<OrganizationListItem> organizations;
	private Organization organization;
	private HtmlDataTable organizationsTable;
	
	// TODO: Include the ipRangeList in the
	//       Details view (organization-details.jsp)
	
	public String actionEditSingle(){
		// TODO: Of course not do that!
		return "organization_edit";
	}
    public boolean getFailed() { return true; }
    public String getErrorMsg() { return "ERRORS WILL BE PUT HERE!"; }
	public String actionDetails(){
		this.organization = (Organization) this.organizationsTable.getRowData();
		// FIXME: Pass this on to the edit bean!
		return "organization_details";
//		else "organizations_overview";
	}
	public String actionEdit(){
		this.organization = (Organization) this.organizationsTable.getRowData();
		// FIXME: Pass this on to the edit bean!
		return "organization_edit";
//		else "organizations_overview";
	}
	public void search() {
		if(this.searchinput == null) { this.searchinput = ""; }
		PageRequest<OrganizationListItem> request = new FirstPageRequest<OrganizationListItem>(Integer.MAX_VALUE);
		this.organizations = this.organizationService.findOrganizationsBySearchString(this.searchinput, request).result;
	}
	public List<OrganizationListItem> getOrganizations() {
		PageRequest<OrganizationListItem> request = new FirstPageRequest<OrganizationListItem>(Integer.MAX_VALUE);
		PageResult<OrganizationListItem> pageResult = this.organizationService.getOrganizationListAll(request);
		this.organizations = pageResult.result;
		return this.organizations;
	}
	public String getSearchinput() { return searchinput; }
	public void setSearchinput(String searchinput) { this.searchinput = searchinput; }
	public HtmlDataTable getOrganizationsTable() { return organizationsTable; }
	public void setOrganizationsTable(HtmlDataTable organizationsTable) { this.organizationsTable = organizationsTable; }
	public void setOrganizationService(OrganizationService organizationService) { this.organizationService = organizationService; }
	public Organization getOrganization() {
		return organization;
	}
}
