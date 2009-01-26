package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.domain.Resource;


public class SqlMapResourceDao extends SqlMapClientDaoSupport implements ResourceDao {
	// TODO: Go through all!
	public void insertResource(Resource resource){
		
		getSqlMapClientTemplate().insert("insertResource", resource);

	}
	public void updateResource(Resource resource){
		
		getSqlMapClientTemplate().update("updateResource", resource);

	}
	public void deleteResource(Resource resource){
		
		getSqlMapClientTemplate().delete("deleteResource", resource);

	}
	public List<Resource> getResourceListAll(){
		return null;
	}


	
	
	
	
	
	
	
	public Resource getResourceById(Integer resourceId) {
		return (Resource) getSqlMapClientTemplate().queryForObject("getResourceById", resourceId);
	}
	
	public List<Resource> getResourceList() {
		return (List<Resource>) getSqlMapClientTemplate().queryForList("getResourceList");
	}
	
	
	
}
