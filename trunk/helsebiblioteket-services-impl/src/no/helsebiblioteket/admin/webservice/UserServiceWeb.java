package no.helsebiblioteket.admin.webservice;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.listobjects.UserListItem;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
@SuppressWarnings("serial")

public class UserServiceWeb extends BasicWebService implements UserService {
	protected static final Log logger = LogFactory.getLog(UserServiceWeb.class);
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private Options options;
	private QName roleListAllName;
	private QName positionListAllName;
	private QName roleByKeyName;
	private QName userListAllName;
	private QName findUsersBySearchStringRolesName;
	private QName findUserByUsernameName;
	private QName insertUserName;
	private QName updateUserName;

	public void init(){
		options = serviceClient.getOptions();
	    options.setTo(targetEPR);
	}

	public ListResult<Role> getRoleListAll(String DUMMY){
		Object[] args = new Object[] { DUMMY };
		Class[] returnTypes = new Class[] { ListResult.class };
		return (ListResult<Role>)invoke(this.roleListAllName, args, returnTypes);
	}
	public ListResult<Position> getPositionListAll(String DUMMY){
		Object[] args = new Object[] { DUMMY };
		Class[] returnTypes = new Class[] { ListResult.class };
		return (ListResult<Position>)invoke(this.positionListAllName, args, returnTypes);
	}
	public SingleResult<Role> getRoleByKey(String key){
		Object[] args = new Object[] { key };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<Role>)invoke(this.roleByKeyName, args, returnTypes);
	}
	public PageResult<UserListItem> getUserListAll(PageRequest<UserListItem> request){
		Object[] args = new Object[] { request };
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResult<UserListItem>)invoke(this.userListAllName, args, returnTypes);
	}
	public PageResult<UserListItem> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest<UserListItem> request){
		Object[] args = new Object[] { searchString, roles, request};
		Class[] returnTypes = new Class[] { PageResult.class };
		return (PageResult<UserListItem>)invoke(this.findUsersBySearchStringRolesName, args, returnTypes);
	}
	public SingleResult<User> findUserByUsername(String username){
		Object[] args = new Object[] { username };
		Class[] returnTypes = new Class[] { SingleResult.class };
		return (SingleResult<User>)invoke(this.findUserByUsernameName, args, returnTypes);
	}
	public Boolean insertUser(User user){
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.insertUserName, args, returnTypes);
	}
	public Boolean updateUser(User user){
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { Boolean.class };
		return (Boolean)invoke(this.updateUserName, args, returnTypes);
	}

	
	public Log getLogger() {
		return this.logger;
	}
	public RPCServiceClient getServiceClient() {
		return this.serviceClient;
	}


	public void setServiceClient(RPCServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}
	public void setTargetEPR(EndpointReference targetEPR) {
		this.targetEPR = targetEPR;
	}
}
