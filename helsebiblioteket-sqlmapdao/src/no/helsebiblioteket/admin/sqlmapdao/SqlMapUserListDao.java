package no.helsebiblioteket.admin.sqlmapdao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserListDao;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.line.UserListLine;
import no.helsebiblioteket.admin.domain.list.UserListItem;

public class SqlMapUserListDao extends SqlMapClientDaoSupport implements UserListDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<UserListItem> getUserListPaged(int skip, int max) {
		List<UserListLine> lines = getSqlMapClientTemplate().queryForList("getUserListPaged", skip, max);
		return translateList(lines);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserListItem> getUserListPagedSearchStringRoles(String searchString, List<Role> roles, int skip, int max) {
		List<UserListLine> lines = getSqlMapClientTemplate().queryForList("getUserListPaged", skip, max);
		List<UserListItem> translated = translateList(lines);
		List<UserListItem> result = new ArrayList<UserListItem>();
		for (UserListItem listItem : translated) {
			if(listItem.getName().toLowerCase().contains(searchString.toLowerCase()) ||
					listItem.getUsername().toLowerCase().contains(searchString.toLowerCase())){
				for (Role role : roles) {
					// TODO: Fix! Handles only one role!
					if(listItem.getRoleKeys().equals(role.getKey())) { result.add(listItem); break; }
				}
			}
		}
		return result;
	}
	private List<UserListItem> translateList(List<UserListLine> lines){
		List<UserListItem> result = new ArrayList<UserListItem>();
		for (UserListLine line : lines) {
			UserListItem item = new UserListItem();
			item.setUsername(line.getUsername());

			// TODO: Move name adding to service or domain!
			item.setName(line.getFirstName() + line.getLastName());

			// TODO: Fix! Handles only one role!
			List<String> names = new ArrayList<String>();
			names.add(line.getRoleName());
			item.setRoleNames(names);
			List<UserRoleKey> keys = new ArrayList<UserRoleKey>();
			keys.add(line.getRoleKey());
			item.setRoleKeys(keys);
			
			result.add(item);
		}
		return result;
	}
}
