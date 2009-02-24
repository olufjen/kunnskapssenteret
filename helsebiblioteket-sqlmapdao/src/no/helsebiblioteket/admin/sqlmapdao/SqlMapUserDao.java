package no.helsebiblioteket.admin.sqlmapdao;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapUserDao extends SqlMapClientDaoSupport implements UserDao {
	@Override
	public void insertUser(User user){
		getSqlMapClientTemplate().insert("insertUser", user);
		User tmp = (User) getSqlMapClientTemplate().queryForObject("getUserByUsername", user.getUsername());
		user.setLastChanged(tmp.getLastChanged());
	}
	@Override
	public void updateUser(User user){
		getSqlMapClientTemplate().update("updateUser", user);
		User tmp = (User) getSqlMapClientTemplate().queryForObject("getUserByUsername", user.getUsername());
		user.setLastChanged(tmp.getLastChanged());
	}
	@Override
	public void deleteUser(User user){
		getSqlMapClientTemplate().delete("deleteUser", user);
	}
	@Override
	public User getUserByUsername(String username){
		return (User) getSqlMapClientTemplate().queryForObject("getUserByUsername", username);
	}
}
