package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.service.OrganizationService;

@SuppressWarnings("serial")
public class OrganizationServiceWeb extends BasicWebService implements OrganizationService {
	protected static final Log logger = LogFactory.getLog(OrganizationServiceWeb.class);
	private QName organizationTypeListAllName;
	private QName organizationTypeByKeyName;
	private QName organizationListAllName;
	private QName findOrganizationsBySearchString;
	private QName organizationByListItemName;
	private QName insertOrganizationName;
	private QName updateOrganizationName;
	private QName allPositionsName;
	public ListResult<OrganizationType> getOrganizationTypeListAll(String DUMMY) {
		Object[] args = new Object[] { DUMMY  };
		Class[] returnTypes = new Class[] { ListResult.class };
		return (ListResult<OrganizationType>) invoke(this.organizationTypeListAllName, args, returnTypes);
	}
	public SingleResult<OrganizationType> getOrganizationTypeByKey(OrganizationTypeKey key) {
		Object[] args = new Object[] { key  };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<OrganizationType>) invoke(this.organizationTypeByKeyName, args, returnTypes);
	}
	public PageResult<OrganizationListItem> getOrganizationListAll(PageRequest<OrganizationListItem> request) {
		Object[] args = new Object[] { request  };
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResult<OrganizationListItem>) invoke(this.organizationListAllName, args, returnTypes);
	}
	public PageResult<OrganizationListItem> findOrganizationsBySearchString(String searchString, PageRequest<OrganizationListItem> request) {
		Object[] args = new Object[] { request  };
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResult<OrganizationListItem>) invoke(this.findOrganizationsBySearchString, args, returnTypes);
	}


	public SingleResult<Organization> getOrganizationByListItem(OrganizationListItem organizationListItem) {
		Object[] args = new Object[] { organizationListItem  };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<Organization>) invoke(this.organizationByListItemName, args, returnTypes);
	}
	public Boolean insertOrganization(Organization organization) {
		Object[] args = new Object[] { organization  };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean) invoke(this.insertOrganizationName, args, returnTypes);
	}
	public Boolean updateOrganization(Organization organization) {
		Object[] args = new Object[] { organization  };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean) invoke(this.updateOrganizationName, args, returnTypes);
	}
	public ListResult<OrganizationListItem> getOrganizationListByIpAddress(IpAddress ipAddress) {
		Object[] args = new Object[] { ipAddress  };
		Class[] returnTypes = new Class[] { ListResult.class };
		return (ListResult<OrganizationListItem>) invoke(this.updateOrganizationName, args, returnTypes);
	}

	
	public Log getLogger() {
		return this.logger;
	}
	public void setOrganizationTypeListAllName(QName organizationTypeListAllName) {
		this.organizationTypeListAllName = organizationTypeListAllName;
	}
	public void setOrganizationTypeByKeyName(QName organizationTypeByKeyName) {
		this.organizationTypeByKeyName = organizationTypeByKeyName;
	}
	public void setOrganizationListAllName(QName organizationListAllName) {
		this.organizationListAllName = organizationListAllName;
	}
	public void setOrganizationByListItemName(QName organizationByListItemName) {
		this.organizationByListItemName = organizationByListItemName;
	}
	public void setUpdateOrganizationName(QName updateOrganizationName) {
		this.updateOrganizationName = updateOrganizationName;
	}
	public void setFindOrganizationsBySearchString(
			QName findOrganizationsBySearchString) {
		this.findOrganizationsBySearchString = findOrganizationsBySearchString;
	}
	public void setInsertOrganizationName(QName insertOrganizationName) {
		this.insertOrganizationName = insertOrganizationName;
	}
	public void setAllPositionsName(QName allPositionsName) {
		this.allPositionsName = allPositionsName;
	}
}
