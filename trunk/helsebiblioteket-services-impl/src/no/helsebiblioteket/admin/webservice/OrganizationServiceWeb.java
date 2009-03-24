package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultIpAddressSet;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;

@SuppressWarnings("serial")
public class OrganizationServiceWeb extends BasicWebService implements OrganizationService {
	protected static final Log logger = LogFactory.getLog(OrganizationServiceWeb.class);
	private QName organizationTypeListAllName;
	private QName organizationTypeByKeyName;
	private QName organizationListAllName;
	private QName organizationListIpAddressName;
	private QName findOrganizationsBySearchString;
	private QName organizationByListItemName;
	private QName insertMemberOrganizationName;
	private QName updateOrganizationName;
	private QName addIpAddresses;
	private QName addIpAddressRanges;
	private QName deleteIpAddresses;
	private QName insertSupplierOrganizationName;

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
		Class[] returnTypes = new Class[] { PageResultOrganizationListItem.class };
		return (PageResultOrganizationListItem) invoke(this.organizationListAllName, args, returnTypes);
	}
	@Override
	public PageResultOrganizationListItem getOrganizationListBySearchString(PageRequest request, String searchString) {
		Object[] args = new Object[] { request, searchString  };
		Class[] returnTypes = new Class[] { PageResultOrganizationListItem.class };
		return (PageResultOrganizationListItem) invoke(this.findOrganizationsBySearchString, args, returnTypes);
	}


	@Override
	public SingleResultOrganization getOrganizationByListItem(OrganizationListItem organizationListItem) {
		Object[] args = new Object[] { organizationListItem  };
		Class[] returnTypes = new Class[] { SingleResultOrganization.class };
		return (SingleResultOrganization) invoke(this.organizationByListItemName, args, returnTypes);
	}
	@Override
	public SingleResultOrganization insertMemberOrganization(MemberOrganization memberOrganization) {
		// TODO: These tests should not be here.
		if(memberOrganization.getIpAddressRangeList() == null) {
			throw new NullPointerException("IpAddressRangeList should not be null");
		}
		if(memberOrganization.getIpAddressSingleList() == null){
			throw new NullPointerException("IpAddressSingleList should not be null");
		}
		Object[] args = new Object[] { memberOrganization };
		Class[] returnTypes = new Class[] { SingleResultOrganization.class };
		return (SingleResultOrganization) invoke(this.insertMemberOrganizationName, args, returnTypes);
	}
	@Override
	public SingleResultOrganization insertSupplierOrganization(SupplierOrganization supplierOrganization) {
		// TODO: These tests should not be here.
		if(supplierOrganization.getResourceList() == null){
			throw new NullPointerException("ResourceList should not be null");
		}
		
		Object[] args = new Object[] { supplierOrganization };
		Class[] returnTypes = new Class[] { SingleResultOrganization.class };
		return (SingleResultOrganization) invoke(this.insertSupplierOrganizationName, args, returnTypes);
	}
	@Override
	public SingleResultOrganization updateOrganization(Organization organization) {
		Object[] args = new Object[] { organization  };
		Class[] returnTypes = new Class[] { SingleResultOrganization.class };
		return (SingleResultOrganization) invoke(this.updateOrganizationName, args, returnTypes);
	}
	@Override
	public ListResultIpAddressSet addIpAddresses(Organization organization, IpAddressSingle[] ipAddressSets) {
		Object[] args = new Object[] { organization, ipAddressSets  };
		Class[] returnTypes = new Class[] { ListResultIpAddressSet.class };
		return (ListResultIpAddressSet) invoke(this.addIpAddresses, args, returnTypes);
	}
	@Override
	public ListResultIpAddressSet addIpAddressRanges(Organization organization, IpAddressRange[] ipAddressRanges) {
		Object[] args = new Object[] { organization, ipAddressRanges  };
		Class[] returnTypes = new Class[] { ListResultIpAddressSet.class };
		return (ListResultIpAddressSet) invoke(this.addIpAddressRanges, args, returnTypes);
	}
	@Override
	public Boolean deleteIpAddresses(IpAddressSet[] ipAddressSets) {
		Object[] args = new Object[] { ipAddressSets  };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean) invoke(this.deleteIpAddresses, args, returnTypes);
	}
	@Override
	public ListResultOrganizationListItem getOrganizationListByIpAddress(IpAddress ipAddress) {
		Object[] args = new Object[] { ipAddress  };
		Class[] returnTypes = new Class[] { ListResultOrganizationListItem.class };
		return (ListResultOrganizationListItem) invoke(this.organizationListIpAddressName, args, returnTypes);
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
	public void setInsertMemberOrganizationName(QName insertMemberOrganizationName) {
		this.insertMemberOrganizationName = insertMemberOrganizationName;
	}
	public void setUpdateOrganizationName(QName updateOrganizationName) {
		this.updateOrganizationName = updateOrganizationName;
	}
	public void setInsertSupplierOrganizationName(QName insertSupplierOrganizationName) {
		this.insertSupplierOrganizationName = insertSupplierOrganizationName;
	}
	public void setAddIpAddresses(QName addIpAddresses) {
		this.addIpAddresses = addIpAddresses;
	}
	public void setDeleteIpAddresses(QName deleteIpAddresses) {
		this.deleteIpAddresses = deleteIpAddresses;
	}
	public void setAddIpAddressRanges(QName addIpAddressRanges) {
		this.addIpAddressRanges = addIpAddressRanges;
	}
	public void setOrganizationListIpAddressName(QName organizationListIpAddressName) {
		this.organizationListIpAddressName = organizationListIpAddressName;
	}
}