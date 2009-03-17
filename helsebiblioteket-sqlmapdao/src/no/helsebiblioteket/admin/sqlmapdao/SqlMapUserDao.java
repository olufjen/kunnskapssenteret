package no.helsebiblioteket.admin.sqlmapdao;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.OrganizationUser;

public class SqlMapUserDao extends SqlMapClientDaoSupport implements UserDao {
	@Override
	public void insertUser(OrganizationUser user){
		getSqlMapClientTemplate().insert("insertUser", user);
		OrganizationUser tmp = (OrganizationUser) getSqlMapClientTemplate().queryForObject("getUserByUsername", user.getUser().getUsername());
		user.getUser().setLastChanged(tmp.getUser().getLastChanged());
	}
	@Override
	public void updateUser(OrganizationUser user){
		getSqlMapClientTemplate().update("updateUser", user);
		OrganizationUser tmp = (OrganizationUser) getSqlMapClientTemplate().queryForObject("getUserByUsername", user.getUser().getUsername());
		user.getUser().setLastChanged(tmp.getUser().getLastChanged());
	}
	@Override
	public void deleteUser(OrganizationUser user){
		getSqlMapClientTemplate().delete("deleteUser", user);
	}
	@Override
	public OrganizationUser getUserByUsername(String username){
		return (OrganizationUser) getSqlMapClientTemplate().queryForObject("getUserByUsername", username);
	}
	@Override
	public OrganizationUser getUserById(Integer id) {
		return (OrganizationUser) getSqlMapClientTemplate().queryForObject("getUserById", id);
	}
}
