package no.helsebiblioteket.admin.bean;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.tree.DefaultMutableTreeNode;
import org.apache.myfaces.custom.tree.HtmlTree;
import org.apache.myfaces.custom.tree.IconProvider;
import org.apache.myfaces.custom.tree.model.DefaultTreeModel;
import org.apache.myfaces.custom.tree.model.TreeModel;
import org.apache.myfaces.custom.tree.model.TreeModelEvent;

import no.helsebiblioteket.admin.bean.item.OrganizationTableItem;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

public class OrganizationBean implements IconProvider{
	private static final String bundleDomainOrganizationTypeKey = "no.helsebiblioteket.admin.web.jsf.messageresources.domain_organizationtypekey";
	protected final Log logger = LogFactory.getLog(getClass());
	private OrganizationService organizationService;
	private AccessService accessService;
	private String searchinput;
	private OrganizationListItem[] organizations;
	private Organization organization;
	private MemberOrganization memberOrganization;
	private SupplierOrganization supplierOrganization;
	private HtmlDataTable organizationsTable;
	private Boolean isNew;
	private TreeModel treeModel;
	private HtmlTree htmlTree;
	private String searchedString;
	private SupplierSourceResource[] supplierSourceResources = null;
	private List<IpAddressRange> ipRangeList;

	protected List<SupplierSourceResource> deltetedResources;
	protected List<ResourceAccessListItem> deltetedAccesses;
	protected ResourceAccessListItem[] orgTypeAccessList;
	protected ResourceAccessListItem[] orgAccessList;

//	private boolean showMore = true;
//	private boolean showMoreLeft = true;
//	private boolean showMoreRight = true;
	private PageResultOrganizationListItem lastPageResult = null;
	private int SHOW_MAX = 40;
	
	public boolean getFailed() { return true; }
    public String getErrorMsg() { return "ERRORS WILL BE PUT HERE!"; }
	public String actionForward(){
		if(this.getShowMoreRight()){
			if(this.searchedString == null) this.searchedString = "";
			PageRequest pageRequest = new PageRequest(this.lastPageResult.getSkipped() + SHOW_MAX,
					SHOW_MAX);
			this.lastPageResult = this.organizationService.getOrganizationListBySearchString(pageRequest, this.searchedString);
			this.organizations = this.lastPageResult.getResult();
			this.resetTreeModel();
		}
		return "organizations_overview";
	}
	public String actionBackward(){
		if(this.getShowMoreLeft()){
			if(this.searchedString == null) this.searchedString = "";
			PageRequest pageRequest = new PageRequest(this.lastPageResult.getSkipped() - SHOW_MAX,
					SHOW_MAX);
			this.lastPageResult = this.organizationService.getOrganizationListBySearchString(pageRequest, this.searchedString);
			this.organizations = this.lastPageResult.getResult();
			this.resetTreeModel();
		}
		return "organizations_overview";
	}
	public String actionDetails(){
		return actionDetailsEdit(false, true);
	}
	public String actionEdit() {
		return this.actionDetailsEdit(true, true);
	}
	private String actionDetailsEdit(boolean edit, boolean fromTree){
		OrganizationListItem item;
		if(fromTree){
			FacesContext context = FacesContext.getCurrentInstance();
			String paramString = (String) context.getExternalContext().getRequestParameterMap().get("treeOrgId");
			Integer val = new Integer(paramString);
			item = null;
			for (OrganizationListItem itemCheck : this.organizations) {
				if(itemCheck.getId().equals(val)){
					item = itemCheck;
					break;
				}
			}
		} else {
			item = new OrganizationListItem();
			item.setId(this.organization.getId());
		}
		SingleResultOrganization res = this.organizationService.getOrganizationByListItem(item);
		this.isNew = false;
		if(res instanceof ValueResultMemberOrganization){
			this.memberOrganization = ((ValueResultMemberOrganization)res).getValue();
			this.supplierOrganization = null;
			this.organization =  memberOrganization.getOrganization();
			this.orgTypeAccessList = this.accessService.getAccessListByOrganizationType(this.organization.getType()).getList();
			this.orgAccessList = this.accessService.getAccessListByOrganization(this.organization).getList();
			this.supplierSourceResources = this.accessService.getSupplierSourceResourceListAll("").getList();
			this.ipRangeList = new ArrayList<IpAddressRange>();
			for (IpAddressRange range : this.memberOrganization.getIpAddressRangeList()) {
				ipRangeList.add(range);
			}
			for (IpAddressSingle single : this.memberOrganization.getIpAddressSingleList()) {
				IpAddressRange range = new IpAddressRange();
				range.setIpAddressFrom(single.getIpAddressSingle());
				range.setIpAddressTo(new IpAddress(""));
				range.setIpAddressSet(single.getIpAddressSet());
				ipRangeList.add(range);
			}
			this.deltetedAccesses = new ArrayList<ResourceAccessListItem>();
			if(edit) return "create_change_member_organization";
		} else if(res instanceof ValueResultSupplierOrganization){
			this.memberOrganization = null;
			this.supplierOrganization = ((ValueResultSupplierOrganization)res).getValue();
			this.organization = supplierOrganization.getOrganization();
			this.deltetedResources = new ArrayList<SupplierSourceResource>();
			if(edit) return "create_change_supplier_organization";
		} else {
			this.organization = null;
			return "organizations_overview";
		}
		return "organization_details";
	}
	public String actionEditSingle(){
		return actionDetailsEdit(true, false);
	}
	public String actionDetailsSingle(){
		return actionDetailsEdit(false, false);
	}

