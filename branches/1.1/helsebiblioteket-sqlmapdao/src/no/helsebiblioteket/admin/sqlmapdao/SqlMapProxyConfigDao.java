package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import no.helsebiblioteket.admin.dao.ProxyConfigDao;
import no.helsebiblioteket.admin.domain.line.ProxyConfigLine;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SqlMapProxyConfigDao extends SqlMapClientDaoSupport implements ProxyConfigDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProxyConfigLine> getProxyConfigListAll() {
		return (List<ProxyConfigLine>) getSqlMapClientTemplate().queryForList("getProxyConfigListAll");
	}

}