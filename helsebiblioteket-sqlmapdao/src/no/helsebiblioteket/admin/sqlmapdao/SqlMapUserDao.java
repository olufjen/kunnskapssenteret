package no.helsebiblioteket.admin.sqlmapdao;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapUserDao extends SqlMapClientDaoSupport implements UserDao {
	@Override
	public void insertUser(User user){
		getSqlMapClientTemplate().insert("insertUser", user);		
	}
	@Override
	public void updateUser(User user){
		getSqlMapClientTemplate().update("updateUser", user);
	}
	@Override
	public void deleteUser(User user){
		getSqlMapClientTemplate().delete("deleteUser", user.getId());
	}
	@Override
	public User getUserByUsername(String username){
		return (User) getSqlMapClientTemplate().queryForObject("getUserByUsername", username);
	}
}
