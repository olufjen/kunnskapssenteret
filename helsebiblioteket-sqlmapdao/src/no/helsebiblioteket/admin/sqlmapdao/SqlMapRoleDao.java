package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.daoobjects.RoleSystemCompositeKey;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;

public class SqlMapRoleDao extends SqlMapClientDaoSupport implements RoleDao {
	@Override
	public Role getRoleByKeySystem(UserRoleKey roleKey, System system) {
		RoleSystemCompositeKey compositeKey = new RoleSystemCompositeKey();
		compositeKey.setRoleKey(roleKey.toString());
		compositeKey.setSystemKey(system.getKey().toString());
		return (Role) getSqlMapClientTemplate().queryForObject("getRoleByKeySystem", compositeKey);
	}
	@SuppressWarnings("unchecked")
	public List<Role> getRoleListBySystem(System system) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("getRoleListBySystemId", system.getSystemId());
	}
}
