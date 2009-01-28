package no.helsebiblioteket.admin.sqlmapdao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.dao.UserListDao;
import no.helsebiblioteket.admin.dao.UserRoleDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.line.UserRoleLine;
import no.helsebiblioteket.admin.domain.list.UserListItem;


public class SqlMapUserRoleDao extends SqlMapClientDaoSupport implements UserRoleDao {
	// TODO: Go through all!

	@Override
	public void deleteUserRole(UserRoleLine userRole) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserRoleLine> getUserRoleListByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUserRole(UserRoleLine userRole) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserRole(UserRoleLine userRole) {
		// TODO Auto-generated method stub
		
	}
}
