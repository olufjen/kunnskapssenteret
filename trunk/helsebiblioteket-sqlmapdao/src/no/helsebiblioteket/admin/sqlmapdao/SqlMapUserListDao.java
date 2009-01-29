package no.helsebiblioteket.admin.sqlmapdao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.dao.UserListDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.list.UserListItem;


public class SqlMapUserListDao extends SqlMapClientDaoSupport implements UserListDao {
	// TODO: Go through all!



	@Override
	public List<UserListItem> getUserListPaged(int from, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserListItem> getUserListPagedSearchStringRoles(
			String searchString, List<Role> roles, int from, int max) {
		// TODO Auto-generated method stub
		return null;
	}
}
