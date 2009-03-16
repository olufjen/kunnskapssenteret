package no.helsebiblioteket.admin.bean;

import javax.faces.component.html.HtmlDataTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private OrganizationService organizationService;
	private String searchinput;
	private OrganizationListItem[] organizations;
	private Organization organization;
	private MemberOrganization memberOrganization;
	private SupplierOrganization supplierOrganization;
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
		OrganizationListItem item = (OrganizationListItem) this.organizationsTable.getRowData();
		SingleResultOrganization res = this.organizationService.getOrganizationByListItem(item);
		if(res instanceof ValueResultMemberOrganization){
			this.memberOrganization = ((ValueResultMemberOrganization)res).getValue();
			this.supplierOrganization = null;
			this.organization =  memberOrganization.getOrganization();
		} else if(res instanceof ValueResultSupplierOrganization){
			this.memberOrganization = null;
			this.supplierOrganization = ((ValueResultSupplierOrganization)res).getValue();
			this.organization = supplierOrganization.getOrganization();			
		} else {
			this.organization = null;
		}
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
		PageRequest request = new PageRequest(0, 40);
		this.organizations = this.organizationService.getOrganizationListBySearchString(request, this.searchinput).getResult();
	}
	public OrganizationListItem[] getOrganizations() {
		PageRequest request = new PageRequest(0, 40);
		PageResultOrganizationListItem pageResult = this.organizationService.getOrganizationListAll(request);
		this.organizations = pageResult.getResult();
		return this.organizations;
	}
	public String getSearchinput() { return searchinput; }
	public void setSearchinput(String searchinput) { this.searchinput = searchinput; }
	public HtmlDataTable getOrganizationsTable() { return organizationsTable; }
	public void setOrganizationsTable(HtmlDataTable organizationsTable) { this.organizationsTable = organizationsTable; }
	public void setOrganizationService(OrganizationService organizationService) { this.organizationService = organizationService; }
	public boolean getIsMemberOrganization(){ return this.memberOrganization != null; }
	public boolean getIsSupplierOrganization(){ return this.supplierOrganization != null; }
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public MemberOrganization getMemberOrganization() {
		return memberOrganization;
	}
	public void setMemberOrganization(MemberOrganization memberOrganization) {
		this.memberOrganization = memberOrganization;
	}
	public SupplierOrganization getSupplierOrganization() {
		return supplierOrganization;
	}
	public void setSupplierOrganization(SupplierOrganization supplierOrganization) {
		this.supplierOrganization = supplierOrganization;
	}
	public Organization getOrganization() {
		// TODO: Delete this debugging information
		
		organization.getDescription();
		organization.getType();

		organization.getContactInformation().getPostalAddress();
		organization.getContactInformation().getPostalCode();
		organization.getContactInformation().getPostalLocation();
		organization.getContactInformation().getEmail();
		organization.getContactInformation().getTelephoneNumber();

		organization.getContactPerson().getFirstName();
		organization.getContactPerson().getLastName();
		organization.getContactPerson().getName();

		organization.getContactPerson().getHprNumber();
		organization.getContactPerson().getStudentNumber();
		organization.getContactPerson().getEmployer();
		organization.getContactPerson().getPosition().getName();

		organization.getContactPerson().getProfile().getParticipateSurvey();
		organization.getContactPerson().getProfile().getReceiveNewsletter();

		organization.getContactPerson().getContactInformation().getPostalAddress();
		organization.getContactPerson().getContactInformation().getPostalCode();
		organization.getContactPerson().getContactInformation().getPostalLocation();
		organization.getContactPerson().getContactInformation().getEmail();
		organization.getContactPerson().getContactInformation().getTelephoneNumber();

		if(this.getIsMemberOrganization()){
			MemberOrganization memberOrganization = this.memberOrganization;
			for (IpAddressRange range : memberOrganization.getIpAddressRangeList()) {
				range.getIpAddressFrom();
				range.getIpAddressTo();
			}
			for (IpAddressSingle single : memberOrganization.getIpAddressSingleList()) {
				single.getIpAddressSingle();
			}
		}

		if(this.getIsSupplierOrganization()){
			SupplierOrganization supplierOrganization = this.supplierOrganization;
			for (SupplierSourceResource resource : supplierOrganization.getResourceList()) {
				resource.getResource().getResourceType();
				resource.getSupplierSource().getSupplierSourceName();
				resource.getSupplierSource().getUrl();
			}
		}
		
		return organization;
	}
}