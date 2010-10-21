package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.ResourceTypeDao;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;

public class SqlMapResourceTypeDao extends SqlMapClientDaoSupport implements ResourceTypeDao{
	public ResourceType getResourceTypeByKey(ResourceTypeKey resourceTypeKey) {
		return (ResourceType) getSqlMapClientTemplate().queryForObject("getResourceTypeByKey", resourceTypeKey);
	}
	@SuppressWarnings("unchecked")
	public List<ResourceType> getResourceTypeListAll() {
		return getSqlMapClientTemplate().queryForList("getResourceTypeListAll");
	}
}
