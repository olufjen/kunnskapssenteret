package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserRoleDao;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.line.UserRoleLine;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;

public class SqlMapUserRoleDao extends IbatisSqlMapClientDaoSupport implements UserRoleDao {
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