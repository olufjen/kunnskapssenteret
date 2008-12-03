package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.User;

public interface UserCompositeDao {
	public User getUserByUsername(User user);
	
	public List<User> getAllUsers();
//	public List<User> getAllUsersByNameSearchString(String name, List<Role> roles);
}
