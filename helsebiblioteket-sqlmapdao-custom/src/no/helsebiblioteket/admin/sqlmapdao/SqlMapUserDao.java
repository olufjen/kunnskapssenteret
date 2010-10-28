package no.helsebiblioteket.admin.sqlmapdao;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;

public class SqlMapUserDao extends IbatisSqlMapClientDaoSupport implements UserDao {
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
		user.getUser().setUsername(user.getUser().getId() + "_" + user.getUser().getUsername());
		user.getUser().setDeleted(true);
		getSqlMapClientTemplate().update("updateUser", user);
	}
	@Override
	public OrganizationUser getUserByUsername(String username){
		return (OrganizationUser) getSqlMapClientTemplate().queryForObject("getUserByUsername", username);
	}
	@Override
	public OrganizationUser getUserById(Integer id) {
		return (OrganizationUser) getSqlMapClientTemplate().queryForObject("getUserById", id);
	}
	@Override
	public OrganizationUser getDeletedUserByUsername(String username) {
		return (OrganizationUser) getSqlMapClientTemplate().queryForObject("getDeletedUserByUsername", username);
	}
}