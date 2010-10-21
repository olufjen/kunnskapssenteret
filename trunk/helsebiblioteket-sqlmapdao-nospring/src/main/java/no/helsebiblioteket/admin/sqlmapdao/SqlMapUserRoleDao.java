package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserRoleDao;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.line.UserRoleLine;

public class SqlMapUserRoleDao extends SqlMapClientDaoSupport implements UserRoleDao {
	@Override
	public void insertUserRole(UserRoleLine userRole) {
		getSqlMapClientTemplate().insert("insertUserRole", userRole);
		UserRoleLine tmp = (UserRoleLine) getSqlMapClientTemplate().queryForObject("getUserRoleLineByUserRoleLine", userRole);
		userRole.setLastChanged(tmp.getLastChanged());
	}
	public void deleteUserRole(UserRoleLine userRole) {
		getSqlMapClientTemplate().delete("deleteUserRole", userRole);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleLine> getUserRoleListByUser(User user) {
		return getSqlMapClientTemplate().queryForList("getUserRoleListByUserId", user.getId());
	}
}