package no.helsebiblioteket.admin.webservice;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
@SuppressWarnings("serial")

public class UserServiceWeb implements UserService {
	protected static final Log logger = LogFactory.getLog(UserServiceWeb.class);
	private RPCServiceClient serviceClient;
	private EndpointReference targetEPR;
	private Options options;
	private QName saveUser;
	private QName findUser;
	private QName createUser;
	public void init(){
		options = serviceClient.getOptions();
	    options.setTo(targetEPR);
	}
	public User invokeUserFunction(User user, QName name) {
		Object[] args = new Object[] { user };
		Class[] returnTypes = new Class[] { User.class };
		try {
			Object[] response = serviceClient.invokeBlocking(name,args, returnTypes);
			return (User) response[0];
		} catch (AxisFault e) {
			logger.error(e);
			return null;
		}
	}
	public User saveUser(User user) {
		return this.invokeUserFunction(user, this.saveUser);
	}
	public User findUserByUsername(User user) {
		return this.invokeUserFunction(user, this.findUser);
	}
	public User createUser(User user) {
		return this.invokeUserFunction(user, this.createUser);
	}

	
	public PageResult<User> findUsersBySearchStringRoles(String searchString,
			List<Role> roles, PageRequest<User> request) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Position> getAllUserPositions() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Role> getAllUserRoles() {
		// TODO Auto-generated method stub
		return null;
	}
	public PageResult<User> getAllUsers(PageRequest<User> request) {
		// TODO Auto-generated method stub
		return null;
	}
	public Role getRoleByKey(Role role) {
		// TODO Auto-generated method stub
		return null;
	}
	public void setSaveUser(QName saveUser) {
		this.saveUser = saveUser;
	}
	public QName getSaveUser() {
		return saveUser;
	}
	public void setServiceClient(RPCServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}
	public void setTargetEPR(EndpointReference targetEPR) {
		this.targetEPR = targetEPR;
	}
	public void setFindUser(QName findUser) {
		this.findUser = findUser;
	}
	public void setCreateUser(QName createUser) {
		this.createUser = createUser;
	}
}
