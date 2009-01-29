package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.dao.ResourceTypeDao;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceType;

public class SqlMapResourceTypeDao extends SqlMapClientDaoSupport implements ResourceTypeDao{

	@Override
	public void deleteResourceType(ResourceType resourceType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Resource> getResourceTypeListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertResourceType(ResourceType resourceType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateResourceType(ResourceType resourceType) {
		// TODO Auto-generated method stub
		
	}

}
