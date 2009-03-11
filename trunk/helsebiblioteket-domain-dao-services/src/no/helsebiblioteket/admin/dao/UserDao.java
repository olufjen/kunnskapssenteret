package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.User;

public interface UserDao {
	// Edit
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);

	// Fetch
	public User getUserByUsername(String username);
	public User getUserById(Integer id);
}
