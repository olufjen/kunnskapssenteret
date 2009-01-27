package no.helsebiblioteket.admin.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class UserServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testGetRoleListAll(){
		// TODO: Write tests.
//		public ListResult<Role> getRoleListAll(String DUMMY);
//		public ListResult<Position> getPositionListAll(String DUMMY);
//		public SingleResult<Role> getRoleByKey(String key);
//		public PageResult<User> getUserListAll(PageRequest<User> request);
//		public PageResult<User> findUsersBySearchStringRoles(String searchString, List<Role> roles, PageRequest<User> request);
//		public SingleResult<User> findUserByUsername(String username);
//		public Boolean insertUser(User user);
//		public Boolean updateUser(User user);
	}
}
