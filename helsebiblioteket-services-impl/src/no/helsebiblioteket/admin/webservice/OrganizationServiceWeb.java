package no.helsebiblioteket.admin.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.PositionList;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationServiceWeb implements OrganizationService {
	protected static final Log logger = LogFactory.getLog(OrganizationServiceWeb.class);
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private Options options;
	private QName allPositionsName;
	public void init(){
		options = serviceClient.getOptions();
	    options.setTo(targetEPR);
	}

	public void createOrganization(Organization organization) {
		// TODO Auto-generated method stub

	}
	public PageResult<Organization> findOrganizationsBySearchStringRoles(
			String searchString, PageRequest<Organization> request) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Organization> getAllOrganizations() {
		// TODO Auto-generated method stub
		return null;
	}
	public PositionList getAllPositions(String dummy) {
		Object[] args = new Object[] { dummy  };
		Class[] returnTypes = new Class[] { PositionList.class };
		try {
			Object[] response = serviceClient.invokeBlocking(
					this.allPositionsName, args, returnTypes);
			PositionList result = (PositionList) response[0];
			
			return result;
		} catch (AxisFault e) {
			// TODO What do we do here?
			logger.error(e);
			return new PositionList();
		}
	}
	public MemberOrganization getMemberOrganization(Integer organizationId) {
		// TODO Auto-generated method stub
		return null;
	}
	public Organization getOrganization(Integer organizationId) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<OrganizationType> getOrganizationTypeList() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<SupplierOrganization> getSupplierList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setServiceClient(RPCServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}

	public void setTargetEPR(EndpointReference targetEPR) {
		this.targetEPR = targetEPR;
	}

	public void setAllPositionsName(QName allPositionsName) {
		this.allPositionsName = allPositionsName;
	}

	@Override
	public void saveOrganization(Organization organization) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrganizationType getOrganizationTypeByKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
