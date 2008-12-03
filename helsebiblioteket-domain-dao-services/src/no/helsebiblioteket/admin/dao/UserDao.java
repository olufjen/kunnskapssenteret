package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.User;

public interface UserDao {
	public User getUserByUsername(User user);

	public void insertUser(User user);
	public void updateUser(User user);
}
