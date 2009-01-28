package no.helsebiblioteket.admin.sqlmapdao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.dao.UserListDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.list.UserListItem;


public class SqlMapUserDao extends SqlMapClientDaoSupport implements UserDao {
	// TODO: Go through all!
	public void insertUser(User user){

		getSqlMapClientTemplate().insert("insertUser", user);		

	}
	public void updateUser(User user){
		
		getSqlMapClientTemplate().update("updateUser", user);

	}
	public void deleteUser(User user){
		
	}
	public User getUserByUsername(String username){
		return null;
	}

	
	
	
	
	
	
	
	public void setForeignKeysForUser(User user) {
		if (user != null) {
//			if (user.getAccessList() != null) {
//				for (Access access : user.getAccessList()) {
//					// TODO: What is this?
////					access.setUserId(user.getId());
//				}
//			}
		}
	}
	
	public User getUserById(Integer userId) {
		return (User) getSqlMapClientTemplate().queryForObject("getUserById", userId);
	}

	public User findUser(String username) {
		return (User) getSqlMapClientTemplate().queryForObject("getUserByUsername", username);
	}

	public User getUserByUsername(User user) {
		return (User) getSqlMapClientTemplate().queryForObject("getUserByUsername", user.getUsername());
	}



	public List<User> getAllUsers() {
		List<User> users = (List<User>) getSqlMapClientTemplate().queryForList("getAllUsers");
		for (User user : users) {
			if (user.getRoleList() == null){
				user.setRoleList(new ArrayList<UserRole>());
			}
		}
		return users;
	}
}
