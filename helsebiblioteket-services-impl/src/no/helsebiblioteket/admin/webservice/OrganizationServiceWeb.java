package no.helsebiblioteket.admin.webservice;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationTypeKey;
import no.helsebiblioteket.admin.listobjects.OrganizationListItem;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationServiceWeb extends BasicWebService implements OrganizationService {
	protected static final Log logger = LogFactory.getLog(OrganizationServiceWeb.class);
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private Options options;
	private QName organizationTypeListAllName;
	private QName organizationTypeByKeyName;
	private QName organizationListAllName;
	private QName findOrganizationsBySearchString;
	private QName organizationByListItemName;
	private QName insertOrganizationName;
	private QName updateOrganizationName;
	public void init(){
		options = serviceClient.getOptions();
	    options.setTo(targetEPR);
	}
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

	
	public Log getLogger() {
		return this.logger;
	}
	public RPCServiceClient getServiceClient() {
		return serviceClient;
	}

	
	public void setServiceClient(RPCServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}

	public void setTargetEPR(EndpointReference targetEPR) {
		this.targetEPR = targetEPR;
	}
}
