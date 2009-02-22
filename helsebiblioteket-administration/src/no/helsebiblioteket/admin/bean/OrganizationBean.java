package no.helsebiblioteket.admin.bean;

import java.util.List;

import javax.faces.component.html.HtmlDataTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.requestresult.FirstPageRequest;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private OrganizationService organizationService;
	private String searchinput;
	private OrganizationListItem[] organizations;
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
	public String actionEdit() {
		String actionString = "organizations_overview";
		setOrganization((Organization) this.organizationsTable.getRowData());
		String orgTypeKey = this.organization.getType().getKey().toString();
		if (orgTypeKey.equals(OrganizationTypeKey.content_supplier.toString())) {
			actionString = "create_change_supplier_organization";
		} else if (
				orgTypeKey.equals(OrganizationTypeKey.health_enterprise.toString())
				|| orgTypeKey.equals(OrganizationTypeKey.other.toString())
				|| orgTypeKey.equals(OrganizationTypeKey.public_administration.toString())
				|| orgTypeKey.equals(OrganizationTypeKey.teaching.toString())
			) {
			actionString = "create_change_member_organization";
		}
		logger.debug("changing organization with id " + this.organization.getId());
		return actionString;
	}
	public void search() {
		if(this.searchinput == null) { this.searchinput = ""; }
		PageRequest request = new PageRequest(0, Integer.MAX_VALUE);
		this.organizations = this.organizationService.getOrganizationListBySearchString(request, this.searchinput).getResult();
	}
	public OrganizationListItem[] getOrganizations() {
		PageRequest request = new PageRequest(0, Integer.MAX_VALUE);
		PageResultOrganizationListItem pageResult = this.organizationService.getOrganizationListAll(request);
		this.organizations = pageResult.getResult();
		return this.organizations;
	}
	public String getSearchinput() { return searchinput; }
	public void setSearchinput(String searchinput) { this.searchinput = searchinput; }
	public HtmlDataTable getOrganizationsTable() { return organizationsTable; }
	public void setOrganizationsTable(HtmlDataTable organizationsTable) { this.organizationsTable = organizationsTable; }
	public void setOrganizationService(OrganizationService organizationService) { this.organizationService = organizationService; }
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Organization getOrganization() {
		return organization;
	}
}
