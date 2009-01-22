package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.User;

public class SqlMapUserDao extends SqlMapClientDaoSupport implements UserDao {
	
	public void setForeignKeysForUser(User user) {
		if (user != null) {
			if (user.getAccessList() != null) {
				for (Access access : user.getAccessList()) {
					access.setUserId(user.getId());
				}
			}
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

	public void insertUser(User user) {
		getSqlMapClientTemplate().insert("insertUser", user);		
	}

	public void updateUser(User user) {
		getSqlMapClientTemplate().update("updateUser", user);
	}

	public List<User> getAllUsers() {
		return (List<User>) getSqlMapClientTemplate().queryForList("getAllUsers");
	}
}