	public String actionNewSupplierOrganization() {
		this.isNew = true;
		this.supplierOrganization = new SupplierOrganization();
		this.supplierOrganization.getOrganization().setContactInformation(new ContactInformation());
		this.supplierOrganization.getOrganization().setContactPerson(new Person());
		this.supplierOrganization.getOrganization().getContactPerson().setContactInformation(new ContactInformation());
		this.supplierOrganization.getOrganization().getContactPerson().setProfile(new Profile());
		this.supplierOrganization.setResourceList(new SupplierSourceResource[0]);
		SingleResultOrganizationType result = organizationService.getOrganizationTypeByKey(OrganizationTypeKey.content_supplier);
    	if(result instanceof ValueResultOrganizationType){
			this.supplierOrganization.getOrganization().setType(((ValueResultOrganizationType) result).getValue());
		}
    	this.deltetedResources = new ArrayList<SupplierSourceResource>();

		return "create_change_supplier_organization";
	}
	public String actionNewMemberOrganization() {
		this.isNew = true;
		this.memberOrganization = new MemberOrganization();
		this.memberOrganization.getOrganization().setContactInformation(new ContactInformation());
		this.memberOrganization.getOrganization().setContactPerson(new Person());
		this.memberOrganization.getOrganization().getContactPerson().setContactInformation(new ContactInformation());
		this.memberOrganization.getOrganization().getContactPerson().setProfile(new Profile());
		this.memberOrganization.getOrganization().setNameEnglish("");
		this.memberOrganization.getOrganization().setNameNorwegian("");
		this.memberOrganization.getOrganization().setNameShortEnglish("");
		this.memberOrganization.getOrganization().setNameShortNorwegian("");
		this.memberOrganization.setIpAddressRangeList(new IpAddressRange[0]);

		this.supplierSourceResources = this.accessService.getSupplierSourceResourceListAll("").getList();
		this.deltetedResources = new ArrayList<SupplierSourceResource>();
		this.orgTypeAccessList = new ResourceAccessListItem[0];
		this.orgAccessList = new ResourceAccessListItem[0];
		this.ipRangeList = new ArrayList<IpAddressRange>();
		
		return "create_change_member_organization";
	}
	public void actionSearch() {
		if(this.searchinput == null) { this.searchinput = ""; }
		this.searchedString = this.searchinput;
		this.runSearch();
	}
	public void runSearch() {
		PageRequest request = new PageRequest(0, SHOW_MAX);
		if(this.searchedString == null) this.searchedString = "";
		this.lastPageResult = this.organizationService.getOrganizationListBySearchString(request, this.searchedString);
		this.organizations = this.lastPageResult.getResult();
		this.resetTreeModel();
	}
	public void actionExport(){
		List<String> lines = new ArrayList<String>();
		PageRequest request = new PageRequest(0, SHOW_MAX);
		String searchString = this.searchedString;
		if(searchString == null){ searchString = ""; }
		PageResultOrganizationListItem result = this.organizationService.getOrganizationListBySearchString(request, searchString);
		while (true){
			for (OrganizationListItem item : result.getResult()) {
				String group = item.getTypeText();
				String name = item.getNameNorwegian();
				if(name.equals("")) name = item.getNameEnglish();
				int i=0;
				if(item.getIpAddressesFrom().length == 0){
					lines.add(group + ";" + name + ";;");
				}
				for (String ip : item.getIpAddressesFrom()) {
					String IPFrom = ip;
					String IPTo = item.getIpAddressesTo()[i++];

					lines.add(group + ";" + name + ";" + IPFrom + ";" + IPTo);
					
					group = "";
					name = "";
				}
			}
			if(result.getSkipped() + result.getNumber() >= result.getTotal()){
				break;
			} else {
				request = new PageRequest(result.getSkipped() + result.getNumber(), SHOW_MAX);
				result = this.organizationService.getOrganizationListBySearchString(request, searchString);
			}
		}
		this.sendFile(lines);
	}
	private void sendFile(List<String> lines) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType("application/txt");
		response.setHeader("Content-Disposition", "attachment;filename=\"" +
				   "download.cvs" + "\""); 
		try {
			PrintStream ps = new PrintStream(response.getOutputStream());
			for (String s : lines) {
				ps.append(s);
				ps.append("\n");
			}
			ps.flush();
			ps.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().responseComplete();
	}
	public void resetTreeModel() {
		DefaultMutableTreeNode root;
		if(this.treeModel == null){
			OrganizationTableItem rootItem = new OrganizationTableItem();
			rootItem.setText("");
			root = new DefaultMutableTreeNode(rootItem);
			this.treeModel = new DefaultTreeModel(root);
		} else {
			root = ((DefaultMutableTreeNode)this.treeModel.getRoot());
		}
		Iterator it = root.children();
		List<DefaultMutableTreeNode> list = new ArrayList<DefaultMutableTreeNode>();
		while(it.hasNext()){
			DefaultMutableTreeNode itNode = (DefaultMutableTreeNode) it.next();
			list.add(itNode);
		}
		for (DefaultMutableTreeNode defaultMutableTreeNode : list) {
			root.remove(defaultMutableTreeNode);
		}
		for (OrganizationListItem item : this.organizations) {
			OrganizationTableItem tableItem = new OrganizationTableItem();
			String name = item.getNameNorwegian();
			if(name.equals("")) name = item.getNameEnglish();
			tableItem.setText(name);
			tableItem.setOrganization(item);
			tableItem.setShowLinks(true);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(tableItem);
            root.insert(node);
            int i=0;
			for (String ipFromString : item.getIpAddressesFrom()) {
				OrganizationTableItem tableSubItem = new OrganizationTableItem();
				String ipToString = item.getIpAddressesTo()[i++];
				if(ipToString.equals("")){
					tableSubItem.setText(ipFromString);
				} else {
					tableSubItem.setText(ipFromString + " - " + ipToString);
				}
            	DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(tableSubItem);
            	node.insert(subNode);
			}
		}
		if(this.organizations.length == 0){
			OrganizationTableItem tableItem = new OrganizationTableItem();
			tableItem.setText("NONE");
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(tableItem);
            root.insert(node);
		}
		if(this.htmlTree != null){
			this.htmlTree.setIconProvider(this);
			this.htmlTree.treeStructureChanged(new TreeModelEvent(root, new Object[]{root}));
		}
	}

