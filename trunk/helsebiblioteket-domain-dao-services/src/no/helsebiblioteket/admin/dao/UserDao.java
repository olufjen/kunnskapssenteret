package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.User;

public interface UserDao {
	public User findUserByUsername(User user);
	public List<User> getUserList();
	public void createUser(User user);
	
}
