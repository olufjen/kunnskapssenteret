package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.RoleDao;
import no.helsebiblioteket.admin.dao.keys.RoleSystemCompositeKey;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;

public class SqlMapRoleDao extends SqlMapClientDaoSupport implements RoleDao {
	@Override
	public Role getRoleByKeySystem(UserRoleKey roleKey, System system) {
		RoleSystemCompositeKey compositeKey = new RoleSystemCompositeKey();
		compositeKey.setRoleKey(roleKey.getValue());
		compositeKey.setSystemKey(system.getKey().getValue());
		return (Role) getSqlMapClientTemplate().queryForObject("getRoleByKeySystem", compositeKey);
	}
	@SuppressWarnings("unchecked")
	public List<Role> getRoleListBySystem(System system) {
		return (List<Role>) getSqlMapClientTemplate().queryForList("getRoleListBySystemId", system.getId());
	}
	public Role getRoleById(Integer roleId) {
		return (Role) getSqlMapClientTemplate().queryForObject("getRoleById", roleId);
	}
}
