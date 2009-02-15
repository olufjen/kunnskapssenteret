package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.OrganizationService;

@SuppressWarnings("serial")
public class OrganizationServiceWeb extends BasicWebService implements OrganizationService {
	protected static final Log logger = LogFactory.getLog(OrganizationServiceWeb.class);
	private QName organizationTypeListAllName;
	private QName organizationTypeByKeyName;
	private QName organizationListAllName;
	private QName findOrganizationsBySearchString;
	private QName organizationByListItemName;
	private QName insertMemberOrganizationName;
	private QName updateMemberOrganizationName;
	private QName insertSupplierOrganizationName;
	private QName updateSupplierOrganizationName;
	private QName allPositionsName;
	@Override
	public ListResultOrganizationType getOrganizationTypeListAll(String DUMMY) {
		Object[] args = new Object[] { DUMMY  };
		Class[] returnTypes = new Class[] { ListResultOrganizationType.class };
		return (ListResultOrganizationType) invoke(this.organizationTypeListAllName, args, returnTypes);
	}
	@Override
	public SingleResultOrganizationType getOrganizationTypeByKey(OrganizationTypeKey key) {
		Object[] args = new Object[] { key  };
		Class[] returnTypes = new Class[] { SingleResultOrganizationType.class };
		return (SingleResultOrganizationType) invoke(this.organizationTypeByKeyName, args, returnTypes);
	}
	@Override
	public PageResultOrganizationListItem getOrganizationListAll(PageRequest request) {
		Object[] args = new Object[] { request  };
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResultOrganizationListItem) invoke(this.organizationListAllName, args, returnTypes);
	}
	@Override
	public PageResultOrganizationListItem findOrganizationsBySearchString(String searchString, PageRequest request) {
		Object[] args = new Object[] { request  };
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResultOrganizationListItem) invoke(this.findOrganizationsBySearchString, args, returnTypes);
	}


	@Override
	public SingleResultOrganization getOrganizationByListItem(OrganizationListItem organizationListItem) {
		Object[] args = new Object[] { organizationListItem  };
		Class[] returnTypes = new Class[] { SingleResultOrganization.class };
		return (SingleResultOrganization) invoke(this.organizationByListItemName, args, returnTypes);
	}
	@Override
	public Boolean insertMemberOrganization(MemberOrganization organization) {
		Object[] args = new Object[] { organization  };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean) invoke(this.insertMemberOrganizationName, args, returnTypes);
	}
	@Override
	public Boolean updateMemberOrganization(MemberOrganization organization) {
		Object[] args = new Object[] { organization  };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean) invoke(this.updateMemberOrganizationName, args, returnTypes);
	}
	@Override
	public Boolean insertSupplierOrganization(SupplierOrganization organization) {
		Object[] args = new Object[] { organization  };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean) invoke(this.insertSupplierOrganizationName, args, returnTypes);
	}
	@Override
	public Boolean updateSupplierOrganization(SupplierOrganization organization) {
		Object[] args = new Object[] { organization  };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean) invoke(this.updateSupplierOrganizationName, args, returnTypes);
	}
	@Override
	public ListResultOrganizationListItem getOrganizationListByIpAdress(IpAddress ipAddress) {
		Object[] args = new Object[] { ipAddress  };
		Class[] returnTypes = new Class[] { ListResultOrganizationListItem.class };
		return (ListResultOrganizationListItem) invoke(this.organizationListAllName, args, returnTypes);
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
	public void setFindOrganizationsBySearchString(
			QName findOrganizationsBySearchString) {
		this.findOrganizationsBySearchString = findOrganizationsBySearchString;
	}
	public void setAllPositionsName(QName allPositionsName) {
		this.allPositionsName = allPositionsName;
	}
	public void setInsertMemberOrganizationName(QName insertMemberOrganizationName) {
		this.insertMemberOrganizationName = insertMemberOrganizationName;
	}
	public void setUpdateMemberOrganizationName(QName updateMemberOrganizationName) {
		this.updateMemberOrganizationName = updateMemberOrganizationName;
	}
	public void setInsertSupplierOrganizationName(QName insertSupplierOrganizationName) {
		this.insertSupplierOrganizationName = insertSupplierOrganizationName;
	}
	public void setUpdateSupplierOrganizationName(QName updateSupplierOrganizationName) {
		this.updateSupplierOrganizationName = updateSupplierOrganizationName;
	}
}
