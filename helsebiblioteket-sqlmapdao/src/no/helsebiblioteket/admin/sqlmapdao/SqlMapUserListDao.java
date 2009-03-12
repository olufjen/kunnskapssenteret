package no.helsebiblioteket.admin.sqlmapdao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.UserListDao;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.line.UserListLine;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.sqlmapdao.input.SearchStringInput;

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
		Map<Integer, List<UserListLine>> allLinesByUserId = new HashMap<Integer, List<UserListLine>>();
		for (Role role : roles) {
			List<Integer> ids = getSqlMapClientTemplate().queryForList("getUserIdsSearchRole",
					new SearchStringInput("%" + searchString + "%", role.getId(), null, null),
					skip, max);
			if(ids.size() != 0) {
				List<UserListLine> lines = getSqlMapClientTemplate().queryForList("getUserListSearchRole",
						new SearchStringInput("%" + searchString + "%", role.getId(),
								ids.get(0), ids.get(ids.size()-1)),
						skip, max);
				for (UserListLine line : lines) {
					if(allLinesByUserId.size() == max){ break; }
					List<UserListLine> found = allLinesByUserId.get(line.getId());
					if(found == null){
						found =  new ArrayList<UserListLine>();
						allLinesByUserId.put(line.getId(), found);
					}
					found.add(line);
				}
			}
			if(allLinesByUserId.size() == max){ break; }
		}
		Integer[] sortedIds = allLinesByUserId.keySet().toArray(new Integer[0]);
		Arrays.sort(sortedIds);
		List<Integer> reverseIds = Arrays.asList(sortedIds);
		Collections.reverse(reverseIds);
		List<UserListItem> sortedItems = new ArrayList<UserListItem>();
		for (Integer integer : sortedIds) {
			sortedItems.addAll(translateList(allLinesByUserId.get(integer)));
		}
		return sortedItems;
	}
	private List<UserListItem> translateList(List<UserListLine> lines){
		List<UserListItem> result = new ArrayList<UserListItem>();
		if(lines.size()==0){ return result; }
		UserListLine line = lines.get(0);
		UserListItem working = new UserListItem();
		working.setId(line.getId());
		// TODO: Move name adding to service or domain!
		working.setName(line.getFirstName() + " " + line.getLastName());
		working.setUsername(line.getUsername());
		List<String> workingRoleNames = new ArrayList<String>();
		List<UserRoleKey> workingRoleKeys = new ArrayList<UserRoleKey>();
		workingRoleNames.add(line.getRoleName());
		workingRoleKeys.add(line.getRoleKey());
		for (int i=1; i<lines.size(); i++) {
			line = lines.get(i);
			if( ! working.getId().equals(line.getId())){
				working.setRoleNames(translateRoleNames(workingRoleNames));
				working.setRoleKeys(translateRoleKeys(workingRoleKeys));
				result.add(working);
				
				working = new UserListItem();
				working.setId(line.getId());
				working.setName(line.getFirstName() + " " + line.getLastName());
				working.setUsername(line.getUsername());
				workingRoleNames = new ArrayList<String>();
				workingRoleKeys = new ArrayList<UserRoleKey>();
			}
			workingRoleNames.add(line.getRoleName());
			workingRoleKeys.add(line.getRoleKey());
		}
		result.add(working);
		return result;
	}
	private UserRoleKey[] translateRoleKeys(List<UserRoleKey> workingRoleKeys) {
		int count = 0;
		for (UserRoleKey userRoleKey : workingRoleKeys) {
			if(userRoleKey != null && userRoleKey.getValue() != null) count++;
		}
		UserRoleKey[] result = new UserRoleKey[count];
		int i = 0;
		for (UserRoleKey userRoleKey : workingRoleKeys) {
			if(userRoleKey != null && userRoleKey.getValue() != null)  {
				result[i] = userRoleKey;
				i++;
			}
		}
		return result;
	}
	private String[] translateRoleNames(List<String> workingRoleNames) {
		int count = 0;
		for (String string : workingRoleNames) {
			if(string != null) count++;
		}
		String[] result = new String[count];
		int i=0;
		for (String string : workingRoleNames) {
			if(string != null) {
				result[i] = string;
				i++;
			}
		}
		return result;
	}
}
