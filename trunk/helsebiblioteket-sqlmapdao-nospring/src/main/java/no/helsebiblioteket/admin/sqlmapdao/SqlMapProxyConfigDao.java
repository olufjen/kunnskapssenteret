package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import no.helsebiblioteket.admin.dao.ProxyConfigDao;
import no.helsebiblioteket.admin.domain.line.ProxyConfigLine;

import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;

public class SqlMapProxyConfigDao extends IbatisSqlMapClientDaoSupport implements ProxyConfigDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProxyConfigLine> getProxyConfigListAll() {
		return (List<ProxyConfigLine>) getSqlMapClientTemplate().queryForList("getProxyConfigListAll");
	}

}
