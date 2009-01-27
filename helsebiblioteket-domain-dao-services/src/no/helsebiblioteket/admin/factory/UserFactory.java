package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

public class UserFactory {
	public static UserFactory factory = new UserFactory();
	private UserFactory(){}
	public User createUser(){
		User user = new User();
		user.setUsername("");
		user.setPassword("");
//		user.setAccessList(new ArrayList<Access>());
		user.setRoleList(new ArrayList<Role>());
		user.setLastChanged(new Date());
//		protected Organization organization;
//		private Person person;
//		private Date lastChanged;

		return user;
	}
}
