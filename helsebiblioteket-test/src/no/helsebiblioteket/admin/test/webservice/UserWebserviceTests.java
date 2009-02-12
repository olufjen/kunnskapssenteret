package no.helsebiblioteket.admin.test.webservice;

import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.service.UserService;

import org.junit.Assert;

public class UserWebserviceTests {
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void testGetPositionListAll(){
		Position[] positions = this.userService.getPositionListAll("").getList();
		Assert.assertNotNull("No result", positions);
	}
}