	public String getIconUrl(Object userObject, int childCount, boolean isLeaf) {
		if(userObject instanceof DefaultMutableTreeNode &&
				((DefaultMutableTreeNode)userObject).getUserObject() instanceof OrganizationTableItem){
			OrganizationTableItem item = (OrganizationTableItem) ((DefaultMutableTreeNode)userObject).getUserObject();
			if(item==null || item.getOrganization() == null){
				return "images/NOT_EXIST.gif";
			} else {
				if(item.getOrganization().getTypeKey().equals(OrganizationTypeKey.content_supplier.getValue())){
					return "images/supplier.gif";
				} else {
					return "images/not_supplier.gif";
				}
			}
		} else {
			return "images/NOT_EXIST.gif";
		}
	}

	public TreeModel getTreeModel() {
		if(this.treeModel == null){
			if(this.organizations == null){
				getOrganizations();
			}
			this.resetTreeModel();
		}
        return this.treeModel;
	}
	public void setTreeModel(TreeModel treeModel) {
        this.treeModel = treeModel;
	}
	public OrganizationListItem[] getOrganizations() {
		PageRequest request = new PageRequest(0, SHOW_MAX);
		this.lastPageResult = this.organizationService.getOrganizationListAll(request);
		this.organizations = this.lastPageResult.getResult();
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
	public Boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	public HtmlTree getHtmlTree() {
		if(this.htmlTree == null){
			this.htmlTree = new HtmlTree();
		}
		this.htmlTree.setIconProvider(this);
		return htmlTree;
	}
	public void setHtmlTree(HtmlTree htmlTree) {
		this.htmlTree = htmlTree;
		this.htmlTree.setIconProvider(this);
	}
	public SupplierSourceResource[] getSupplierSourceResources() {
		return supplierSourceResources;
	}
	public ResourceAccessListItem[] getOrgTypeAccessList() {
		return orgTypeAccessList;
	}
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	public ResourceAccessListItem[] getOrgAccessList() {
		return orgAccessList;
	}
	public void setOrgAccessList(ResourceAccessListItem[] orgAccessList) {
		this.orgAccessList = orgAccessList;
	}
	public String getOrganizationTypeName() {
		return MessageResourceReader.getMessageResourceString(bundleDomainOrganizationTypeKey, organization.getType().getKey().getValue());
	}
	public boolean getShowMore() {
		if(this.lastPageResult.getTotal() > SHOW_MAX){
			return true;
		} else {
			return false;
		}
	}
	public boolean getShowMoreLeft() {
		if(this.lastPageResult.getSkipped() > 0){
			return true;
		} else {
			return false;
		}
	}
	public boolean getShowMoreRight() {
		if(this.lastPageResult.getTotal() >
			this.lastPageResult.getSkipped() + this.lastPageResult.getNumber()){
			return true;
		} else {
			return false;
		}
	}
	public AccessService getAccessService() {
		return accessService;
	}
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	public List<IpAddressRange> getIpRangeList() {
		return ipRangeList;
	}
	public void setIpRangeList(List<IpAddressRange> ipRangeList) {
		this.ipRangeList = ipRangeList;
	}
}
