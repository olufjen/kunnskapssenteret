package no.helsebiblioteket.admin.sqlmapdao;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapUserDao extends SqlMapClientDaoSupport implements UserDao {
	public void insertUser(User user){
		getSqlMapClientTemplate().insert("insertUser", user);		
	}
	public void updateUser(User user){
		getSqlMapClientTemplate().update("updateUser", user);
	}
	public void deleteUser(User user){
		getSqlMapClientTemplate().delete("deleteUser", user.getId());
	}
	public User getUserByUsername(String username){
		return (User) getSqlMapClientTemplate().queryForObject("getUserByUsername", username);
	}
}
