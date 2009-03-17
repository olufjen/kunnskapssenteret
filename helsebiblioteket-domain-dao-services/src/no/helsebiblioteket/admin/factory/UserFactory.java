package no.helsebiblioteket.admin.factory;

import java.util.Date;

import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;

public class UserFactory {
	public static UserFactory factory = new UserFactory();
	private UserFactory(){}
	public User createUser(){
		User user = new User();
		user.setUsername("");
		user.setPassword("");
		user.setLastChanged(new Date());
		user.setRoleList(new Role[0]);
		user.setPerson(PersonFactory.factory.createPerson());
		return user;
	}
	public User completeUser(Position position){
		User user = createUser();
		user.setPerson(PersonFactory.factory.completePerson(position));
		return user;
	}
}
